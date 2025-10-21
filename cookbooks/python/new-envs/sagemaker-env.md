Great question—there are a few clean, repeatable ways to get your pinned stack onto SageMaker. Pick the path that matches what you’re doing (Studio notebooks vs. training/processing jobs, with/without internet).

# 1) SageMaker Studio / Studio Classic (interactive notebooks)

Use `%pip` (or `%conda`) **inside** the running kernel. This is the simplest for exploration.

```python
# in a Studio notebook cell
%pip install -q \
  numpy==1.26.4 scipy==1.11.4 pandas==2.1.4 \
  matplotlib==3.8.4 seaborn==0.13.2 plotly==5.24.1 \
  scikit-learn==1.4.2 gensim==4.3.2 xgboost==2.1.1 \
  nltk==3.9.1 \
  transformers==4.44.2 datasets==3.0.2 \
  typing_extensions==4.12.2
```

Notes:

* Studio explicitly supports `%pip` and `%conda` from notebooks and terminal; if one doesn’t work on a given image, use the other. ([AWS Documentation][1])

# 2) Training jobs (Script/Estimator) – install with `requirements.txt`

For **PyTorch / TensorFlow / Scikit-learn / Hugging Face** Estimators, put `requirements.txt` next to your `entry_point` and pass the folder as `source_dir`. The container will `pip install -r requirements.txt` at job start.

```python
from sagemaker.pytorch import PyTorch

estimator = PyTorch(
    entry_point="train.py",
    source_dir="src",                # contains train.py AND requirements.txt
    role=role,
    instance_type="ml.g5.2xlarge",
    framework_version="2.6",         # or newer that your region supports
    py_version="py311",
    hyperparameters={"epochs": 3},
)
estimator.fit(inputs={"train": s3_uri})
```

`src/requirements.txt` example (pin to what you used locally):

```
numpy==1.26.4
scipy==1.11.4
pandas==2.1.4
scikit-learn==1.4.2
gensim==4.3.2
xgboost==2.1.1
nltk==3.9.1
matplotlib==3.8.4
seaborn==0.13.2
plotly==5.24.1
transformers==4.44.2
datasets==3.0.2
typing_extensions==4.12.2
```

This pattern (requirements in `source_dir`) is supported across SDK framework estimators and processors. ([SageMaker Documentation][2])

# 3) Processing jobs (feature prep, TF-IDF, evaluation)

With `ScriptProcessor` or `FrameworkProcessor`, add `requirements.txt` in `source_dir`, then install at runtime (SDK supports it), or do a manual install at the top of your script.

**A) SDK-supported (`requirements.txt` in `source_dir`)**

```python
from sagemaker.processing import FrameworkProcessor
from sagemaker.sklearn import SKLearnProcessor

processor = SKLearnProcessor(
    framework_version="1.4-1",
    role=role,
    instance_type="ml.m5.2xlarge",
    instance_count=1,
    base_job_name="tfidf-prep",
)

processor.run(
    code="process.py",        # in ./code
    source_dir="code",        # contains process.py and requirements.txt
    inputs=[], outputs=[]
)
```

**B) Manual install inside `process.py` (handy if you need more control)**

```python
import subprocess, sys
subprocess.check_call([sys.executable, "-m", "pip", "install", "-r", "requirements.txt"])
# ...rest of your script...
```

Both approaches are commonly used. ([AWS Documentation][3])

# 4) Pick the right container (CUDA already baked in)

Use AWS **Deep Learning Containers (DLCs)** or **Hugging Face DLCs** with the CUDA stack preinstalled. That way, you don’t manage CUDA drivers yourself—your pip installs are just Python wheels.

Examples of available DLC lines (they evolve, but pattern is stable and documented):

* **PyTorch training/inference DLCs** (CUDA 12.x, Ubuntu 22.04). ([AWS Documentation][4])
* **Hugging Face SageMaker DLCs** for training/inference (PyTorch and TensorFlow flavors). ([Hugging Face][5])

With the SageMaker SDK you can retrieve URIs programmatically:

```python
import sagemaker
from sagemaker import image_uris

uri = image_uris.retrieve(
    framework="pytorch",
    region=sagemaker.Session().boto_region_name,
    version="2.6",                 # choose a supported version for your region
    py_version="py311",
    image_scope="training",
    instance_type="ml.g5.2xlarge",
)
print(uri)
```

