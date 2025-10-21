### **🔹 Mathematics Behind PyTorch Autograd**
PyTorch’s **autograd (automatic differentiation)** system is built on **calculus and linear algebra**, particularly the **chain rule from differential calculus**. This allows PyTorch to compute gradients efficiently for optimization.

---

## **1️⃣ Core Mathematics Used in PyTorch Autograd**
### **📌 1. Chain Rule (Differential Calculus)**
The **chain rule** is the foundation of backpropagation, allowing us to compute gradients of **composite functions**.

Given a function:
\[
z = f(y), \quad y = g(x)
\]
The derivative of \( z \) with respect to \( x \) is:
\[
\frac{dz}{dx} = \frac{dz}{dy} \cdot \frac{dy}{dx}
\]
In PyTorch, **autograd** applies this rule **recursively** when computing gradients through multiple layers of a neural network.

---

### **📌 2. Jacobian and Hessian Matrices (Multivariable Calculus)**
For **vector-valued** functions, we use:
- **Jacobian matrix**: First-order partial derivatives (used in backpropagation).
- **Hessian matrix**: Second-order derivatives (used in advanced optimizers like Newton’s method).

#### **Example: Jacobian for a Vector Function**
For a function **\( f(x) \)** with multiple outputs:
\[
f(x) =
\begin{bmatrix}
f_1(x) \\
f_2(x) \\
\vdots \\
f_n(x)
\end{bmatrix}
\]
The **Jacobian matrix** is:
\[
J =
\begin{bmatrix}
\frac{\partial f_1}{\partial x_1} & \frac{\partial f_1}{\partial x_2} & \cdots & \frac{\partial f_1}{\partial x_m} \\
\frac{\partial f_2}{\partial x_1} & \frac{\partial f_2}{\partial x_2} & \cdots & \frac{\partial f_2}{\partial x_m} \\
\vdots & \vdots & \ddots & \vdots \\
\frac{\partial f_n}{\partial x_1} & \frac{\partial f_n}{\partial x_2} & \cdots & \frac{\partial f_n}{\partial x_m}
\end{bmatrix}
\]

PyTorch allows computing the **Jacobian** and **Hessian** using `torch.autograd.functional.jacobian()` and `torch.autograd.functional.hessian()`.

---

### **📌 3. Computational Graphs (Graph Theory & Linear Algebra)**
PyTorch represents computations as a **directed acyclic graph (DAG)**, where:
- **Nodes** are **tensors**.
- **Edges** are **operations** applied to tensors.
- Gradients flow **backward** through this graph during backpropagation.

#### **Example: Computational Graph for \( y = x^2 + 3x \)**
1. \( u = x^2 \)
2. \( v = 3x \)
3. \( y = u + v \)

PyTorch dynamically constructs this graph and applies **automatic differentiation**.

---

### **📌 4. Optimization (Gradient Descent)**
Once gradients are computed, PyTorch uses **gradient descent** (or its variants like Adam, RMSprop) to update parameters.

For gradient descent:
\[
\theta := \theta - \alpha \frac{dL}{d\theta}
\]
where:
- \( \theta \) = Model parameters
- \( L \) = Loss function
- \( \alpha \) = Learning rate

---

## **2️⃣ Example: PyTorch Autograd in Action**
```python
import torch

# Define a function: y = x^2 + 3x
x = torch.tensor(2.0, requires_grad=True)
y = x**2 + 3*x

# Compute gradient
y.backward()

# Print dy/dx
print(x.grad)  # Output: 2x + 3 = 2(2) + 3 = 7
```
✅ PyTorch **automatically computes gradients** using the **chain rule** and stores them in `x.grad`.

---

## **🚀 Summary**
✔ **Autograd in PyTorch relies on:**
✅ **Differential Calculus (Chain Rule)** – For computing derivatives.
✅ **Multivariable Calculus (Jacobian, Hessian)** – For vector gradients.
✅ **Graph Theory (Computational Graphs)** – To store operations dynamically.
✅ **Optimization (Gradient Descent)** – For parameter updates.

These mathematical principles make **automatic differentiation and deep learning** possible in PyTorch! 🚀

---

### Videos

1. [Multivariable calculus By Khan Academy](https://www.khanacademy.org/math/multivariable-calculus)
1. [Describing graphs](https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/describing-graphs)
1. https://www.mathtutordvd.com/products/The-Calculus-3-Tutor-Volume-1.cfm