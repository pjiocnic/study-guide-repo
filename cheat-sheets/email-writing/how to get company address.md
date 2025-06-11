Getting a **free company email address** (like `yourname@yourcompany.com`) typically requires two things:

---

### ✅ What You Need
1. **A custom domain name** (e.g., `yourcompany.com`)
2. **An email hosting provider** that supports your domain

---

### 🌟 Option 1: Use Zoho Mail (Free Plan with Custom Domain)
Zoho Mail is one of the **few providers** that still offers **free custom domain email hosting** (up to 5 users).

#### 🔧 Steps:
1. ✅ Buy a domain name (e.g., from Namecheap, Google Domains, GoDaddy, etc.)
2. ✅ Go to [Zoho Mail Free Plan](https://www.zoho.com/mail/zohomail-pricing.html)
3. ✅ Choose **Mail Free Plan** → click **Sign Up Now**
4. ✅ Verify your domain (via TXT or CNAME in DNS)
5. ✅ Create a user like `info@yourcompany.com`
6. ✅ Set up MX records to point email traffic to Zoho

#### ✅ What You Get:
- Up to 5 users
- 5GB/user
- Webmail access
- IMAP/POP support (limited)
- No ads

---

### 🌟 Option 2: Use Cloudflare Email Routing (Free Forwarding)
If you want to **receive email at `you@yourcompany.com`** and **forward it to Gmail**, this is **100% free**.

#### 🔧 Steps:
1. ✅ Register your domain (e.g., `yourcompany.com`)
2. ✅ Sign up at [Cloudflare](https://cloudflare.com)
3. ✅ Add your domain and point your nameservers to Cloudflare
4. ✅ Use Cloudflare's **Email Routing** to:
   - Create an alias `you@yourcompany.com`
   - Forward to `yourname@gmail.com`
5. ❌ You **cannot send** from Gmail using `you@yourcompany.com` unless you use SMTP (like Amazon SES)

---

### 🌟 Option 3: Use Gmail With Domain (Not Free)
Google Workspace lets you send from `you@yourcompany.com` via Gmail UI.

- Cost: **$6/user/month**
- Site: [Google Workspace](https://workspace.google.com/)

---

### 📌 Summary Table

| Option              | Free? | Custom Domain? | Can Send? | Can Receive? | Notes |
|---------------------|-------|----------------|-----------|--------------|-------|
| **Zoho Mail Free**  | ✅     | ✅              | ✅         | ✅            | Great full-featured option |
| **Cloudflare Routing** | ✅  | ✅              | ❌ (forward only) | ✅      | Free receive-only forwarding |
| **Google Workspace** | ❌ ($6/mo) | ✅         | ✅         | ✅            | Full Gmail interface |

---

Would you like me to walk you through Zoho or Cloudflare setup step-by-step for your domain?