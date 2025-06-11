
# 🧠 Key Mathematical Concepts in LoRA (Low-Rank Adaptation)
*A beginner-friendly breakdown with curated learning resources.*

---

### 0. My Goto Sites

1. [Low Rank Representations and Singular Value Decomposition by Barry Van Veen](https://www.youtube.com/playlist?list=PLGI7M8vwfrFMh15xEWdkhJSy08xGo134L)
1. [Low Rank Decompositions of Matrices by Barry Van Veen](https://www.youtube.com/watch?app=desktop&v=_FmolBCUo9M)

---

### **1. Matrices and Vectors**
- **What it is**: LoRA modifies model weights, which are stored as matrices. Understanding how to add and multiply matrices and vectors is foundational.
- **Why it matters**: Matrix operations form the basis of how neural networks process and update weights.
- **Resources**:
  - 📘 [Khan Academy – Linear Algebra: Vectors and Matrices](https://www.khanacademy.org/math/linear-algebra)
  - 📺 [3Blue1Brown – Essence of Linear Algebra (YouTube)](https://www.youtube.com/playlist?list=PLZHQObOWTQDMsr9K-rj53DwVRMYO3t5Yr)

---

### **2. Rank of a Matrix**
- **What it is**: The rank tells us the number of linearly independent rows or columns in a matrix.
- **Why it matters**: LoRA leverages *low-rank* matrices to reduce the number of parameters without sacrificing performance.
- **Resources**:
  - 📘 [Paul’s Online Math Notes – Matrix Rank](https://tutorial.math.lamar.edu/Classes/LinAlg/Rank.aspx)
  - 📺 [Khan Academy – Rank of a Matrix](https://www.khanacademy.org/math/linear-algebra/alternate-bases/rank/v/linear-algebra-rank)

---

### **3. Matrix Factorization**
- **What it is**: The process of breaking down a matrix into a product of smaller matrices.
- **Why it matters**: LoRA updates are split into two smaller matrices (A and B), dramatically reducing trainable parameters.
- **Resources**:
  - 📘 [Stanford CS229 Notes – Matrix Factorization](https://cs229.stanford.edu/notes2022fall/cs229-notes7.pdf)
  - 📺 [StatQuest – Matrix Factorization Explained](https://www.youtube.com/watch?v=ZspR5PZemcs)

---

### **4. Low-Rank Approximation**
- **What it is**: An efficient method of representing large matrices using low-rank components.
- **Why it matters**: LoRA assumes that weight changes during fine-tuning can be captured by low-rank approximations.
- **Resources**:
  - 📘 [MIT OpenCourseWare – Lecture on SVD & Low-Rank Approximation](https://ocw.mit.edu/courses/mathematics/18-06-linear-algebra-spring-2010/)
  - 📺 [3Blue1Brown – SVD and Low-Rank Approximation](https://www.youtube.com/watch?v=P5mlg91as1c)

---

### **5. Linear Algebra Basics**
- **What it is**: Includes concepts like vector spaces, linear independence, and matrix transformations.
- **Why it matters**: These are the building blocks behind matrix operations and understanding LoRA’s structure.
- **Resources**:
  - 📘 [Khan Academy – Full Linear Algebra Course](https://www.khanacademy.org/math/linear-algebra)
  - 📺 [3Blue1Brown – Essence of Linear Algebra](https://www.youtube.com/playlist?list=PLZHQObOWTQDMsr9K-rj53DwVRMYO3t5Yr)

---

### **6. Scaling Factor (α)**
- **What it is**: A hyperparameter introduced in LoRA to control the magnitude of low-rank updates.
- **Why it matters**: Proper scaling ensures model stability and optimal fine-tuning.
- **Resources**:
  - 📘 [DeepLearning.ai – The Batch Newsletter on LoRA](https://www.deeplearning.ai/the-batch/issue-206/)
  - 📺 [Hugging Face – LoRA for Fine-Tuning Transformers](https://www.youtube.com/watch?v=KxvKCS5NQ3s)

---

### **7. Hyperparameters (e.g., Rank `r`, Alpha)**
- **What it is**: Settings that define model behavior during training, such as the rank of the matrix decomposition and the scaling factor.
- **Why it matters**: These directly affect performance, efficiency, and generalization in fine-tuning.
- **Resources**:
  - 📘 [Hugging Face – PEFT (Parameter-Efficient Fine-Tuning) Guide](https://huggingface.co/blog/peft)
  - 📘 [Weights & Biases – Hyperparameter Tuning Guide](https://wandb.ai/site/articles/hyperparameter-tuning)

---

### 📚 Bonus: General Platforms for Learning
- 🧮 [Khan Academy](https://www.khanacademy.org/) – For all math fundamentals.
- 🎓 [Coursera – Mathematics for Machine Learning](https://www.coursera.org/learn/linear-algebra-machine-learning) – Great structured course.
- 💡 [Brilliant.org](https://brilliant.org/) – Interactive math and data science practice.
- 🔍 [Google Scholar](https://scholar.google.com/) – For in-depth academic references on LoRA and matrix methods.

---

Let me know if you'd like this exported as a **PDF**, turned into **slides**, or adapted into a **study flashcard set**!

---

### Linear Algebra

### Basics
1. Orthogonal matrix
1. [Unitary Matrix](https://www.cuemath.com/algebra/unitary-matrix/)
1. [Complex, Hermitian, and Unitary Matrices by Professor Dave Explains](https://www.youtube.com/watch?v=DUuTx2nbizM&list=PLybg94GvOJ9FoGQeUMFZ4SWZsr30jlUYK&index=157&pp=iAQB)
1. [Matrix decomposition by Professor Dave Explains](https://www.youtube.com/watch?v=wHAJzemKQW4&list=PLybg94GvOJ9En46TNCXL2n6SiqRc_iMB8&index=22)

### Matrix decomposition

1. [Matrix Decompositions in Machine Learning by Yusei Fujikura](https://medium.com/@yuseif445/matrix-decompositions-in-machine-learning-f97a3f250f81)

### SVD
1. [Lecture: The Singular Value Decomposition (SVD)](https://www.youtube.com/watch?v=EokL7E6o1AE)
1. [Lecture 21 - Singular Value Decomposition](https://www.youtube.com/watch?v=QrMwkwAHP2Q)
- https://www.dropbox.com/scl/fi/rq49rme1hrevg7n78d8oe/SVD.pdf?rlkey=oorac1giqhswgnfdppcnreb8c&e=1&dl=0

1. [Practical Linear Algebra for Data Science by Mike X Cohen](https://learning.oreilly.com/library/view/practical-linear-algebra/9781098120603/ch06.html#id327)
1. [Further Matrix Decompositions: LU, Cholesky, QR, and SVD by Professor Dave Explains](https://www.youtube.com/watch?v=wHAJzemKQW4&list=PLybg94GvOJ9FoGQeUMFZ4SWZsr30jlUYK&index=158&pp=iAQB)
1. [Lecture 47 — Singular Value Decomposition | Stanford University](https://www.youtube.com/watch?v=P5mlg91as1c&t=55s)
1. [Singular Value Decomposition (SVD): Mathematical Overview by Steve Brunton](https://www.youtube.com/watch?v=nbBvuuNVfco&list=PLMrJAkhIeNNSVjnsviglFoY2nXildDCcv)

### Rank
2. [Rank by Echelon Form | Matrix | Maths BY Gautam Varde](https://www.youtube.com/watch?v=czEh2xAinBs)

[LoRA for fine-tuning LLMs explained with example by Data Science in your pocket](https://www.youtube.com/watch?v=chfr8N59N1M)
[LoRA & QLoRA Fine-tuning Explained In-Depth by Entry Point AI](https://www.youtube.com/watch?v=t1caDsMzWBk)
[Low-rank Adaption of Large Language Models: Explaining the Key Concepts Behind LoRA by Chris Alexiuk](https://www.youtube.com/watch?v=dA-NhCtrrVE&t=14s)
[LoRA: Low-Rank Adaptation of Large Language Models - Explained visually + PyTorch code from scratch by Umar Jamil](https://www.youtube.com/watch?v=PXWYUTMt-AU)
[[**START_HERE**] What is LoRA? Low-Rank Adaptation for finetuning LLMs EXPLAINED by AI Coffee Break with Letitia](https://www.youtube.com/watch?v=KEv-F5UkhxU&t=316s)

---

You're correct in noting that **Low-Rank Adaptation (LoRA)** utilizes low-rank decomposition techniques to efficiently fine-tune large language models. To help you grasp these concepts, here are some beginner-friendly video resources:

**Low Rank Decompositions of Matrices**
This video provides an introduction to low-rank matrix decompositions, explaining the fundamentals and applications of these techniques.
[Watch on YouTube](https://m.youtube.com/watch?v=_FmolBCUo9M)

**Low-rank approximation - Harvard AM205 video 2.12**
A lecture from Harvard's Applied Math 205 course that delves into low-rank approximations and their significance in numerical methods.
[Watch on YouTube](https://www.youtube.com/watch?v=zNMxkFWP_WU)


**(Lecture 22) SVD: Low Rank Approximation**
This lecture discusses Singular Value Decomposition (SVD) and its role in achieving low-rank approximations, providing a mathematical foundation for these concepts.
[Watch on YouTube](https://www.youtube.com/watch?v=kUglNcA3evc)

**Explaining the Key Concepts Behind LoRA**
This video introduces LoRA and its use of low-rank matrix decompositions to reduce the costs associated with training large language models.
[Watch on YouTube](https://www.youtube.com/watch?v=dA-NhCtrrVE)

