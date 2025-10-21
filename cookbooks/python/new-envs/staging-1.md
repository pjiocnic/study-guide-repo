Got it 👍 You can wipe and rebuild your `nlp-env` conda environment from scratch with a script. Here’s a clean way that works on Windows (PowerShell) or Linux/Mac (bash):

---

## 📄 Script: Recreate `nlp-env`

### Windows (PowerShell / Anaconda Prompt)

```powershell
# Remove old env if it exists
conda env remove -n nlp-env -y

# Recreate env with Python 3.10
conda create -n nlp-env python=3.10 -y

# Activate it
conda activate nlp-env

# Install core libraries via conda for speed
conda install -y numpy=1.26.4 scipy=1.10.1

# Install gensim via pip (latest compatible)
pip install gensim==4.3.2

# Add Jupyter kernel support
pip install ipykernel

# Register kernel so Jupyter/VS Code can see it
python -m ipykernel install --user --name nlp-env --display-name "Python (nlp-env)"
```

---

### Linux / Mac (bash)

```bash
# Remove old env if exists
conda env remove -n nlp-env -y

# Recreate env
conda create -n nlp-env python=3.10 -y
conda activate nlp-env

# Install dependencies
conda install -y numpy=1.26.4 scipy=1.10.1
pip install gensim==4.3.2 ipykernel

# Register kernel
python -m ipykernel install --user --name nlp-env --display-name "Python (nlp-env)"
```

---

## ✅ Verification

After running the script, test inside the new environment:

```bash
python -c "import gensim, scipy, numpy; print(gensim.__version__, scipy.__version__, numpy.__version__)"
```

Expected:

```
4.3.2 1.10.1 1.26.4
```

And in Jupyter, select **Python (nlp-env)** as your kernel.

---

👉 Do you want me to also extend this script to include **pandas, matplotlib, scikit-learn** (so the env is notebook-ready for ML/EDA work), or keep it minimal with just gensim + scipy + numpy?



