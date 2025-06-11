## 🧠 Machine Learning → 🔧 Model Evaluation & Hyperparameter Tuning

### 1️⃣ **Model Evaluation Foundations**
- **Train/Test Split**
- **Cross-Validation (CV)**
  - k-Fold CV
  - Stratified k-Fold CV
  - Leave-One-Out CV (LOOCV)
  - Grouped CV / TimeSeriesSplit
- **Bias-Variance Tradeoff**
- **Overfitting vs Underfitting**
- **Learning Curves**
- **Validation Curves**
- **Performance Metrics**
  - Classification: Accuracy, Precision, Recall, F1-Score, ROC-AUC, Log Loss
  - Regression: MAE, MSE, RMSE, R², Adjusted R²

---

### 2️⃣ **Hyperparameter Tuning Techniques**
- **Manual Search (Grid Search by hand)**
- **Grid Search**
  - `GridSearchCV` (Scikit-learn)
  - Nested Cross-Validation
- **Random Search**
  - `RandomizedSearchCV`
- **Bayesian Optimization**
  - Hyperopt, Optuna, Scikit-Optimize
- **Gradient-based Optimization (e.g., HyperGradient)**
- **Evolutionary Algorithms**
  - Genetic Algorithm for Hyperparameter Search
- **Successive Halving / Hyperband**
- **Automated Machine Learning (AutoML)**
  - Auto-sklearn, TPOT, H2O AutoML

---

### 3️⃣ **Advanced Tuning & Evaluation**
- **Custom Scorers in CV**
- **Multi-metric Evaluation**
- **Parallel/Distributed Tuning (e.g., `n_jobs`, Ray Tune, Dask)**
- **Early Stopping (especially in boosting/neural networks)**
- **Ensembling During CV**
  - Cross-validated stacking
- **Cross-validation with Time Series**
- **Nested Cross-Validation for Model Selection**
- **Pipeline Integration for CV**
  - `Pipeline + GridSearchCV`

---

### 4️⃣ **Model Robustness & Reliability**
- **Cross-Validation + Confidence Intervals**
- **Model Stability Checks**
- **Out-of-Fold Predictions**
- **Permutation Importance vs. Cross-Validation Score Drop**
- **Reproducibility and Random Seeds**
- **Data Leakage Prevention During CV**
- **Variance Across Splits**

---

### 🔁 Common Hyperparameters Tuned by GridSearchCV
| Model | Common Hyperparameters |
|-------|-------------------------|
| `LogisticRegression` | `C`, `penalty`, `solver` |
| `RandomForestClassifier` | `n_estimators`, `max_depth`, `max_features`, `min_samples_split` |
| `SVC` | `C`, `gamma`, `kernel` |
| `GradientBoostingClassifier` | `learning_rate`, `n_estimators`, `subsample`, `max_depth` |
| `KNeighborsClassifier` | `n_neighbors`, `weights`, `metric` |
| `XGBoost` / `LightGBM` | `eta`, `max_depth`, `min_child_weight`, `subsample`, `colsample_bytree` |

---

Would you like me to convert this list into a **mind map-style diagram** or a **markdown file** for reference?