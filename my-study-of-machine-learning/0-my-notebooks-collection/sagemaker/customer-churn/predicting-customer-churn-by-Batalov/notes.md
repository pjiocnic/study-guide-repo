1. In a practical application, you should supply an identifier (natural ID) for each data point–such as a customer ID–to tie the churn predictions back to the corresponding customers
2. basic statistics: correlation of individual attributes to the target attribute; the range and distribution of the values
3. Types of distribution encountered: Using a combination of Histograms and BoxPlots, we can see the **numeric attributes**, such as Day_Mins, appear to be normally distributed, while CustServ_Calls appears to be positively skewed.
4. Convert all binary values, such as yes/no and True/False into corresponding 1/0 values
5. distribution of the target attribute, Churn?: we see that 483 out of 3,333 customers, or approximately 14.49 %, did churn. **This is an important statistic for deciding whether the trained model is usable**
6. Analyzing Categorical variables such as Area_Code, Phone, State

| Attributes | Correlations to target | Unique values | Most frequent categories | Least frequent | Preview |
| ---------- | ---------------------: | ------------: | ------------------------ | -------------- | ------- |
| Area\_Code |                0.00004 |             3 | 415                      | 408            | —       |
| Phone      |                      0 |          3333 | 333-4492                 | 374-1213       | —       |
| State      |                0.00576 |            51 | WV                       | CA             | —       |

7. Drop columns - Phone attribute: Here the data is fictitous phone numbers but in real life a customer whose phone number is hard to remember could be more likely to churn. Tip: drop attributes that you are sure have no effect on the target attribute
8. Normalize all numeric attributes. Why? Numeric attributes with higher absolute values could dominate the ML model. How to? After normalization, the corresponding attributes have a mean of 0 and a variance of 1
9. Exception - don't drop numerical column CustServ_Calls. Why? number of calls placed to Customer Service might be highly correlated with churn. After all, a happy customer doesn’t need to call Customer Service. So, I choose to boost the significance of CustServ_Calls by not normalizing it
10. Bottom line - look at numberical columns to make sure they are NOT dominating from other attributes


# Formalized plan for churn modeling prep & analysis (restructured, all details retained)

## 1) Identity linkage

* **Provide a natural identifier per record** (e.g., *customer ID*) so churn predictions can be tied back to the corresponding customers.

## 2) Basic statistics & profiling

* **Compute correlations** of individual attributes to the **target** (Churn?).
* Examine **range** and **distribution** of values for each attribute.

## 3) Distribution assessment (numeric attributes)

* Use **Histograms** and **BoxPlots**.
* Observation: **Day\_Mins** appears **normally distributed**.
* Observation: **CustServ\_Calls** appears **positively skewed**.

## 4) Binary encoding

* **Convert all binary values** (e.g., yes/no, True/False) to **1/0**.

## 5) Target prevalence (class balance)

* For **Churn?**: **483 out of 3,333** customers (**≈14.49%**) did churn.
  **This is an important statistic for deciding whether the trained model is usable.**

## 6) Categorical analysis (Area\_Code, Phone, State)

* Profile categorical variables as follows:

| Attributes | Correlations to target | Unique values | Most frequent categories | Least frequent | Preview |
| ---------- | ---------------------: | ------------: | ------------------------ | -------------- | ------- |
| Area\_Code |                0.00004 |             3 | 415                      | 408            | —       |
| Phone      |                      0 |          3333 | 333-4492                 | 374-1213       | —       |
| State      |                0.00576 |            51 | WV                       | CA             | —       |

## 7) Column dropping (feature selection)

* **Phone** attribute: data here is fictitious phone numbers; however, in real life, a customer whose phone number is hard to remember **could** be more likely to churn.
* **Tip:** drop attributes you are **sure** have **no effect** on the target attribute.

## 8) Normalization (feature scaling)

* **Normalize all numeric attributes.**
  **Why?** Numeric attributes with **higher absolute values** could **dominate** the ML model.
  **How?** After normalization, each such attribute has **mean = 0** and **variance = 1**.

## 9) Exception policy (do not normalize CustServ\_Calls)

* **Do not drop** the numerical column **CustServ\_Calls**.
  **Why?** The number of calls to Customer Service **might be highly correlated with churn** (a happy customer doesn’t need to call).
  **Choice:** **Boost** the significance of **CustServ\_Calls** by **not normalizing** it.

## 10) Final sanity check

* **Bottom line:** Inspect numerical columns to ensure they are **not dominating** other attributes.


## Model evaluation

1. Confusion matrix

| % True class \ % Predicted class | 0                                                                  | 1                                                               |
| -------------------------------- | ------------------------------------------------------------------ | --------------------------------------------------------------- |
| **0**                            | **TN** – Percentage of predictions correctly predicting no churn   | **FP** – Percentage of predictions erroneously predicting churn |
| **1**                            | **FN** – Percentage of predictions erroneously predicting no churn | **TP** – Percentage of predictions correctly predicting churn   |

