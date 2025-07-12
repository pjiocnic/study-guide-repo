
# Create a summary table using pandas DataFrame

```py
import pandas as pd

# Create a summary table using pandas DataFrame
summary_table = pd.DataFrame({
    # Get the data type of each column
    'Dtype': df.dtypes,

    # Count non-null (non-missing) values in each column
    'Count': df.count(),

    # Count the number of unique values in each column
    'Unique': df.nunique(),

    # Count how many values are null (missing) in each column
    'Null Values': df.isnull().sum(),

    # Count how many values are exactly zero in each column
    'Zero Values': [(df[col] == 0).sum() for col in df.columns],

    # For each column, get the most frequent value (mode) and its count
    # If a column has no mode (e.g., all values are NaN), show "N/A"
    'Frequent Value (and number of occurences)': [
        f"{df[col].mode().iloc[0]} ({df[col].value_counts().max()})"
        if not df[col].mode().empty else "N/A"
        for col in df.columns
    ]
})

# Display the summary table
summary_table
```