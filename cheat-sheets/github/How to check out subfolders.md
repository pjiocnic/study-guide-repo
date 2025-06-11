<h1>How to check out subfolders</h1>

To download only a specific subfolder from a GitHub repository, follow one of these methods:

---

### **Method 1: Using `svn` (No Need to Clone the Whole Repository)**
GitHub supports `svn` (Subversion), allowing you to check out a specific subdirectory.

#### **Steps:**
1. Install `svn` (if not already installed):
   - **Linux/macOS:** `sudo apt install subversion` (Ubuntu) or `brew install svn` (Mac)
   - **Windows:** Download and install [TortoiseSVN](https://tortoisesvn.net/) or use Git Bash with SVN.

2. Run the following command in a terminal or Git Bash:
   ```sh
   svn checkout https://github.com/aws-samples/amazon-bedrock-samples/trunk/agents-and-function-calling/bedrock-agents
   ```
   This will download only the `bedrock-agents` subfolder into your local directory.

---

### **Method 2: Using `git sparse-checkout` (Efficient Partial Clone)**
If you need a Git-based solution while avoiding a full clone:

#### **Steps:**
1. **Clone the repository with sparse-checkout enabled:**
   ```sh
   git clone --depth 1 --filter=blob:none --no-checkout https://github.com/aws-samples/amazon-bedrock-samples.git
   ```
2. **Enter the cloned repository:**
   ```sh
   cd amazon-bedrock-samples
   ```
3. **Enable sparse checkout and specify the subfolder:**
   ```sh
   git sparse-checkout init --cone
   git sparse-checkout set agents-and-function-calling/bedrock-agents
   ```
4. **Checkout the files:**
   ```sh
   git checkout
   ```
   This downloads only the `agents-and-function-calling/bedrock-agents` folder.

---

### **Method 3: Using GitHub’s Download ZIP Option**
If you prefer a manual approach:
1. Go to the subfolder URL in the browser:
   [https://github.com/aws-samples/amazon-bedrock-samples/tree/main/agents-and-function-calling/bedrock-agents](https://github.com/aws-samples/amazon-bedrock-samples/tree/main/agents-and-function-calling/bedrock-agents)
2. Click on **“<> Code”** (Green button).
3. Select **Download ZIP** (but this will download the whole repository).
4. Extract the ZIP and navigate to the required subfolder.

---

### **Best Option Based on Needs**
| Method | Pros | Cons |
|--------|------|------|
| `svn checkout` | Fast, downloads only the folder | Requires `svn` |
| `git sparse-checkout` | Keeps Git functionality, downloads only what’s needed | Requires `git 2.25+` |
| Download ZIP | Simple, no tools needed | Downloads the whole repo |

For your case, **Method 1 (`svn checkout`) is the easiest and fastest.** 🚀