## 📖 Workbook: Troubleshooting Amazon SQS Dead Letter Queues (DLQ)

### 🎥 Video Summary

**Speaker:** Nishant, Cloud Support Engineer at AWS, Bangalore
**Topic:** Why messages might be sent to a Dead Letter Queue (DLQ) in Amazon SQS and how to troubleshoot the issue.

---

### 🔍 What You’ll Learn

✅ What a Dead Letter Queue (DLQ) is in SQS
✅ How the **maximum receive count** affects message redelivery
✅ Other common causes that trigger messages to move to a DLQ
✅ Best practices to avoid messages going to the DLQ too early

---

## ✏️ Notes & Steps

---

### 1️⃣ Understanding Dead Letter Queues

* Amazon SQS can be configured with a Dead Letter Queue.
* Messages move to the DLQ if the **number of receive attempts** exceeds the **maximum receive count**.

#### 🔧 Check the Maximum Receive Count

1. Go to the **Amazon SQS Console**.
2. Select the **source queue**.
3. Click **Edit**.
4. Under **Dead Letter Queue section** → look for the **Maximum Receives** value.

💡 **Default value:** 10
⚠️ Adjust this number based on your application’s needs.

---

### 2️⃣ Make Sure the Dead Letter Queue’s Retention is Longer

* DLQ retention period must be **longer than the source queue retention**.
* This prevents the DLQ messages from expiring before you process them.

---

### 3️⃣ Check Lambda Configuration

If using AWS Lambda as a consumer:

* Check the article **“Why is my Lambda function retrying valid messages?”** for further tuning.
* Poor configuration can send **valid messages to the DLQ** too quickly.

---

### 4️⃣ Avoid Premature Dead Lettering Due to Console Viewing

* Simply **viewing messages** in the SQS console increases the receive count.
* This can cause early movement to the DLQ.
* 💡 **Mitigation:** Increase the Maximum Receive Count value as needed.

---

### 5️⃣ Mind the Visibility Timeout

* Messages must be **deleted** within the configured **Visibility Timeout window**.
* If they aren’t deleted:

  * They’ll become visible again.
  * This counts as an extra receive attempt.

---

### ✅ Summary Checklist

✅ Check and set the **maximum receive count** properly.
✅ DLQ retention period must be **longer than source queue retention**.
✅ Review **Lambda configurations** if applicable.
✅ Avoid previewing messages in the SQS console too often.
✅ Ensure messages are deleted within the **visibility timeout**.

---

## 🧠 Quiz Yourself

1. What happens when the max receive count is exceeded?
2. Why must the DLQ retention period be greater than the source queue retention?
3. How can viewing messages in the console cause dead lettering?
4. What is the effect of not deleting messages within the visibility timeout?

---

## 📝 Action Plan

✅ Review your SQS Dead Letter Queue configuration.
✅ Adjust **maximum receive count** as needed.
✅ Check Lambda behavior (if applicable).
✅ Confirm your team’s process for deleting messages.

---

**🎉 That’s it!**
You now have a solid understanding of why messages might end up in a Dead Letter Queue and how to troubleshoot common SQS DLQ issues.

---

Let me know if you’d also like me to format this as a **Google Docs link** or **downloadable PDF** file — just say the word! 💬
