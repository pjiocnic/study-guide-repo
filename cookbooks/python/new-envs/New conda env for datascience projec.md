## Setting a new conda env for `juypter notebook`

---

### Create 📄 `environment.yml`

```yaml
name: nlp-env
channels:
  - defaults
  - conda-forge
dependencies:
  - python=3.10
  - pip
  # Core numeric stack
  - numpy=1.26.4
  - scipy=1.10.1
  - pandas
  - scikit-learn
  - matplotlib
  # XGBoost (via conda-forge to get prebuilt binaries)
  - xgboost
  # Install extras via pip
  - pip:
      - gensim==4.3.2
      - ipykernel

```

---

### 🔹 How to use it

1. Save the file above as `environment.yml`.
2. Run in Anaconda Prompt (or terminal):

   ```bash
   conda env create -f environment.yml
   conda activate nlp-env
   ```
3. In VS Code → Command Palette → **Python: Select Interpreter** → pick `nlp-env`.

---

### 🔹 Verify inside the new env

```bash
python -c "import gensim, scipy, numpy; print(gensim.__version__, scipy.__version__, numpy.__version__)"
```

Expected output:

```
4.3.2 1.10.1 1.26.4
```

## Launch jupyter notebook
`jupyter notebook` or `jupyter lab`

#### additional spacy download
`python -m spacy download en_core_web_sm`