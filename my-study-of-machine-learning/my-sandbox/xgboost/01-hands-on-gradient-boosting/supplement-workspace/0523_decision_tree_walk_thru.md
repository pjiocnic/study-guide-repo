DataSet: books/01-hands-on-gradient-boosting/workspace/Chapter02/census_cleaned.csv

<figure>
  <img src="images/0523-1.png" style="border: 2px solid black; border-radius: 5px;" width="550"/>
  <figcaption><b>Fig 1:</b> Decision Tree</figcaption>
</figure>

---

## 📦 Dataset Counts at Root Node

| Label (income\_ >50K) | Count  |
| --------------------- | ------ |
| Class 0 (≤50K)        | 18,539 |
| Class 1 (>50K)        | 5,881  |
| **Total Samples**     | 24,420 |

---

## 🔢 Step 1: Compute **Root Gini (Unsplit)**

$$
p_0 = \frac{18539}{24420}, \quad p_1 = \frac{5881}{24420}
$$

$$
\text{Gini}_{\text{root}} = 1 - p_0^2 - p_1^2
= 1 - \left(\frac{18539}{24420}\right)^2 - \left(\frac{5881}{24420}\right)^2
$$

$$
\approx 1 - (0.7592)^2 - (0.2408)^2
= 1 - 0.5764 - 0.0588 = \boxed{0.3657}
$$

✅ Matches the diagram exactly.

---

## 🌿 Split: `marital-status_ Married-civ-spouse <= 0.5`

Now, the dataset is split into:

| Branch        | Class 0 | Class 1 | Total  |
| ------------- | ------- | ------- | ------ |
| Left (<= 0.5) | 12,344  | 865     | 13,209 |
| Right (> 0.5) | 6,195   | 5,016   | 11,211 |

---

## 🔢 Step 2: Gini for Left Child

$$
p_0 = \frac{12344}{13209}, \quad p_1 = \frac{865}{13209}
$$

$$
\text{Gini}_{\text{left}} = 1 - p_0^2 - p_1^2
\approx 1 - (0.9345)^2 - (0.0655)^2
\approx 1 - 0.8733 - 0.0043 = \boxed{0.1224}
$$

---

## 🔢 Step 3: Gini for Right Child

$$
p_0 = \frac{6195}{11211}, \quad p_1 = \frac{5016}{11211}
$$

$$
\text{Gini}_{\text{right}} = 1 - p_0^2 - p_1^2
\approx 1 - (0.5524)^2 - (0.4476)^2
\approx 1 - 0.3051 - 0.2003 = \boxed{0.4945}
$$

---

## 🧮 Step 4: Weighted Gini After Split

$$
\text{Weighted Gini} = \frac{13209}{24420} \cdot 0.1224 + \frac{11211}{24420} \cdot 0.4945
$$

$$
= 0.5410 \cdot 0.1224 + 0.4589 \cdot 0.4945
\approx 0.0663 + 0.2269 = \boxed{0.2932}
$$

---

## ✅ Final Summary Table

| Description                   | Value  |
| ----------------------------- | ------ |
| Root Gini (Unsplit)           | 0.3657 |
| Gini Left                     | 0.1224 |
| Gini Right                    | 0.4945 |
| **Weighted Gini After Split** | 0.2932 |

This confirms:

* The split on `marital-status_Married-civ-spouse` is chosen because it **reduces impurity** the most.
* All numbers match your decision tree output.

Would you like to do this breakdown visually or repeat for the next best split (e.g., `relationship_Husband`)?

---

## 🧠 **What's the Purpose of "Weighted Gini After Split"?**

The **goal** of a decision tree at each node is to **find the best feature and threshold to split the data** so that the resulting child nodes are as **pure** as possible.

* **Root Gini** tells us **how impure the current node is**.
* **Weighted Gini After Split** tells us **how impure the children will be** after a specific split — **accounting for how many samples go left vs. right**.

---

## 🔄 Why "Weighted"?

Let’s say:

* One child node is very pure but has only 5 samples.
* The other child node is very impure but has 5000 samples.

We don’t want to be misled by the pure child — we must **weigh impurity by how many samples each child has**.

That’s what **Weighted Gini** does:

$$
\text{Weighted Gini} = \frac{n_{\text{left}}}{n_{\text{total}}} \cdot \text{Gini}_{\text{left}} + \frac{n_{\text{right}}}{n_{\text{total}}} \cdot \text{Gini}_{\text{right}}
$$

---

## 📊 Where Is It in the Diagram?

**It’s not shown directly.**

But the **tree uses it behind the scenes** at each step to **choose the best split**.

What you see in the diagram is:

* Gini of the **current node** (e.g., root = 0.366)
* Samples and class distribution

What’s **not shown**:

* The Gini values **before making the split** (i.e., Root Gini vs. Weighted Gini After Split for each candidate feature)

---

## ✅ Decision Rule

At every node:

The algorithm computes **Weighted Gini After Split** for **all possible features and thresholds**,
and **selects the one that minimizes it.**

That’s how `marital-status_ Married-civ-spouse <= 0.5` won the root node.

---