2. Adjust the threshold (which is 0.5 by default) to further tune predictions, or a cutoff score, that the service will use to give me the final prediction

3. Use Precision and Recall to find percentage of prediction mistakes (The sum of false positive and false negative percentages gives us the total error percentage)


# Assigning Costs

Here’s the gist—under these assumptions, the churn model’s costs per outcome are:

| Outcome                 | What happens                                         | Cost      |
| ----------------------- | ---------------------------------------------------- | --------- |
| **True Negative (TN)**  | Correctly predicts a happy customer → no action      | **\$0**   |
| **False Negative (FN)** | Misses a churning customer → lose them and replace   | **\$500** |
| **True Positive (TP)**  | Correctly flags churner → offer retention incentive  | **\$100** |
| **False Positive (FP)** | Wrongly flags happy customer → unnecessary incentive | **\$100** |

Key takeaways:

* **False negatives are the most expensive** (assumed **\$500** each).
* Both **TP and FP cost \$100** due to offering a retention deal.
* **TN costs \$0** (do nothing).

If you’re optimizing the model, you’d pick a threshold that minimizes
**Expected cost = 500×FN + 100×(TP + FP)**.

# Finding the optimal threshold

optimize the **cutoff C** to minimize total **cost**, not total error:

| Outcome                 | Included in cost?     | Weight            |
| ----------------------- | --------------------- | ----------------- |
| **False Negative (FN)** | Yes                   | **\$500 × FN(C)** |
| **False Positive (FP)** | Yes                   | **\$100 × FP(C)** |
| **True Positive (TP)**  | Yes (retention offer) | **\$100 × TP(C)** |
| **True Negative (TN)**  | No                    | **\$0 × TN(C)**   |

**Objective (as a function of cutoff $C$)**

$$
\text{Cost}(C) \;=\; 500\,\mathrm{FN}(C)\;+\;100\,[\mathrm{TP}(C)+\mathrm{FP}(C)]\;+\;0\cdot\mathrm{TN}(C)
$$

(Equivalently: **Cost(C) = 500·FN(C) + 100·(TP(C)+FP(C))**.)

**Key takeaways**

* **FN is far costlier** than FP, so accuracy isn’t the right target—**minimize cost**.
* TN has zero cost, so its term drops out.
* The optimal **cutoff $C^\*$** is the one that yields the **smallest Cost(C)** on your test set.

**How to find $C^\*$**

1. Score the **test** set to get predicted probabilities.
2. Sweep thresholds $C \in [0,1]$ (e.g., 0.00→1.00 by 0.01).
3. For each $C$, compute FN(C), FP(C), TP(C), TN(C) and then **Cost(C)**.
4. Pick the $C$ with the **minimum** cost; report that cutoff and its confusion matrix.

## Automating cost calculation

see `predicting-customer-churn-by-Batalov/cost-based-ml/cost_based_ml.py`


## Should I use a model to predict customer churn or just a Rule based strategy is good enough?

Here’s the punchline: **use the ML model**—it lowers cost even if accuracy (total error) isn’t better.

| Strategy             | Assumption / Cutoff                     |                      Total Error | Cost per Customer | Savings vs. No-ML |
| -------------------- | --------------------------------------- | -------------------------------: | ----------------: | ----------------: |
| **No ML (baseline)** | Assume everyone is loyal; no incentives |               — (FN rate 14.49%) |       **\$72.45** |                 — |
| **ML model**         | Optimal cutoff **C = 0.1698949**        | \~**19%** (higher than baseline) |       **\$50.30** |       **\$22.15** |

**Why this happens:** false negatives are expensive (\$500 each), so optimizing for **cost** beats optimizing for **error rate**. The model targets costly churners with a \$100 retention offer, reducing overall spend despite more total errors.

**Business impact:** at 100,000 customers, the model’s **\$22.15/customer** advantage ≈ **\$2.215M** savings.
**General lesson:** treat any approach (even rule-based or “do nothing”) as a “model,” compute expected cost, and pick the strategy with the **lowest cost**, not necessarily the lowest error.


# Additional Reading

1. [Creating a Data Schema for Amazon ML](https://docs.aws.amazon.com/machine-learning/latest/dg/creating-a-data-schema-for-amazon-ml.html)
2. [Using Amazon S3 with Amazon ML](https://docs.aws.amazon.com/machine-learning/latest/dg/using-amazon-s3-with-amazon-ml.html)
3. [Download, Prepare, and Upload Training Data](https://docs.aws.amazon.com/sagemaker/latest/dg/automatic-model-tuning-ex-data.html)
4. [Data Transformations for Machine Learning](https://docs.aws.amazon.com/machine-learning/latest/dg/data-transformations-for-machine-learning.html)
5. [Precision and recall](https://en.wikipedia.org/wiki/Precision_and_recall)
6. [Binary Model Insights](https://docs.aws.amazon.com/machine-learning/latest/dg/binary-model-insights.html)