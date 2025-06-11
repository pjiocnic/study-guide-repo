
## 🧪 Step-by-Step: Conda + PyTorch + Jupyter (GPU Ready)

---

### ✅ 1. Create a New Conda Environment

```bash
conda create -n torch-gpu python=3.10
conda activate torch-gpu
```

---

### ✅ 2. Install GPU-Compatible PyTorch

Use the official PyTorch install command for CUDA 12.1:

```bash
pip install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cu121
```

> Even in a Conda env, we use `pip` here because it's the cleanest way to get the exact CUDA build.

---

### ✅ 3. Install Jupyter and ipykernel

```bash
conda install jupyter ipykernel
```

Then register this environment as a Jupyter kernel:
```bash
python -m ipykernel install --user --name=torch-gpu --display-name "Python (torch-gpu)"
```

---

### ✅ 4. Select the Kernel in VS Code

1. Open VS Code.
2. Launch your `.ipynb` file.
3. Click the **kernel selector** (top right of notebook UI).
4. Pick `"Python (torch-gpu)"`.

You're now using your Conda environment with full GPU support inside Jupyter.

---

### ✅ 5. Optional: `environment.yml` for Sharing/Rebuilding

If you want to export the setup:
```bash
conda env export --name torch-gpu > environment.yml
```

Example `environment.yml`:

```yaml
name: torch-gpu
channels:
  - defaults
  - conda-forge
dependencies:
  - python=3.10
  - pip
  - jupyter
  - ipykernel
  - pip:
    - torch==2.2.2+cu121
    - torchvision==0.17.2+cu121
    - torchaudio==2.2.2+cu121
    - --index-url https://download.pytorch.org/whl/cu121
```

Then to recreate:
```bash
conda env create -f environment.yml
```

---

### ✅ 6. Test It in Notebook

```python
import torch
print(torch.cuda.is_available())
print(torch.cuda.get_device_name(0) if torch.cuda.is_available() else "No GPU")
```

Should return `True` and your **RTX A2000 Ada** name.

---

Would you like me to generate a ready-to-go `environment.yml` and folder layout for you too?