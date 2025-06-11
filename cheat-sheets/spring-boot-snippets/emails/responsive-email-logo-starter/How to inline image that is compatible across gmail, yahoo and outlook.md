### ✅ Goal:
- Make the logo slightly larger on desktop (e.g., from `150px` to `180px`)
- Make it scale **down** on mobile (e.g., to `120px`)
- Keep it **responsive** and **not distorted**

---

### ✅ Updated HTML + CSS for Responsive Logo Scaling

#### ✅ Add this in your `<head>` (style block for mobile):
```html
<style>
  @media only screen and (max-width: 600px) {
    img.logo {
      width: 120px !important;
      max-width: 100% !important;
      height: auto !important;
    }
  }
</style>
```

#### ✅ Then use this HTML:
```html
<img src="https://yourdomain.com/images/logo.png"
     alt="Your Company Logo"
     class="logo"
     width="180"
     style="display:block; max-width:180px; height:auto; margin: 0 auto; border: 0;">
```

---

### 🔍 How It Works:

| Device Type      | What Happens |
|------------------|---------------|
| **Desktop**      | Logo displays at `180px` wide |
| **Mobile (≤600px)** | CSS sets it to `120px`, keeping proportions |

---

### 🧼 Pro Tips:
- ✅ Always use `max-width` and `height:auto` to avoid squishing
- ✅ `display:block` removes whitespace below the image in some clients
- ✅ Use `class="logo"` to safely target the image with media queries

