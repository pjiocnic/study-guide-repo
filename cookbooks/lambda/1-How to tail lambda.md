If you want to **tail AWS Lambda logs from your local Windows laptop**, the easiest and most common way is using the **AWS CLI** (or **AWS SAM CLI**) to pull **CloudWatch Logs** in near-real time.

Here’s the breakdown:

---

## **1️⃣ Prerequisites**

* **AWS CLI installed**
  Download: [AWS CLI v2 for Windows](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
* **Configured AWS credentials**

  ```powershell
  aws configure
  ```

  Enter:

  * AWS Access Key
  * AWS Secret Key
  * Default region
  * Output format (`json` is fine)

---

## **2️⃣ Get the Lambda's CloudWatch Log Group Name**

AWS Lambda automatically writes logs to **CloudWatch Logs** in a group named:

```
/aws/lambda/<YourLambdaFunctionName>
```

Check with:

```powershell
aws logs describe-log-groups --log-group-name-prefix "/aws/lambda/"
```

---

## **3️⃣ Tail Logs Using AWS CLI**

Replace `MyFunction` with your Lambda’s function name:

```powershell
aws logs tail /aws/lambda/MyFunction --follow
```

* `--follow` works like `tail -f`, streaming new logs as they arrive.
* Optional: Limit to last X minutes:

```powershell
aws logs tail /aws/lambda/MyFunction --follow --since 5m
```

---

## **4️⃣ Using AWS SAM CLI (Alternative)**

If you have **AWS SAM CLI** installed:

```powershell
sam logs --name MyFunction --tail
```

* `--name` is the Lambda name
* `--tail` keeps it running live

Install SAM CLI for Windows: [AWS SAM CLI Install](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install-windows.html)

---

## **5️⃣ With Filtering (Optional)**

You can add filters to see only logs matching a string:

```powershell
aws logs tail /aws/lambda/MyFunction --follow --filter-pattern "ERROR"
```

---

✅ **Summary**:
If you just want quick live logs, the **AWS CLI v2** command:

```powershell
aws logs tail /aws/lambda/MyFunction --follow
```

is the cleanest approach from Windows.

---

If you want, I can give you a **PowerShell script** that automatically:

1. Detects the Lambda name
2. Picks the latest log stream
3. Starts tailing it with filters.
   That way you can just run `tail-lambda.ps1 "MyFunction"` and watch logs live.

Do you want me to make that script?
