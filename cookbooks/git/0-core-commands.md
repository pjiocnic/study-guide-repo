
```bash
# 1. Remove staged files
git rm --cached <file>   # unstages but keeps the file on disk
git rm <file>            # unstages AND deletes the file from disk

# 2. list staged files with sizes
git diff --cached --name-only -z | xargs -0 ls -lh
```