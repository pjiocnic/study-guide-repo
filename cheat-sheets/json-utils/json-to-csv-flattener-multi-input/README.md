# JSON to CSV Flattener

Reads all `.json` files from `jsons/` directory and flattens them into a single CSV file.

## Features
- Supports nested objects and arrays (`[*]`)
- Multiple input JSONs ➜ one `output.csv`
- First row is header, rest are data
- Customize headers via `flat-fields.properties`

## Usage
```bash
chmod +x run.sh
./run.sh
```
