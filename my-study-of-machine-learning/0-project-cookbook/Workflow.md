1. Preparing your data: Preprocess your data by encoding categorical variables, scaling numerical variables, and handling any missing values or outliers

```py
import pandas as pd
from sklearn.preprocessing import StandardScaler
# Scale numerical features
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.transform(X_test)
```

2. Training your model

```py
import xgboost as xgb
# Create XGBoost classifier
model = xgb.XGBClassifier(n_estimators=100, max_depth=3, learning_rate=0.1)
# Train the model
model.fit(X_train_scaled, y_train)
```

3. Evaluating and tuning your model

```py
from sklearn.metrics import accuracy_score
# Make predictions on test set
y_pred = model.predict(X_test_scaled)
# Evaluate the model
accuracy = accuracy_score(y_test, y_pred)
print("Accuracy:", accuracy)
```


4. Hyperparameter tuning

```py
from sklearn.model_selection import GridSearchCV
# Define hyperparameter grid
param_grid = {
    'n_estimators': [50, 100, 200],
    'max_depth': [3, 5, 7],
    'learning_rate': [0.01, 0.1, 0.2]
}
# Perform grid search
grid_search = GridSearchCV(estimator=model, param_grid=param_grid, cv=5, scoring='accuracy')
grid_search.fit(X_train_scaled, y_train)
# Get the best hyperparameters
best_params = grid_search.best_params_
print("Best hyperparameters:", best_params)
```

## References

1. https://johnvastola.medium.com/xgboost-the-secret-weapon-behind-machine-learning-success-b9dafe36fa7e