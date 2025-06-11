<h1>DL Fundamentals</h1>

<!-- TOC -->

- [1. **Logits**](#1-logits)
- [2. **Softmax**](#2-softmax)
  - [2.1 What is Softmax](#21-what-is-softmax)
  - [2.2 Softmax Temperature](#22-softmax-temperature)
- [3. **Sigmoid**](#3-sigmoid)
- [4. **Cross-Entropy**](#4-cross-entropy)
- [5. Loss Functions](#5-loss-functions)
- [9. Summary Table:](#9-summary-table)

<!-- /TOC -->

---

## 1. **Logits**
   - **Category**: **Intermediate Output**
   - **Details**: Logits refer to the raw, unnormalized scores or outputs of the neural network before applying an activation function like softmax or sigmoid. They are typically the direct output of the last layer in a model.
  - **Useful Links:**
      1. [Understanding Logits, Sigmoid, Softmax, and Cross-Entropy Loss in Deep Learning by Aman Arora](https://wandb.ai/amanarora/Written-Reports/reports/Understanding-Logits-Sigmoid-Softmax-and-Cross-Entropy-Loss-in-Deep-Learning--Vmlldzo0NDMzNTU3)

---

## 2. **Softmax**
   - **Category**: **Activation Function**
   - **Details**: The softmax function converts logits (raw scores) into a probability distribution over multiple classes. It is used in the final layer of a neural network for multi-class classification. The formula is:
     \[
     \text{Softmax}(z_i) = \frac{e^{z_i}}{\sum_{j} e^{z_j}}
     \]

### 2.1 What is Softmax

1. [Understanding Logits, Sigmoid, Softmax, and Cross-Entropy Loss in Deep Learning by Aman Arora](https://wandb.ai/amanarora/Written-Reports/reports/Understanding-Logits-Sigmoid-Softmax-and-Cross-Entropy-Loss-in-Deep-Learning--Vmlldzo0NDMzNTU3)

### 2.2 Softmax Temperature

1. [Softmax Temperature by Harshit Sharma](https://medium.com/@harshit158/softmax-temperature-5492e4007f71)

---

## 3. **Sigmoid**
   - **Category**: **Activation Function**
   - **Details**: The sigmoid function maps input values to the range (0, 1), making it particularly useful for binary classification problems. It is defined as:
     \[
     \sigma(x) = \frac{1}{1 + e^{-x}}
     \]

1. [Understanding Logits, Sigmoid, Softmax, and Cross-Entropy Loss in Deep Learning by Aman Arora](https://wandb.ai/amanarora/Written-Reports/reports/Understanding-Logits-Sigmoid-Softmax-and-Cross-Entropy-Loss-in-Deep-Learning--Vmlldzo0NDMzNTU3)

---

## 4. **Cross-Entropy**
   - **Category**: **Loss Function**
   - **Details**: Cross-entropy measures the difference between two probability distributions — the predicted probabilities and the actual labels. It is commonly used in classification problems. For binary classification, the formula is:
     \[
     \text{Cross-Entropy Loss} = - \frac{1}{N} \sum_{i=1}^{N} \left[ y_i \log(\hat{y}_i) + (1 - y_i) \log(1 - \hat{y}_i) \right]
     \]
     For multi-class classification, it extends to:
     \[
     \text{Loss} = - \sum_{i} y_i \log(\hat{y}_i)
     \]

1. [Understanding Logits, Sigmoid, Softmax, and Cross-Entropy Loss in Deep Learning by Aman Arora](https://wandb.ai/amanarora/Written-Reports/reports/Understanding-Logits-Sigmoid-Softmax-and-Cross-Entropy-Loss-in-Deep-Learning--Vmlldzo0NDMzNTU3)

---

## 5. Loss Functions

1. [Loss Functions in Machine Learning Explained](https://www.datacamp.com/tutorial/loss-function-in-machine-learning)

---

## 9. Summary Table:

| **Term**        | **Category**         | **Purpose**                              |
|------------------|----------------------|------------------------------------------|
| **Sigmoid**      | Activation Function  | Binary classification activation         |
| **Cross-Entropy**| Loss Function        | Measures error in predicted probabilities|
| **Softmax**      | Activation Function  | Converts logits to class probabilities   |
| **Logits**       | Intermediate Output  | Raw scores from the model                |

---

