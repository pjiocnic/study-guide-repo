#!/usr/bin/env python3
import argparse, sys, os, csv
import pandas as pd

def load_table(path: str, sheet: str = None) -> pd.DataFrame:
    ext = os.path.splitext(path)[1].lower()
    if ext in [".xlsx", ".xls"]:
        return pd.read_excel(path, sheet_name=sheet)
    elif ext in [".csv", ".txt"]:
        return pd.read_csv(path)
    else:
        raise SystemExit(f"Unsupported input extension: {ext}. Use .xlsx, .xls, or .csv")

def main():
    ap = argparse.ArgumentParser(description="Prepare text classification dataset (text,label) from spreadsheet")
    ap.add_argument("--input", required=True, help="Path to Excel/CSV input (must contain SEQ_NBR, CATEGORY, REMARKS_1, REMARKS_2)")
    ap.add_argument("--sheet", default=None, help="Excel sheet name (if input is .xlsx/.xls)")
    ap.add_argument("--output", default="text_label.csv", help="Output CSV path (text,label)")
    ap.add_argument("--mapping_out", default=None, help="Optional CSV to save mapping of (text,label) to list of SEQ_NBRs")
    ap.add_argument("--min_chars", type=int, default=3, help="Drop rows where TEXT length < min_chars after strip")
    args = ap.parse_args()

    df = load_table(args.input, args.sheet)

    required = {"SEQ_NBR","CATEGORY","REMARKS_1","REMARKS_2"}
    missing = required - set(df.columns)
    if missing:
        raise SystemExit(f"Missing required columns: {sorted(missing)}")

    # TEXT = REMARKS_1 if present else REMARKS_2
    df["TEXT"] = df["REMARKS_1"].where(df["REMARKS_1"].notna(), df["REMARKS_2"])

    # Strip and basic hygiene
    df["TEXT"] = df["TEXT"].astype(str).str.strip()
    # Drop rows where TEXT is empty or too short
    df = df[df["TEXT"].str.len() >= args.min_chars]

    # Deduplicate on CATEGORY + TEXT
    # Keep first occurrence; optional mapping retains original SEQ_NBR traceability
    df_dedup = df.drop_duplicates(subset=["CATEGORY","TEXT"]).copy()

    # Prepare final tabular dataset
    out_df = df_dedup.rename(columns={"CATEGORY":"label","TEXT":"text"})[["text","label"]]

    # Save main CSV — ensure commas/quotes are handled
    # QUOTE_MINIMAL will quote fields that contain commas/newlines automatically
    out_df.to_csv(args.output, index=False, encoding="utf-8", quoting=csv.QUOTE_MINIMAL, lineterminator="\n")
    print(f"Wrote dataset: {args.output}  (rows={len(out_df)})")

    # Optional: mapping from (text,label) -> list of SEQ_NBRs that got collapsed
    if args.mapping_out:
        m = (df.groupby(["CATEGORY","TEXT"])["SEQ_NBR"]
               .apply(list)
               .reset_index()
               .rename(columns={"CATEGORY":"label","TEXT":"text"}))
        m.to_csv(args.mapping_out, index=False, encoding="utf-8", quoting=csv.QUOTE_MINIMAL, lineterminator="\n")
        print(f"Wrote mapping: {args.mapping_out}  (rows={len(m)})")

if __name__ == "__main__":
    main()
