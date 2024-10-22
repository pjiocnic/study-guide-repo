
###  Use awk to extract the matching lines and the following line

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

### Use Python to extract the matching lines and the following line

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

### Concatenate lines (concatenate_lines.sh) using bash

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

### Concatenate lines using Python

```py
import sys

def concatenate_lines(filename):
    output_file = "concatenated_output.txt"

    # Open the input file and the output file
    with open(filename, 'r') as infile, open(output_file, 'w') as outfile:
        lines = infile.readlines()

        i = 0
        while i < len(lines):
            # Check if the line starts with 'S "  AORL-1'
            if lines[i].startswith('S "  AORL-1'):
                # Extract the part after 'S "' (which is after the first 3 characters)
                line1 = lines[i][3:].strip()

                # Check if the next line exists
                if i + 1 < len(lines):
                    # Extract the part after 'S "' from the next line (skipping first 3 characters)
                    line2 = lines[i + 1][3:].strip()

                    # Write the concatenated result to the output file
                    outfile.write(f"{line1}{line2}\n")

                # Skip the next line as it is already processed
                i += 1
            # Move to the next line
            i += 1

    print(f"Concatenation complete. Output saved to {output_file}")

# Check if filename is provided
if len(sys.argv) < 2:
    print("Usage: python script.py filename")
    sys.exit(1)

# Pass the filename as an argument to the function
filename = sys.argv[1]
concatenate_lines(filename)
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

Equivalent python code:

```py
import sys

def process_file(filename):
    output_file = "cleaned_concatenated_output.txt"

    # Open the input file and the output file
    with open(filename, 'r') as infile, open(output_file, 'w') as outfile:
        lines = infile.readlines()

        i = 0
        while i < len(lines):
            # Check if the line starts with 'S "  AORL-1'
            if lines[i].startswith('S "  AORL-1'):
                # Extract the part after 'S "  AORL-' (which is after the first 10 characters)
                line1 = lines[i][10:].strip()

                # Check if the next line exists
                if i + 1 < len(lines):
                    # Extract the part after 'S "' from the next line (skipping first 3 characters)
                    line2 = lines[i + 1][3:].strip()

                    # Write the concatenated result to the output file
                    outfile.write(f"{line1}{line2}\n")

                # Skip the next line as it is already processed
                i += 1
            # Move to the next line
            i += 1

    print(f"Processing complete. Output saved to {output_file}")

# Check if filename is provided
if len(sys.argv) < 2:
    print("Usage: python script.py filename")
    sys.exit(1)

# Pass the filename as an argument to the function
filename = sys.argv[1]
process_file(filename)
```