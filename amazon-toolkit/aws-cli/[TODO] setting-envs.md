
1. Save the file as `check_and_update_sqs.py`.
2. Make sure you have **AWS credentials** set (`aws configure` or env vars).
3. Install `boto3`:

   ```bash
   pip install boto3
   ```
4. Run:

   ```bash
   python check_and_update_sqs.py
   ```

---

Great question — let’s go step-by-step on **how to do `aws configure` on a Windows laptop**!

---

## 🧭 Steps to set up `aws configure` on Windows:

1. ✅ **Install the AWS CLI** (if you haven’t already):

   * Download the installer for Windows (MSI) from here:
     [https://aws.amazon.com/cli/](https://aws.amazon.com/cli/)
   * Run the `.msi` file and follow the setup wizard.

2. ✅ **Open Command Prompt (cmd) or PowerShell**:

   * Hit **Windows + R**, type `cmd` and press **Enter**.
   * Or you can use **PowerShell** (`powershell` in Run).

3. ✅ **Run the `aws configure` command**:

   ```powershell
   aws configure
   ```

4. ✅ **Provide your credentials when prompted**:

   ```text
   AWS Access Key ID [None]: YOUR_ACCESS_KEY_ID
   AWS Secret Access Key [None]: YOUR_SECRET_ACCESS_KEY
   Default region name [None]: us-east-1    # Or your preferred region
   Default output format [None]: json       # Or text, yaml
   ```

---

## 📝 Finding your credentials:

Your **Access Key** and **Secret Key** can be generated or retrieved from the AWS Management Console under:

```
IAM → Users → YourUser → Security credentials → Create access key
```

> ⚠️ Never share these keys publicly!

---

## 📂 Where this config is saved:

By default, `aws configure` stores credentials in:

* `C:\Users\<YourWindowsUsername>\.aws\credentials`
* Configs like region in `C:\Users\<YourWindowsUsername>\.aws\config`

You can check them anytime using:

```powershell
type $env:USERPROFILE\.aws\credentials
type $env:USERPROFILE\.aws\config
```

---

## 🎯 Test your setup:

Try a simple AWS CLI command to verify:

```powershell
aws s3 ls
```

If configured properly, it’ll list your S3 buckets.

---

💡 **That’s it!** Let me know if you’d also like help with:

* Creating an **IAM user** for CLI.
* Installing AWS CLI using `choco`.
* Scripting profile-specific credentials.
* Switching between multiple profiles (`aws configure --profile profile_name`).

Would you like me to prepare those too?
