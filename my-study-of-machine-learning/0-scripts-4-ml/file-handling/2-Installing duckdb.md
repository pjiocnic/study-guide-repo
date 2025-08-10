You can get **DuckDB** in two main ways — either as a **standalone CLI tool** or as a **Python library** (or both).

---

## **1️⃣ Install DuckDB CLI**

If you want to run commands directly in your terminal:

**On macOS (Homebrew):**

```bash
brew install duckdb
```

**On Linux (Debian/Ubuntu):**

```bash
sudo apt update
sudo apt install duckdb
```

> If your distro doesn’t have a recent version, you can download binaries from [https://duckdb.org/docs/installation/cli](https://duckdb.org/docs/installation/cli)

**On Windows (Chocolatey):**

```powershell
choco install duckdb
```

Or download the `.zip` from [DuckDB releases](https://github.com/duckdb/duckdb/releases) and put it in your PATH.

---

## **2️⃣ Install DuckDB in Python**

If you want to use it in scripts or Jupyter/Colab:

```bash
pip install duckdb
```

Example:

```python
import duckdb

# Convert CSV to Parquet in Python
duckdb.query("""
    COPY (SELECT * FROM read_csv_auto('input.csv'))
    TO 'output.parquet' (FORMAT PARQUET);
""")
```

---

## **3️⃣ Verify Installation**

```bash
duckdb --version
```

or in Python:

```python
import duckdb
print(duckdb.__version__)
```

---