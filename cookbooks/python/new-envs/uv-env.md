
# 🐍 `uv` Python Environment Cheat Sheet

## 🔹 Installation

```bash
# Install uv (Linux/macOS)
curl -LsSf https://astral.sh/uv/install.sh | sh

# On Windows (PowerShell)
irm https://astral.sh/uv/install.ps1 | iex
```

Check version:

```bash
uv --version
```

---

## 🔹 Create a Virtual Environment

```bash
# Create a new venv in `.venv/`
uv venv

# Create a venv with specific Python version
uv venv --python 3.11

# Create in a custom folder
uv venv .myenv
```

Activate:

```bash
source .venv/bin/activate   # Linux/macOS
.venv\Scripts\activate      # Windows PowerShell
```

Deactivate:

```bash
deactivate
```

---

## 🔹 Installing Packages

```bash
# Install a package into the active environment
uv pip install requests

# Install multiple packages
uv pip install pandas numpy scikit-learn

# Install a specific version
uv pip install "django==4.2"

# Install from requirements.txt
uv pip install -r requirements.txt
```

---

## 🔹 Exporting / Freezing Dependencies

```bash
# Save all installed packages to requirements.txt
uv pip freeze > requirements.txt
```

---

## 🔹 Upgrading Packages

```bash
# Upgrade a specific package
uv pip install --upgrade requests

# Upgrade everything
uv pip upgrade
```

---

## 🔹 Removing Packages

```bash
uv pip uninstall requests
```

---

## 🔹 Running Code Inside Env

```bash
uv run python app.py
uv run pytest
```

This runs the command with the correct environment automatically.

---

## 🔹 Project Setup with `pyproject.toml`

If you’re managing a project:

```bash
# Initialize pyproject.toml
uv init

# Add dependencies
uv add fastapi uvicorn

# Add dev dependencies
uv add --dev black pytest
```

---

## 🔹 Useful Shortcuts

```bash
uv python list         # List available Python versions
uv python install 3.11 # Install Python 3.11
uv cache clean         # Clean uv cache
```

---

✅ **Summary**

* `uv venv` → create environment
* `uv pip install ...` → install dependencies
* `uv pip freeze` → save dependencies
* `uv run` → run commands inside the env
* `uv add` → manage deps in `pyproject.toml`

---

Do you want me to also include a **side-by-side comparison with `pip + venv` commands** so you can see how `uv` simplifies things?
