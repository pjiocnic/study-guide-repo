# Feasability Studt - TFIDF + XGBoost on Laptop

### What really matters

* **nnz (number of non-zeros)**: TF-IDF is super sparse. If each doc averages, say, 40–100 non-zero terms, then:

  * `nnz ≈ 142,252 * 70 ≈ 9.96M`
  * Memory for CSR in float32 is roughly `~ nnz*(4 bytes values + 4 bytes col_idx) + (n_rows+1)*4` → about **~80 MB** for the matrix itself (very manageable).
  * XGBoost (gpu_hist) converts to an internal ELLPACK; VRAM usage is roughly proportional to **nnz × bytes_per_entry** (plus gradients, histograms, model, etc.). With 8–12 GB VRAM, **< ~30–50M nnz** is often OK; beyond that you’ll start to sweat.
* **Tree vs linear booster**:

  * **`gbtree`/`gpu_hist`**: works, but 201,668 features make histograms big; VRAM pressure grows with feature count and bins.
  * **`gblinear`**: much better fit for **huge, sparse, high-dimensional text**; memory scales with nnz and the weight vector (≈ #features). Trains fast and often performs similarly to Logistic Regression/SVM on TF-IDF.

### Quick checks you can run

```python
X = tfidf_matrix  # output of TfidfVectorizer
X.dtype = np.float32  # lowers memory without much loss
print("shape:", X.shape)
print("nnz:", X.nnz)
print("avg nonzeros/doc:", X.nnz / X.shape[0])
```

### If VRAM is tight or training is slow

1. **Try a linear booster first**

   ```python
   xgb.XGBClassifier(
       booster="gblinear",
       reg_alpha=1.0, reg_lambda=1.0,
       n_estimators=200,  # acts like SGD steps for gblinear
       learning_rate=0.1,
       n_jobs=-1  # CPU parallelism; gblinear is CPU-only
   )
   ```

   (For text TF-IDF, gblinear/logistic regression style models are strong baselines.)

2. **Prune the vocabulary**

   * `min_df=5` (or higher), `max_df=0.5`, and/or `max_features=50_000` (or 100k).
   * These usually **help accuracy** and slash memory/time.

   ```python
   TfidfVectorizer(
       stop_words="english",
       min_df=5,
       max_df=0.5,
       max_features=100_000,
       dtype=np.float32,
       sublinear_tf=True
   )
   ```

3. **Feature selection**

   * Keep the top 50k–100k terms by χ² or mutual information (fit on train only).

4. **Low-rank projection (great for trees)**

   * `TruncatedSVD(n_components=300–1000)` on TF-IDF → dense but small; then use `gbtree` (CPU `hist` or GPU `gpu_hist`).

   ```python
   from sklearn.decomposition import TruncatedSVD
   svd = TruncatedSVD(n_components=512, random_state=0)
   X_svd = svd.fit_transform(X_train)
   ```

5. **If you still want `gbtree` on GPU**

   * Use: `tree_method="gpu_hist"`, `max_bin=64–128`, **small depth** (`max_depth=4–6`), `colsample_bytree=0.2–0.5`, `subsample=0.7–0.9`, and `reg_alpha` to encourage sparsity.

   ```python
   xgb.XGBClassifier(
       tree_method="gpu_hist",
       max_depth=6,
       max_bin=128,
       subsample=0.8,
       colsample_bytree=0.3,
       reg_alpha=1.0,
       reg_lambda=1.0,
       n_estimators=500,
       learning_rate=0.05
   )
   ```

### Bottom line

* **142,252 × 201,668** isn’t automatically “too big.” If your **nnz is under ~30–50M**, an **8–12 GB** NVIDIA GPU laptop can often train with `gpu_hist`, though you may need conservative params.
* For text TF-IDF at this scale, **`gblinear` (or logistic regression / linear SVM)** is usually the most resource-friendly and competitive.
* If you want trees, **reduce features** (min_df/max_df/max_features or SVD) and tune for memory.

If you want, paste your `nnz` and GPU VRAM size and I’ll give a more precise go/no-go and parameter set.
