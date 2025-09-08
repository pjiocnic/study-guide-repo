
```py
import pandas as pd
df = pd.read_json('data.json.gz', compression='gzip')
print(df)
```