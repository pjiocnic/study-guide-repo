# Cookbook: Block Large Files with a Git Pre-Commit Hook

## 0) TL;DR (copy–paste)

```bash
# In your repo
mkdir -p .githooks
cat > .githooks/pre-commit <<'SH'
#!/usr/bin/env bash
set -euo pipefail

MAX_SIZE_KB=${MAX_SIZE_KB:-10240}   # default 10 MB; override: MAX_SIZE_KB=51200 git commit ...
mapfile -t FILES < <(git diff --cached --name-only --diff-filter=ACMR)

for f in "${FILES[@]}"; do
  [[ -f "$f" ]] || continue
  size_kb=$(du -k -- "$f" | cut -f1)
  if [[ "$size_kb" -gt "$MAX_SIZE_KB" ]]; then
    echo "❌ Commit blocked: $f is ${size_kb}KB > limit ${MAX_SIZE_KB}KB."
    echo "   Fix: git reset -- \"$f\"  # unstage, or use Git LFS."
    exit 1
  fi
done
SH
chmod +x .githooks/pre-commit
git config core.hooksPath .githooks
```

---

## 1) Why a hook (and not `.gitignore`)?

`.gitignore` only matches **paths**, not **file sizes**. Hooks can inspect staged content and reject a commit if a rule is violated.

---

## 2) Quick Start (repo-local & shareable)

1. **Create a hooks folder and script** (same as TL;DR above).

2. **Make it executable**: `chmod +x .githooks/pre-commit`

3. **Point Git to your hooks folder**:

   ```bash
   git config core.hooksPath .githooks
   ```

   Commit `.githooks/pre-commit` so teammates get it.

4. **Test**:

   ```bash
   dd if=/dev/zero of=big.bin bs=1M count=15
   git add big.bin
   git commit -m "test"   # should be blocked (default 10 MB limit)
   ```

---

## 3) Customization Recipes

### A) Change the size limit (global or ad-hoc)

* Hardcode in script: `MAX_SIZE_KB=51200` (50 MB)
* Or per-commit:

  ```bash
  MAX_SIZE_KB=20480 git commit -m "20MB limit for this commit"
  ```

### B) Only enforce for certain extensions

```bash
# Add before the size check
case "$f" in
  *.zip|*.mp4|*.mov|*.7z) ;;     # allowed extensions to check
  *) continue ;;                 # skip all others
esac
```

### C) Skip specific paths

```bash
# Add before the size check
case "$f" in
  third_party/**|vendor/**|data/cache/**) continue ;;
esac
```

### D) Fail with a friendlier message + auto-suggest LFS

```bash
echo "👉 Consider: git lfs track \"$(basename "$f")\" && git add .gitattributes"
```

---

## 4) Cross-Platform Variants

### Bash (Linux/macOS, Git Bash on Windows)

Use the TL;DR script (works on Git Bash).

### PowerShell (Windows)

Save as `.githooks/pre-commit` **without extension**, but with PS shebang removed; configure Git to run it via `pwsh`:

```powershell
# .githooks/pre-commit (PowerShell)
param()
$ErrorActionPreference = "Stop"
$MAX_SIZE_KB = $env:MAX_SIZE_KB
if (-not $MAX_SIZE_KB) { $MAX_SIZE_KB = 10240 }

# staged files
$files = git diff --cached --name-only --diff-filter=ACMR
foreach ($f in $files) {
  if (Test-Path $f -PathType Leaf) {
    $size_kb = [int]([math]::Ceiling((Get-Item $f).Length / 1KB))
    if ($size_kb -gt $MAX_SIZE_KB) {
      Write-Host "❌ Commit blocked: $f is ${size_kb}KB > limit ${MAX_SIZE_KB}KB."
      Write-Host "   Fix: git reset -- `"$f`""
      exit 1
    }
  }
}
exit 0
```

Then:

```bash
git config core.hooksPath .githooks
```

---

## 5) Team-Wide Enforcement

### Server-side (pre-receive) hook (bare repo)

```bash
#!/usr/bin/env bash
set -euo pipefail
MAX_SIZE_KB=10240

while read old new ref; do
  # All new objects reachable from $new, excluding those from $old
  git rev-list $old..$new --objects --no-object-names |
  while read oid; do
    type=$(git cat-file -t "$oid") || continue
    [[ "$type" == "blob" ]] || continue
    size=$(git cat-file -s "$oid")
    size_kb=$(( (size + 1023) / 1024 ))
    if [[ $size_kb -gt $MAX_SIZE_KB ]]; then
      echo "❌ Push blocked: blob $oid is ${size_kb}KB > ${MAX_SIZE_KB}KB."
      exit 1
    fi
  done
done
```

Add to the server repo’s `hooks/pre-receive` and `chmod +x`.

> This prevents **any** contributor from pushing large files, even if they bypass local hooks.

---

## 6) Workflows & Tips

* **Unstage a large file** (keep it locally):

  ```bash
  git reset -- path/to/file
  ```
* **Clean staged set without deleting file**:

  ```bash
  git restore --staged path/to/file
  ```
* **Combine with `.gitignore`** for noisy binary patterns, and use the hook purely as a size gate.
* **For legitimate large assets**, prefer **Git LFS**.

---

## 7) Troubleshooting

* **Hook not running?**

  * Ensure: `git config core.hooksPath` prints `.githooks`
  * Ensure execute bit: `ls -l .githooks/pre-commit` (should show `x`)
  * Files must be **staged** to be checked.

* **CI still accepts large files?**
  Add a **server-side** `pre-receive` hook (see §5).

* **Already have large blobs in history?**
  You’ll need to rewrite history (e.g., `git filter-repo` or `git filter-branch`) and force-push. Then add the hook to prevent reintroduction.

---

## 8) Optional: Use the “pre-commit” framework

If your team uses the popular `pre-commit` framework, drop this into `.pre-commit-config.yaml`:

```yaml
repos:
  - repo: local
    hooks:
      - id: block-large-files
        name: Block large files
        entry: bash -c 'MAX_SIZE_KB=${MAX_SIZE_KB:-10240}; for f in $(git diff --cached --name-only --diff-filter=ACMR); do [ -f "$f" ] || continue; s=$(du -k -- "$f" | cut -f1); [ "$s" -le "$MAX_SIZE_KB" ] || { echo "❌ $f is ${s}KB > ${MAX_SIZE_KB}KB"; exit 1; }; done'
        language: system
        stages: [commit]
```

Then:

```bash
pip install pre-commit
pre-commit install
```

---

## 9) Handy Commands

* Show **staged** files with sizes:

  ```bash
  git diff --cached --name-only | xargs -I{} sh -c 'test -f "{}" && du -h "{}"'
  ```
* Find large **blobs in history** (top 20):

  ```bash
  git rev-list --objects --all |
  git cat-file --batch-check='%(objecttype) %(objectname) %(objectsize) %(rest)' |
  awk '$1=="blob"{print $3, $4}' | sort -nr | head -20
  ```

---

Want me to tailor the hook for your repo (e.g., different limits per folder, warn vs. block, or auto-suggest LFS by extension)?
