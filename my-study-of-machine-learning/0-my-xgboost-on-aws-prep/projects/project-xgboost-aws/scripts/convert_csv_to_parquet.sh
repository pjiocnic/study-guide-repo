#!/bin/bash

INPUT_DIR="../data/cleaned"
OUTPUT_DIR="../data/parquet"

echo "Converting CSV files in $INPUT_DIR to Parquet format in $OUTPUT_DIR..."
mkdir -p "$OUTPUT_DIR"
python3 ../src/csv_to_parquet.py --input_dir "$INPUT_DIR" --output_dir "$OUTPUT_DIR"
