# XGBoost Feature Engineering on Titanic-Like Dataset

## 🔍 Objective
Demonstrate how to prepare structured data for XGBoost by:
- Handling missing values
- Extracting features from timestamps
- One-hot encoding of categorical data
- Training XGBoost model
- Displaying feature importance

## 📁 Files
- `xgboost_feature_engineering_titanic.ipynb`: Main Colab notebook with feature engineering and XGBoost pipeline
- `titanic_raw.csv`: Sample dataset (can be replaced with a larger CSV)
- `README.md`: This file

## 🚀 Instructions

1. Open [Google Colab](https://colab.research.google.com).
2. Upload both `xgboost_feature_engineering_titanic.ipynb` and your dataset (e.g. `titanic_raw.csv`) to the Colab session.
3. Update the notebook code if you're using a different CSV filename or have additional columns.
4. Run all cells.

## 📈 For Larger Datasets

If your CSV has **tens or hundreds of thousands of rows**:
- Use `pd.read_csv("your_file.csv", chunksize=10000)` to load in chunks
- Consider using `DMatrix` from `xgboost` for memory efficiency
- Profile memory usage with `%memit` or `memory_profiler`
- You can replace the current small dataset with your own

## 🧪 Note

The sample dataset simulates common enterprise data types:
- Oracle `DATE` and `TIMESTAMP`
- Names and country fields
- Missing values
