
```bash
#!/bin/bash

# Check if filename is passed as an argument
if [ $# -eq 0 ]; then
  echo "Usage: $0 filename"
  exit 1
fi

# Filename is passed as the first argument
filename=$1

# Use awk to extract the matching lines and the following line
awk '/^S " AORL-1/ {print; getline; print}' "$filename"
```

```bash
./extract_lines.sh your_file.txt
```

### Using Python

```py
# Function to extract lines starting with "S " AORL-1 and the immediate next line
def extract_lines(filename):
    extracted_lines = []

    with open(filename, 'r') as file:
        lines = file.readlines()

        for i, line in enumerate(lines):
            # Check if the line starts with 'S " AORL-1'
            if line.startswith('S " AORL-1'):
                # Add the current line and the next line (if exists) to the list
                extracted_lines.append(line.strip())
                if i + 1 < len(lines):
                    extracted_lines.append(lines[i + 1].strip())

    return extracted_lines

# Usage example
filename = 'your_file.txt'  # Replace with your file path
result = extract_lines(filename)

# Print the result
for line in result:
    print(line)

```

./concatenate_lines.sh

```bash
#!/bin/bash

# Check if filename is passed as an argument
if [ $# -eq 0 ]; then
  echo "Usage: $0 filename"
  exit 1
fi

# Filename is passed as the first argument
filename=$1

# Output file where the concatenated lines will be saved
output_file="concatenated_output.txt"

# Use awk to process the file
awk '
  /^S "  AORL-1/ {
    # Extract the part after S "
    line1 = substr($0, 4)
    getline
    # Remove S " from the next line and concatenate
    line2 = substr($0, 4)
    # Print the concatenated result to the output file
    print line1 line2
}' "$filename" > "$output_file"

echo "Concatenation complete. Output saved to $output_file"
```

- Final cleansing

```bash
#!/bin/bash

# Check if filename is passed as an argument
if [ $# -eq 0 ]; then
  echo "Usage: $0 filename"
  exit 1
fi

# Filename is passed as the first argument
filename=$1

# Output file where the cleaned and concatenated lines will be saved
output_file="cleaned_concatenated_output.txt"

# Use awk to process the file
awk '
  /^S "  AORL-1/ {
    # Extract the part after S "  AORL- and just keep the numeric part onwards
    line1 = substr($0, 11)  # Skip "S "  AORL-" (first 10 characters)
    getline
    # Remove S " from the next line and concatenate
    line2 = substr($0, 4)   # Skip "S " (first 3 characters)
    # Print the concatenated result to the output file
    print line1 line2
}' "$filename" > "$output_file"

echo "Processing complete. Output saved to $output_file"
```