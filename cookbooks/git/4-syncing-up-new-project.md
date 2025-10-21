
```bash
cd  your-repo
git init

git remote add origin git@gitlab.com:yourname/yourproject.git
git remote -v

# Use the correct branch name: master or main
git checkout master          # or: git checkout main
git fetch origin
git merge origin/master --allow-unrelated-histories  # or origin/main

# If conflicts appear:
#   edit files to resolve
git add <resolved files>
git commit                   # finalizes the merge
git push -u origin master    # or main
```