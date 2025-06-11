Here’s a complete **Streamlit app** that detects available GPUs, their memory usage, and display details using both **PyTorch** and **`nvidia-smi`** (if available). It also includes a fallback if no GPU is found.

---

### ✅ `gpu_monitor_app.py`
```python
import streamlit as st
import subprocess
import torch

st.set_page_config(page_title="GPU Monitor", layout="wide")
st.title("🖥️ GPU & Memory Monitor")

# PyTorch GPU check
st.header("🔍 PyTorch CUDA Info")

if torch.cuda.is_available():
    st.success("CUDA is available!")
    num_gpus = torch.cuda.device_count()
    st.write(f"Number of GPUs: **{num_gpus}**")

    for i in range(num_gpus):
        name = torch.cuda.get_device_name(i)
        mem_allocated = torch.cuda.memory_allocated(i) / 1024**3
        mem_reserved = torch.cuda.memory_reserved(i) / 1024**3

        st.subheader(f"GPU {i}: {name}")
        st.write(f"- Memory Allocated: **{mem_allocated:.2f} GB**")
        st.write(f"- Memory Reserved: **{mem_reserved:.2f} GB**")
else:
    st.warning("CUDA is not available. No GPU detected by PyTorch.")

# NVIDIA SMI GPU info
st.header("📊 Detailed NVIDIA-SMI Info")

def get_nvidia_smi_info():
    try:
        output = subprocess.check_output(
            ['nvidia-smi', '--query-gpu=name,memory.total,memory.used,memory.free', '--format=csv,nounits,noheader'],
            encoding='utf-8'
        )
        gpus = output.strip().split('\n')
        for idx, gpu in enumerate(gpus):
            name, total, used, free = [x.strip() for x in gpu.split(',')]
            st.subheader(f"GPU {idx}: {name}")
            st.write(f"- Total Memory: **{int(total)/1024:.2f} GB**")
            st.write(f"- Used Memory: **{int(used)/1024:.2f} GB**")
            st.write(f"- Free Memory: **{int(free)/1024:.2f} GB**")
    except FileNotFoundError:
        st.error("❌ `nvidia-smi` not found. Make sure NVIDIA drivers are installed.")
    except Exception as e:
        st.error(f"Error while fetching GPU info: {e}")

get_nvidia_smi_info()
```

---

### ▶️ How to Run

1. Save the file as `gpu_monitor_app.py`
2. Install Streamlit if you haven’t:
   ```bash
   pip install streamlit
   ```
3. Run the app:
   ```bash
   streamlit run gpu_monitor_app.py
   ```

---

Would you like this enhanced with **charts**, **real-time refresh**, or **GPUtil-based fallback** for systems without PyTorch?

---
To check if you have a **GPU** in your **notebook (laptop)**, you can use the following methods depending on your operating system:

---

### ✅ **Windows**
1. **Task Manager**
   - Press `Ctrl + Shift + Esc` or right-click the taskbar → Task Manager.
   - Go to the **Performance** tab.
   - Look for **GPU 0**, **GPU 1**, etc., on the sidebar.
   - You'll see the GPU name and usage stats.

2. **Device Manager**
   - Press `Win + X` → choose **Device Manager**.
   - Expand **Display adapters**.
   - You'll see entries like:
     - `Intel UHD Graphics` (integrated)
     - `NVIDIA GeForce GTX/RTX` or `AMD Radeon` (dedicated GPU)

3. **Run `dxdiag`**
   - Press `Win + R` → type `dxdiag` → hit Enter.
   - In the **Display** tab(s), you’ll see GPU info.

---

### ✅ **macOS**
1. **About This Mac**
   - Click the Apple logo () → **About This Mac**.
   - Under **Graphics**, it will list your GPU(s).

2. **Activity Monitor**
   - Open **Activity Monitor** → Window → **GPU History** (if available).

---

### ✅ **Linux**
Open a terminal and run:

```bash
lspci | grep -i --color 'vga\|3d\|2d'
```

Or, for NVIDIA:

```bash
nvidia-smi
```

(If `nvidia-smi` works, you have an NVIDIA GPU and driver installed.)

---

### ✅ **In Python (for ML/DL work)**

If you're using Python, check via:

```python
import torch
print(torch.cuda.is_available())
print(torch.cuda.get_device_name(0) if torch.cuda.is_available() else "No GPU")
```

Or for TensorFlow:

```python
import tensorflow as tf
print(tf.config.list_physical_devices('GPU'))
```

---

Let me know your OS or if you're checking this for a specific purpose (like deep learning), and I can tailor it more.