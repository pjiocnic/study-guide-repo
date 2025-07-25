Sure! Here's a **nicely formatted version** of your Git commands categorized under three sections:

---

### ✅ **Create a New Repository**

```bash
# Clone the empty repository
git clone git@gitlab.com:example/example.git

# Navigate to the repo
cd example

# Create main branch and a README file
git switch -c main
touch README.md

# Stage and commit the file
git add README.md
git commit -m "add README"

# Push to remote
git push -u origin main
```

---

### 📂 **Push an Existing Folder**

```bash
# Navigate to your existing folder
cd existing_folder

# Initialize Git with main branch
git init --initial-branch=main

# Add remote origin
git remote add origin git@gitlab.com:example/example.git

# Stage and commit everything
git add .
git commit -m "Initial commit"

# Push to remote
git push -u origin main
```

---

### 🔁 **Push an Existing Git Repository**

```bash
# Navigate to your repo
cd existing_repo

# Rename old remote if it exists
git remote rename origin old-origin

# Add new GitLab remote
git remote add origin git@gitlab.com:example/example.git

# Push all branches
git push -u origin --all

# Push all tags
git push -u origin --tags
```

---

