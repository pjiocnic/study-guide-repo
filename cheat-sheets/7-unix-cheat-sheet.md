# find

```bash
find . -type d ! -name 'folder_to_exclude*' -exec ls -l {} \;
find . -type d \( ! -name 'folder_to_exclude*' -o ! -name 'another_folder*' \) -exec ls -l {} \;
```

# Deleting old files

```bash
find /path/to/search -type f -exec ls -lt {} + | less
find /path/to/search -type f -mtime +7 -delete
```

# Replacement

```bash
#!/bin/bash

# Replace the following values with your actual strings
old_string="your_old_string"
new_string="your_new_string"

# Specify the directory where you want to start the search
directory="/path/to/search"

# Use find to locate files and sed to perform the replacement or removal
find "$directory" -type f -exec bash -c '
  for file do
    if grep -q "$new_string" "$file"; then
      # If the new string is present, remove the old string
      sed -i "s/${old_string%,}//g" "$file"
    else
      # If the new string is not present, perform the replacement
      sed -i "s/$old_string/$new_string/g" "$file"
    fi
  done
' bash {} +

```