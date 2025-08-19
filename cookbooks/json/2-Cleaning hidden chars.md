What you’re seeing in Notepad++ (`SUB`, `SUBSUB`, etc.) are **control characters** (non-printable characters) in your JSON.

* `SUB` specifically refers to **ASCII 26 (0x1A)**, known as the *Substitute* character.
* These are invisible in many editors, but Notepad++ shows them as markers like `SUB`, `NUL`, etc.
* They often appear if the JSON came from a bad export, copy-paste, or if binary/non-UTF8 data was mixed into the text.

---

## How to Remove Hidden Control Characters in Java 1.8

You can strip all non-printable ASCII control characters (except line breaks, tabs if you want to keep them) before processing your JSON.

### Example Java 1.8 Code

```java
import java.nio.file.Files;
import java.nio.file.Paths;

public class CleanJson {
    public static void main(String[] args) throws Exception {
        // Read JSON as string
        String json = new String(Files.readAllBytes(Paths.get("input.json")), "UTF-8");

        // Regex to remove control characters:
        // \p{Cntrl} = control characters
        // Exclude common whitespace (\t, \n, \r) if you want to preserve formatting
        String cleaned = json.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

        // Write cleaned output
        Files.write(Paths.get("cleaned.json"), cleaned.getBytes("UTF-8"));

        System.out.println("Hidden control characters removed and saved to cleaned.json");
    }
}
```

---

### Explanation of Regex

* `[\\p{Cntrl}]` → matches any control character (`ASCII 0–31` and `127`).
* `&&[^\r\n\t]` → allows exceptions (`\r`, `\n`, and `\t`) so newlines/tabs stay intact.
* So `"replaceAll(..., "")` deletes everything else (like `SUB`).

---

✅ After running this, your JSON will be “cleaned” and parseable by Jackson, Gson, etc.

---

Would you like me to extend this so that it **automatically validates the JSON after cleanup** (e.g., using Jackson), and stops if the file is still corrupted? That way you know whether the cleanup succeeded or if the file itself is malformed.
