# find

```bash
find . -type d ! -name 'folder_to_exclude*' -exec ls -l {} \;
find . -type d \( ! -name 'folder_to_exclude*' -o ! -name 'another_folder*' \) -exec ls -l {} \;
for file in *; do mv "$file" `echo $file | tr ' ' '_'` ; done
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

sed_escape() { printf '%s\n' "$1" | sed 's/[]\.|$(){}?+*^]/\\&/g'; }
escaped_old_string=$(sed_escape "$old_string")
escaped_new_string==$(sed_escape "$new_string")

# Specify the directory where you want to start the search
directory="/path/to/search"

# Use find to locate files and sed to perform the replacement or removal
find "$directory" -type f -exec bash -c '
  for file do
    if grep -q "$new_string" "$file"; then
      # If the new string is present, remove the old string
      sed -i "s/${escaped_old_string%,}//g" "$file"
    else
      # If the new string is not present, perform the replacement
      sed -i "s/$escaped_old_string/$escaped_new_string/g" "$file"
    fi
  done
' bash {} +

```

# Unformat JSON

I have an formatted input json

```bash
{
"endpointApplications": {
    "App_Name": {
        "connectionState": "Disconnected",
        "connectionTime": "No connection was established",
        "linkAttributes": {
            "ackSettings": {
                "dataAckEnabled": "true",
                "dataAckTimeout": "5000",
                "dataNakRetryLimit": "0",
                "retransmitDelay": "500"
            },
            "keepAliveSettings": {
                "keepAliveAckTimeout": "5000",
                "keepAliveInterval": "30000"
            },
            "logTraffic": "false",
            "port": "9999",
            "role": "server"
        },
        "protocol": "snmp"
    }
},
"queueStats": {}
}
```

Use following command to unformat:

```bash
awk -v RS= '{$1=$1}1' input.json

{ "endpointApplications": { "App_Name": { "connectionState": "Disconnected", "connectionTime": "No connection was established", "linkAttributes": { "ackSettings": { "dataAckEnabled": "true", "dataAckTimeout": "5000", "dataNakRetryLimit": "0", "retransmitDelay": "500" }, "keepAliveSettings": { "keepAliveAckTimeout": "5000", "keepAliveInterval": "30000" }, "logTraffic": "false", "port": "9999", "role": "server" }, "protocol": "snmp" } }, "queueStats": {} }
```

Using jq option

```bash
jq -c . input.txt
```

# awk patterns

```bash
awk '
    /pattern/ {
        # custom block to print the line
    }
    !/pattern/ {
        # else do other things
    }
'
```

2. if-next-else

```bash
awk '/pattern/ {do this;next} {else this block will be run}
awk '/orange/{gsub("cow", "cow~")} 1' "$file"
```