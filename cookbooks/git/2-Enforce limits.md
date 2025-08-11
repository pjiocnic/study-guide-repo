# Git Hook Cookbook — Enforce File Size Limits (with per-folder rules, warn vs. block, and LFS hints)

Here’s a drop-in, team-shareable setup you can commit to your repo. It supports:

* Different size limits per folder or extension
* Skipping specific paths
* “Warn” mode (prints warnings but lets commit proceed) or “Block” mode
* Git LFS suggestions for common large binaries

---

## 0) TL;DR — install (copy–paste)

```bash
# In your repo root
mkdir -p .githooks
cat > .githooks/pre-commit <<'SH'
#!/usr/bin/env bash
set -euo pipefail

# =========================
# CONFIG (edit to taste)
# =========================
MODE=${MODE:-block}                 # "block" or "warn"
DEFAULT_MAX_KB=${DEFAULT_MAX_KB:-10240}  # 10 MB default

# Suggest Git LFS for these extensions (no dot, lowercase)
LFS_SUGGEST_EXTS=(mp4 mov mkv avi zip 7z rar iso dylib so dll pdb tflite pt onnx)

# Policy rules are evaluated top-to-bottom; first match wins.
# "skip"  PATTERNS...     -> ignore these paths entirely
# "limit" KB PATTERNS...  -> apply this size limit to matching paths
# If nothing matches, DEFAULT_MAX_KB is used.
POLICIES=(
  "skip   third_party/** vendor/** data/cache/** build/** dist/** .venv/** node_modules/** .git/**"
  "limit  5120           **/*.png **/*.jpg **/*.jpeg **/*.gif **/*.pdf"
  "limit  20480          data/**"
  "limit  51200          models/** **/*.pt **/*.onnx **/*.tflite"
  "limit  10240          **"   # catch-all (10 MB)
)

# =========================
# implementation
# =========================
shopt -s extglob globstar nullglob

# get staged files (A=add, C=copy, M=modify, R=rename)
mapfile -t FILES < <(git diff --cached --name-only --diff-filter=ACMR)

violation_count=0

# helper: case-insensitive ext check
has_ext() {
  local f="$1" ext="${2,,}"
  local base="${f##*/}"
  [[ "${base,,}" == *".${ext}" ]]
}

# find effective policy for a file
effective_limit_for() {
  local f="$1"
  local kind
  local rest
  for rule in "${POLICIES[@]}"; do
    # shellcheck disable=SC2206
    parts=($rule)
    kind="${parts[0]}"
    if [[ "$kind" == "skip" ]]; then
      for pat in "${parts[@]:1}"; do
        [[ "$f" == $pat ]] && echo "SKIP" && return 0
      done
    elif [[ "$kind" == "limit" ]]; then
      local kb="${parts[1]}"
      for pat in "${parts[@]:2}"; do
        [[ "$f" == $pat ]] && echo "$kb" && return 0
      done
    fi
  done
  echo "$DEFAULT_MAX_KB"
}

# check each staged file
for f in "${FILES[@]}"; do
  [[ -f "$f" ]] || continue
  limit=$(effective_limit_for "$f")
  [[ "$limit" == "SKIP" ]] && continue

  size_kb=$(du -k -- "$f" | cut -f1)

  if (( size_kb > limit )); then
    ((violation_count++))
    echo "❌ Size gate: '$f' is ${size_kb}KB > limit ${limit}KB."

    # LFS hint if extension matches
    for ext in "${LFS_SUGGEST_EXTS[@]}"; do
      if has_ext "$f" "$ext"; then
        echo "   💡 Consider Git LFS:  git lfs track \"*.${ext}\" && git add .gitattributes"
        break
      fi
    done

    echo "   Fix:  git reset -- \"$f\"     # unstage"
    echo "         (or raise limit/policy if appropriate)"
    echo
  fi
done

if (( violation_count > 0 )); then
  if [[ "$MODE" == "warn" ]]; then
    echo "⚠️  $violation_count file(s) exceeded limits (warn mode: commit allowed)."
    exit 0
  else
    echo "⛔ $violation_count file(s) exceeded limits (block mode: commit aborted)."
    exit 1
  fi
fi

exit 0
SH
chmod +x .githooks/pre-commit
git config core.hooksPath .githooks
```

That’s it. Commit the `.githooks/pre-commit` file so your team gets it.

---

## 1) How to customize

* **Switch between warn/block** (per commit):

  ```bash
  MODE=warn git commit -m "try with warnings"
  # or persist by editing MODE in the script
  ```

* **Change the default limit**:

  ```bash
  DEFAULT_MAX_KB=20480 git commit -m "20MB default for this commit"
  # or edit DEFAULT_MAX_KB in the script
  ```

* **Add per-folder/extension limits**: change the `POLICIES` array (top to bottom priority).

  * Example: cap images at 5 MB and models at 50 MB (already in the template).
  * Add your own lines like:

    ```
    "limit  1024   logs/** **/*.log"
    "skip          experiments/snapshots/**"
    ```

* **Suggest LFS for more types**: add to `LFS_SUGGEST_EXTS`.

---

## 2) Quick tests

```bash
# 15MB file (should trip 10MB default)
dd if=/dev/zero of=big.bin bs=1M count=15
git add big.bin
git commit -m "test large"   # expect block
```

Try warn mode:

```bash
MODE=warn git commit -m "allow but warn"
```

---

## 3) Team-wide hard stop (server-side)

If you control the remote (e.g., self-hosted GitLab/GitHub Enterprise), add a **pre-receive** hook to the bare repo to reject pushes with big blobs. I can drop that in if you need it.

---

If you want, tell me your repo layout (folders that hold data, models, assets) and your desired limits, and I’ll pre-fill the `POLICIES` section for you.
