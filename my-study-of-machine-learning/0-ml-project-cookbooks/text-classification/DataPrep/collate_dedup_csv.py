# # Basic
# python collate_dedup_csv.py \
#     - -indir / path/to/csv_dir \
#     - -pattern "*.csv" \
#     - -out / path/to/combined.csv \
#     - -text-col TEXT \
#     - -label-col CATEGORY

# # If your CSVs have no header (assumes first two columns are label,text)
# python collate_dedup_csv.py \
#     - -indir / path/to/csv_dir \
#     - -no-header \
#     - -out / path/to/combined.csv

# # Memory-friendly streaming (default chunksize=200000); change delimiter/encoding if needed
# python collate_dedup_csv.py \
#     - -indir / path/to/csv_dir \
#     - -pattern "*.csv" \
#     - -out / path/to/combined.csv \
#     - -sep "," \
#     - -encoding "utf-8" \
#     - -chunksize 200000

#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import sys
import glob
import argparse
import pandas as pd

def parse_args():
    ap = argparse.ArgumentParser(description="Collate CSVs, dedup on (text,label), drop short texts (<=3), and write a single CSV.")
    ap.add_argument("--indir", required=True, help="Directory containing CSV files.")
    ap.add_argument("--pattern", default="*.csv", help="Glob pattern inside indir (default: *.csv).")
    ap.add_argument("--out", required=True, help="Output CSV path.")
    ap.add_argument("--text-col", default="text", help="Name of the text column (default: text).")
    ap.add_argument("--label-col", default="label", help="Name of the label column (default: label).")
    ap.add_argument("--no-header", action="store_true", help="Use if CSVs have no header. Will map first two columns to (label,text).")
    ap.add_argument("--sep", default=",", help="CSV delimiter (default: ',').")
    ap.add_argument("--encoding", default="utf-8", help="File encoding (default: utf-8).")
    ap.add_argument("--chunksize", type=int, default=200000, help="Rows per chunk for streaming (0 to load whole files; default 200k).")
    ap.add_argument("--lower", action="store_true", help="Lowercase text before dedup (optional).")
    return ap.parse_args()

def normalize_text(s: str, lower: bool) -> str:
    if s is None:
        return ""
    s = str(s).strip()
    s = " ".join(s.split())
    if lower:
        s = s.lower()
    return s

def main():
    args = parse_args()

    paths = sorted(glob.glob(os.path.join(args.indir, args.pattern)))
    if not paths:
        print(f"No CSV files found under {args.indir}/{args.pattern}", file=sys.stderr)
        sys.exit(1)

    out_dir = os.path.dirname(os.path.abspath(args.out))
    if out_dir and not os.path.exists(out_dir):
        os.makedirs(out_dir, exist_ok=True)

    header_written = False
    seen = set()  # global (text,label)

    def append_df(df: pd.DataFrame):
        nonlocal header_written
        mode = "a" if header_written else "w"
        df.to_csv(args.out, index=False, mode=mode, header=not header_written, encoding=args.encoding)
        header_written = True

    read_kwargs = dict(sep=args.sep, encoding=args.encoding, dtype=str, on_bad_lines="skip", engine="python")

    for p in paths:
        if args.chunksize and args.chunksize > 0:
            for chunk in pd.read_csv(p, chunksize=args.chunksize, **read_kwargs, header=None if args.no_header else "infer"):
                if args.no_header:
                    chunk = chunk.rename(columns={0: args.label_col, 1: args.text_col})
                    keep_cols = [c for c in chunk.columns if c in (args.label_col, args.text_col)]
                    chunk = chunk[keep_cols]
                else:
                    missing = [c for c in (args.label_col, args.text_col) if c not in chunk.columns]
                    if missing:
                        rename_map = {}
                        for c in chunk.columns:
                            lc = str(c).lower()
                            if lc.startswith("cat") or lc == "label":
                                rename_map[c] = args.label_col
                            if lc.startswith("text") or lc in ("remarks", "message"):
                                rename_map[c] = args.text_col
                        if rename_map:
                            chunk = chunk.rename(columns=rename_map)
                    keep_cols = [c for c in chunk.columns if c in (args.label_col, args.text_col)]
                    chunk = chunk[keep_cols]

                chunk = chunk.dropna(subset=[args.text_col, args.label_col])
                chunk[args.text_col] = chunk[args.text_col].apply(lambda s: normalize_text(s, args.lower))
                chunk = chunk[chunk[args.text_col].str.len() > 3]
                chunk = chunk.drop_duplicates(subset=[args.text_col, args.label_col])

                mask = []
                for t, l in zip(chunk[args.text_col].values, chunk[args.label_col].values):
                    key = (t, l)
                    if key in seen:
                        mask.append(False)
                    else:
                        seen.add(key)
                        mask.append(True)
                deduped = chunk[mask]

                if not deduped.empty:
                    append_df(deduped)
        else:
            df = pd.read_csv(p, **read_kwargs, header=None if args.no_header else "infer")

            if args.no_header:
                df = df.rename(columns={0: args.label_col, 1: args.text_col})
                keep_cols = [c for c in df.columns if c in (args.label_col, args.text_col)]
                df = df[keep_cols]
            else:
                missing = [c for c in (args.label_col, args.text_col) if c not in df.columns]
                if missing:
                    rename_map = {}
                    for c in df.columns:
                        lc = str(c).lower()
                        if lc.startswith("cat") or lc == "label":
                            rename_map[c] = args.label_col
                        if lc.startswith("text") or lc in ("remarks", "message"):
                            rename_map[c] = args.text_col
                    if rename_map:
                        df = df.rename(columns=rename_map)
                keep_cols = [c for c in df.columns if c in (args.label_col, args.text_col)]
                df = df[keep_cols]

            df = df.dropna(subset=[args.text_col, args.label_col])
            df[args.text_col] = df[args.text_col].apply(lambda s: normalize_text(s, args.lower))
            df = df[df[args.text_col].str.len() > 3]
            df = df.drop_duplicates(subset=[args.text_col, args.label_col])

            mask = []
            for t, l in zip(df[args.text_col].values, df[args.label_col].values):
                key = (t, l)
                if key in seen:
                    mask.append(False)
                else:
                    seen.add(key)
                    mask.append(True)
            deduped = df[mask]
            if not deduped.empty:
                append_df(deduped)

    print(f"Done. Wrote: {args.out}")

if __name__ == '__main__':
    main()
