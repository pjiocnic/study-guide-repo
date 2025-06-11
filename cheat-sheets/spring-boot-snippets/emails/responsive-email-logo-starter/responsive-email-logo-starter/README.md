# Responsive Email Logo Starter

This is a starter HTML email template that includes a responsive logo compatible with Gmail, Yahoo Mail, and Outlook clients.

## ✅ Features
- Responsive logo that scales across devices
- No image attachments
- Mobile-friendly layout with inline-safe styles
- Works in Gmail, Yahoo, Outlook (desktop and web)

## 📂 Files
- `index.html` – The responsive email template
- `README.md` – This usage guide

## 🛠 How to Use
1. Replace the logo URL in the HTML with your hosted image URL:
   ```
   https://yourdomain.com/images/logo.png
   ```
2. Replace `{{name}}` with your template variable (e.g., `${name}` in FreeMarker).
3. Send the email using your preferred backend (e.g., JavaMailSender, NodeMailer, etc.).

## 🧪 How to Test
- Send it to your Gmail, Yahoo, and Outlook addresses.
- View on both desktop and mobile.
- Use tools like [Litmus](https://www.litmus.com/) or [Email on Acid](https://www.emailonacid.com/) for professional previews.

## 🔒 Tips
- Host your logo on a fast, public URL (e.g., S3, CDN).
- Do not use `cid:` or base64 for images in mixed-client emails.
