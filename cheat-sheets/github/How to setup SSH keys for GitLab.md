## ✅ Step 1: Check for Existing SSH Keys (Optional)

Open **Git Bash** or **Windows Terminal** and run:

```bash
ls -al ~/.ssh
```

If you see files like `id_rsa` and `id_rsa.pub`, you already have a key. You can skip to Step 4.

---

## 🛠️ Step 2: Generate a New SSH Key

In **Git Bash** or **PowerShell**, run:

```bash
ssh-keygen -t rsa -b 4096 -C "your_email@example.com"
```

You'll see prompts like:

```text
Enter file in which to save the key (/c/Users/YourName/.ssh/id_rsa):
```

* Press **Enter** to accept the default path.
* Enter a **passphrase** if you want, or press **Enter** to skip.

You should see:

```
Your identification has been saved in /c/Users/YourName/.ssh/id_rsa
Your public key has been saved in /c/Users/YourName/.ssh/id_rsa.pub
```

---

## 📋 Step 3: Copy the Public Key

Use this command to copy the public key:

```bash
cat ~/.ssh/id_rsa.pub
```

Then **copy the entire output** starting with `ssh-rsa` and ending with your email.

Alternatively, you can open the file with:

```bash
notepad ~/.ssh/id_rsa.pub
```

---

## 🧩 Step 4: Add SSH Key to GitLab

1. Log in to **GitLab**.
2. Go to your profile picture ➜ **Edit Profile**.
3. Navigate to **SSH Keys** (or open [https://gitlab.com/-/profile/keys](https://gitlab.com/-/profile/keys)).
4. Paste the **copied public key** into the **Key** field.
5. Give it a **title** like “Windows Laptop”.
6. Click **Add key**.

---

## 🔁 Step 5: Add SSH Key to the SSH Agent (Local Git Use)

Start the SSH agent:

```bash
eval "$(ssh-agent -s)"
```

Add your key:

```bash
ssh-add ~/.ssh/id_rsa
```

You may be prompted for the passphrase if you set one.

---

## 🧪 Step 6: Test the SSH Connection

Run:

```bash
ssh -T git@gitlab.com
```

You should see something like:

```
Welcome to GitLab, @yourusername!
```

---

## 💡 Step 7: Set Git to Use SSH for Cloning

Make sure you're cloning using **SSH**, not HTTPS.

Go to your GitLab repository ➜ Click **Clone** ➜ Select **SSH**:

```bash
git clone git@gitlab.com:yourusername/your-repo.git
```

---

## 🧼 Optional: Configure Git Username and Email

```bash
git config --global user.name "Your Name"
git config --global user.email "your_email@example.com"
```

---

## 🔁 Quick Recap

| Task             | Command/Action                                     |
| ---------------- | -------------------------------------------------- |
| Generate SSH key | `ssh-keygen -t rsa -b 4096 -C "you@example.com"`   |
| Add to agent     | `eval "$(ssh-agent -s)"` → `ssh-add ~/.ssh/id_rsa` |
| Copy key         | `cat ~/.ssh/id_rsa.pub`                            |
| Add to GitLab    | Profile ➜ SSH Keys                                 |
| Test connection  | `ssh -T git@gitlab.com`                            |

---
