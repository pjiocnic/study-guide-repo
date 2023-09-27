# Deleting old files

```bash
find /path/to/search -type f -exec ls -lt {} + | less
find /path/to/search -type f -mtime +7 -delete
```