(Choose a version that exists in your region; AWS keeps current CUDA builds in new DLCs.) ([SageMaker Documentation][6])

# 5) No-internet / VPC-only environments

If outbound internet is blocked, you still have options:

**Option A – Private artifact repo (CodeArtifact)**
Point `pip` to **AWS CodeArtifact** (your org’s private PyPI). AWS has a guide for Studio/Notebook Instances in internet-free mode. ([Amazon Web Services, Inc.][7])

**Option B – Wheels in S3 (offline install)**

1. On a machine with internet, run:

   ```bash
   pip download -r requirements.txt -d wheelhouse/
   ```
2. Upload `wheelhouse/` to S3 (or to your project `source_dir` tarball).
3. In your job or notebook:

   ```python
   # from S3 (Processing/Training): first download to /opt/ml/code/wheelhouse then:
   %pip install --no-index --find-links wheelhouse -r requirements.txt
   ```

This pattern (no-index + find-links) is reliable when egress is cut off. ([Reddit][8])

**Option C – VPC endpoints for S3**
Many teams keep data and wheels in S3 and allow **S3 VPC endpoints** while still blocking the public internet. ([Amazon Web Services, Inc.][9])

# 6) Practical tips to avoid conflicts on SageMaker

* Prefer **SDK framework DLCs** (PyTorch/HF) + `requirements.txt` for Python libs. CUDA/cuDNN already matches the framework.
* Keep your **pins the same** as local dev to avoid “works on my laptop” surprises.
* Don’t mix conda and pip for the **same package** inside a job image.
* If you need GPU **XGBoost**, prefer the container’s preinstalled version (or `py-xgboost` that matches CUDA in the DLC) instead of forcing a different CUDA build.
* For Studio kernels, if `%pip` installs vanish after a kernel/image restart, consider a **Lifecycle Configuration** to reinstall at startup. ([AWS Documentation][10])

---

If you tell me which **DLC** you plan to use (e.g., Hugging Face PyTorch training image version) and whether your account is **egress-blocked**, I’ll give you a drop-in `requirements.txt` + Estimator or Processor snippet tailored to that image (and region).

[1]: https://docs.aws.amazon.com/sagemaker/latest/dg/studio-notebooks-add-external.html?utm_source=chatgpt.com "Install External Libraries and Kernels in ..."
[2]: https://sagemaker.readthedocs.io/en/v2.18.0/frameworks/tensorflow/using_tf.html?utm_source=chatgpt.com "Use TensorFlow with the SageMaker Python SDK"
[3]: https://docs.aws.amazon.com/sagemaker/latest/dg/processing-job-frameworks.html?utm_source=chatgpt.com "Data Processing with Framework Processors"
[4]: https://docs.aws.amazon.com/deep-learning-containers/latest/devguide/dlc-pytorch-2-5-inference-sagemaker.html?utm_source=chatgpt.com "AWS Deep Learning Containers for PyTorch 2.5 Inference ..."
[5]: https://huggingface.co/docs/sagemaker/en/reference?utm_source=chatgpt.com "Reference"
[6]: https://sagemaker.readthedocs.io/en/stable/using_pytorch.html?utm_source=chatgpt.com "Use PyTorch with the SageMaker Python SDK"
[7]: https://aws.amazon.com/blogs/machine-learning/private-package-installation-in-amazon-sagemaker-running-in-internet-free-mode/?utm_source=chatgpt.com "Private package installation in Amazon SageMaker ..."
[8]: https://www.reddit.com/r/learnpython/comments/1fssq5r/best_method_to_install_pip_packages_without/?utm_source=chatgpt.com "Best method to install pip packages without internet"
[9]: https://aws.amazon.com/blogs/machine-learning/secure-amazon-s3-access-for-isolated-amazon-sagemaker-notebook-instances/?utm_source=chatgpt.com "Secure Amazon S3 access for isolated ..."
[10]: https://docs.aws.amazon.com/sagemaker/latest/dg/nbi-add-external.html?utm_source=chatgpt.com "External library and kernel installation"
