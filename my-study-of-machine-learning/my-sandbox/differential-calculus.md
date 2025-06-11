Great! Let's go through a **refresher on Differential Calculus** step by step. 🚀

---

## **1. What is Calculus?**
Calculus is the branch of mathematics that studies **change**. It has two major parts:
1. **Differential Calculus** – Deals with the rate of change (slopes, derivatives).
2. **Integral Calculus** – Deals with accumulation (areas, summing small parts).

Since we are focusing on **Differential Calculus**, let's dive into it!

---

## **2. Understanding Slope & Rate of Change**
Before calculus, we use the concept of **slope** in algebra to measure how steep a line is.

For a straight line:
\[
\text{slope} = \frac{\text{change in } y}{\text{change in } x} = \frac{y_2 - y_1}{x_2 - x_1}
\]

But what if we have a **curve** (like \( y = x^2 + 2 \))? The slope is **not constant**! Instead, it **changes** at every point.

---

## **3. Instantaneous Rate of Change (Derivative)**
To find the slope of a curve at a specific point, we use **limits**.

1. **Pick two points on the curve:**
   - One at \( x \), which has value \( f(x) \).
   - Another close by at \( x + h \), which has value \( f(x+h) \).

2. **Compute the average rate of change (slope between two points):**
   \[
   \frac{f(x+h) - f(x)}{h}
   \]

3. **Take the limit as \( h \to 0 \):**
   - This brings the second point infinitely close to the first, giving the **instantaneous slope**.

\[
f'(x) = \lim_{h \to 0} \frac{f(x+h) - f(x)}{h}
\]

This is called the **derivative** of \( f(x) \), denoted as \( f'(x) \) or \( \frac{dy}{dx} \).

---

## **4. Basic Rules of Differentiation**
Once we understand the definition, we can use **rules** to differentiate functions quickly:

1. **Power Rule:**
   \[
   \frac{d}{dx} x^n = n x^{n-1}
   \]
   Example: \( \frac{d}{dx} x^3 = 3x^2 \)

2. **Constant Rule:**
   \[
   \frac{d}{dx} (C) = 0
   \]
   Example: \( \frac{d}{dx} (5) = 0 \)

3. **Sum/Difference Rule:**
   \[
   \frac{d}{dx} (f(x) + g(x)) = f'(x) + g'(x)
   \]
   Example: \( \frac{d}{dx} (x^3 + 2x) = 3x^2 + 2 \)

4. **Product Rule:**
   \[
   \frac{d}{dx} (f(x) g(x)) = f'(x) g(x) + f(x) g'(x)
   \]

5. **Quotient Rule:**
   \[
   \frac{d}{dx} \left( \frac{f(x)}{g(x)} \right) = \frac{f'(x)g(x) - f(x)g'(x)}{g(x)^2}
   \]

6. **Chain Rule:**
   \[
   \frac{d}{dx} f(g(x)) = f'(g(x)) \cdot g'(x)
   \]
   Used for composite functions.

---

## **5. Example: Finding the Derivative of \( y = x^2 + 2 \)**
Using the **power rule**:

\[
\frac{d}{dx} (x^2 + 2) = 2x + 0 = 2x
\]

This means the **instantaneous slope** of \( y = x^2 + 2 \) at any point \( x \) is given by \( 2x \).

To find the slope at \( x = 5 \):

\[
f'(5) = 2(5) = 10
\]

So, the slope at \( x = 5 \) is **10**.

---

## **6. Why is This Useful?**
1. **Physics** – Derivatives measure velocity, acceleration, and other rates of change.
2. **Economics** – Used in cost functions, profit maximization, etc.
3. **Machine Learning & AI** – Optimization algorithms rely on derivatives.
4. **Engineering** – Used in control systems, signal processing, and mechanics.

---

## **7. Next Steps**
- If you want more practice, try differentiating:
  1. \( f(x) = x^3 + 4x \)
  2. \( g(x) = \frac{1}{x} \)
  3. \( h(x) = \sin x \)

Would you like a deeper dive into any specific topic? 🚀

---

### 1. To find the slope of the function

To find the slope of the function \( y = x^2 + 2 \) at \( x = 5 \), follow these steps:

1. **Find the derivative of \( y \):**
   The derivative of \( y = x^2 + 2 \) gives the slope of the function at any point \( x \).
   \[
   \frac{dy}{dx} = \frac{d}{dx} (x^2 + 2)
   \]
   Using the power rule \( \frac{d}{dx} x^n = n x^{n-1} \), we get:
   \[
   \frac{dy}{dx} = 2x
   \]

2. **Evaluate the derivative at \( x = 5 \):**
   \[
   \frac{dy}{dx} \Big|_{x=5} = 2(5) = 10
   \]

So, the slope of the function at \( x = 5 \) is **10**.