
# 📖 Java HTML Email Cookbook with Embedded Images

This guide includes working Java examples for sending HTML emails with embedded images, covering:

1. [Embedded CID Images (Outlook-compatible)](#section1)
2. [Base64-Encoded Images in HTML](#section2)
3. [Convert Image to Base64 in Java](#section3)
4. [Multiple Embedded Images with Spacing and Layout](#section4)

---

<a name="section1"></a>
## 🧩 Section 1: Outlook-Compatible HTML Email with Embedded CID Image

Embed an image from inside a JAR (e.g., `/resources/logo.png`) using CID (Content-ID).

```java
import java.util.Properties;
import java.io.InputStream;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

public class OutlookCompatibleEmail {
    public static void main(String[] args) throws Exception {
        final String username = "your_email@example.com";
        final String password = "your_password";
        final String toEmail = "recipient@example.com";

        // 1. Setup SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // 2. Compose HTML with inline CID image
        MimeBodyPart htmlPart = new MimeBodyPart();
        String html = "<html><body style='font-family:Arial;'>"
            + "<h2>Hello from Java Mail!</h2>"
            + "<p>This image is embedded and works in Outlook:</p>"
            + "<img src='cid:logo_cid' style='width:300px; border:1px solid #ccc;'/>"
            + "</body></html>";
        htmlPart.setContent(html, "text/html; charset=utf-8");

        // 3. Load image from JAR and attach inline
        InputStream imageStream = OutlookCompatibleEmail.class.getResourceAsStream("/logo.png");
        if (imageStream == null) throw new RuntimeException("Image not found!");

        MimeBodyPart imagePart = new MimeBodyPart();
        DataSource fds = new ByteArrayDataSource(imageStream, "image/png");
        imagePart.setDataHandler(new DataHandler(fds));
        imagePart.setHeader("Content-ID", "<logo_cid>");
        imagePart.setDisposition(MimeBodyPart.INLINE);

        // 4. Create multipart and send
        Multipart multipart = new MimeMultipart("related");
        multipart.addBodyPart(htmlPart);
        multipart.addBodyPart(imagePart);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Outlook-Compatible HTML Email with Image");
        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Email sent with CID image.");
    }
}
```

---

<a name="section2"></a>
## 🧩 Section 2: HTML Email with Base64 Image (No Attachment)

Directly embed an image using base64 — no attachment or CID needed. Best for webmail clients, less reliable in older Outlook.

```java
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailWithBase64Image {
    public static void main(String[] args) throws Exception {
        final String username = "your_email@example.com";
        final String password = "your_password";
        final String toEmail = "recipient@example.com";

        String base64Image = "iVBORw0KGgoAAAANSUhEUgAAAGQAAABk..."; // Truncated

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        String html = "<html><body style='font-family:Arial;'>"
            + "<h2>This is a base64 image</h2>"
            + "<img src='data:image/png;base64," + base64Image + "' style='width:300px;border:1px solid #ccc;'/>"
            + "</body></html>";

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Base64 Image Email");
        message.setContent(html, "text/html; charset=utf-8");

        Transport.send(message);
        System.out.println("Base64 email sent.");
    }
}
```

---

<a name="section3"></a>
## 🧩 Section 3: Convert Local Image to Base64 in Java

Use this utility method to generate a base64 string for an image file:

```java
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

String path = "path/to/image.png";
byte[] bytes = Files.readAllBytes(Paths.get(path));
String base64 = Base64.getEncoder().encodeToString(bytes);
```

---

<a name="section4"></a>
## 🧩 Section 4: Multi-line Text + Multiple Images + Spacer (Outlook-Compatible)

Formatted layout with two inline images and a spacing row between them.

```java
import java.util.Properties;
import java.io.InputStream;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

public class OutlookCompatibleMutiImageEmail {
    public static void main(String[] args) throws Exception {
        final String username = "your_email@example.com";
        final String password = "your_password";
        final String toEmail = "recipient@example.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        String html = "<html><body style='font-family:Arial,sans-serif;font-size:14px;'>"
            + "<table width='100%' cellpadding='0' cellspacing='0'>"
            + "<tr><td style='padding:10px;'>"
            + "<p>Dear Team,</p><p>Here’s the latest update with visuals:</p>"
            + "</td></tr>"
            + "<tr><td align='center'><img src='cid:image1' style='max-width:600px; border:1px solid #ccc;'/></td></tr>"
            + "<tr><td style='height:20px;'>&nbsp;</td></tr>"
            + "<tr><td align='center'><img src='cid:image2' style='max-width:600px; border:1px solid #ccc;'/></td></tr>"
            + "</table></body></html>";

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(html, "text/html; charset=utf-8");

        MimeBodyPart imagePart1 = createImagePartFromResource("logo1.png", "image1");
        MimeBodyPart imagePart2 = createImagePartFromResource("logo2.png", "image2");

        Multipart multipart = new MimeMultipart("related");
        multipart.addBodyPart(htmlPart);
        multipart.addBodyPart(imagePart1);
        multipart.addBodyPart(imagePart2);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Multi-line Text with Two Images");
        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Multi-image email sent.");
    }

    private static MimeBodyPart createImagePartFromResource(String fileName, String contentId) throws Exception {
        MimeBodyPart imagePart = new MimeBodyPart();
        InputStream imageStream = OutlookCompatibleMutiImageEmail.class.getResourceAsStream("/" + fileName);
        if (imageStream == null) throw new RuntimeException("Image " + fileName + " not found!");
        DataSource fds = new ByteArrayDataSource(imageStream, "image/png");
        imagePart.setDataHandler(new DataHandler(fds));
        imagePart.setHeader("Content-ID", "<" + contentId + ">");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        return imagePart;
    }
}
```

---

Let me know if you'd like to add:

- 📬 Downloadable `.eml` saving
- 🖼️ Image captions
- 🔘 CTA buttons
- 📄 PDF/attachment support

I can expand this cookbook accordingly!