import pandas as pd
import os
import glob

def convert_all_csv_to_parquet(input_dir, output_dir):
    os.makedirs(output_dir, exist_ok=True)
    csv_files = glob.glob(os.path.join(input_dir, "*.csv"))
    for csv_file in csv_files:
        df = pd.read_csv(csv_file)
        filename = os.path.splitext(os.path.basename(csv_file))[0]
        parquet_file = os.path.join(output_dir, f"{filename}.parquet")
        df.to_parquet(parquet_file, index=False)
        print(f"Converted {csv_file} to {parquet_file}")

if __name__ == "__main__":
    import argparse
    parser = argparse.ArgumentParser(description="Convert CSV files to Parquet format.")
    parser.add_argument("--input_dir", type=str, required=True, help="Directory with input CSV files")
    parser.add_argument("--output_dir", type=str, required=True, help="Directory to store Parquet files")
    args = parser.parse_args()
    convert_all_csv_to_parquet(args.input_dir, args.output_dir)
