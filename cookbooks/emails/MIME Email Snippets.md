# MIME Emails

```java

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class OutlookCompatibleEmail {
    public static void main(String[] args) throws Exception {
        final String username = "your_email@example.com";
        final String password = "your_password";
        final String toEmail = "recipient@example.com";

        // SMTP settings
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Outlook-Compatible HTML Email with Image");

        // 1. HTML part with inline styles and CID image
        MimeBodyPart htmlPart = new MimeBodyPart();
        String html = "<html>" +
            "<body style='font-family:Arial,sans-serif; font-size:14px; color:#333;'>" +
            "<table width='100%' cellpadding='0' cellspacing='0' border='0'>" +
            "  <tr><td>" +
            "    <h2 style='color:#2a8fd4;'>Hello from Java Mail!</h2>" +
            "    <p>This image is embedded and works in Outlook:</p>" +
            "    <img src='cid:logo_cid' width='300' height='auto' style='border:1px solid #ccc;' />" +
            "  </td></tr>" +
            "</table>" +
            "</body>" +
            "</html>";
        htmlPart.setContent(html, "text/html; charset=utf-8");

        // 2. Image body part
        MimeBodyPart imagePart = new MimeBodyPart();
        DataSource fds = new FileDataSource("path/to/logo.png"); // Replace with your image
        imagePart.setDataHandler(new DataHandler(fds));
        imagePart.setHeader("Content-ID", "<logo_cid>");
        imagePart.setFileName("logo.png");
        imagePart.setDisposition(MimeBodyPart.INLINE);

        // 3. Combine both parts
        Multipart multipart = new MimeMultipart("related"); // "related" means body and image are connected
        multipart.addBodyPart(htmlPart);
        multipart.addBodyPart(imagePart);

        message.setContent(multipart);

        Transport.send(message);

        System.out.println("Outlook-compatible email sent successfully.");
    }
}
```

---

```java

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailWithBase64Image {
    public static void main(String[] args) throws Exception {
        final String username = "your_email@example.com";
        final String password = "your_password";
        final String toEmail = "recipient@example.com";

        // Example base64 string (truncated for brevity)
        String base64Image = "iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAA...";

        // SMTP setup
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

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Email with Base64 Image (No Attachment)");

        // Embed the base64 image directly into the HTML
        String html = "<html><body style='font-family:Arial;'>"
                + "<h2>This is an inline base64 image</h2>"
                + "<img src='data:image/png;base64," + base64Image + "' "
                + "style='width:300px;border:1px solid #ccc;'/>"
                + "</body></html>";

        // Set HTML content
        message.setContent(html, "text/html; charset=utf-8");

        // Send it
        Transport.send(message);

        System.out.println("Email sent with base64 image.");
    }
}

```
---

```java
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

String path = "path/to/image.png";
byte[] bytes = Files.readAllBytes(Paths.get(path));
String base64 = Base64.getEncoder().encodeToString(bytes);
```