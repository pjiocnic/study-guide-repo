Below is an example of a simple Java program that reads a properties file bundled within a JAR using Maven. The properties file will be included in the `resources` directory, which Maven automatically packages into the JAR.

---

### Step 1: Project Setup

Create a Maven project with the following structure:

```
my-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── App.java
│   │   └── resources/
│   │       └── config.properties
├── pom.xml
```

---

### Step 2: Code the Java Program

Create the `App.java` file in `src/main/java/com/example/`:

```java
package com.example;

import java.io.*;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar my-app-1.0-SNAPSHOT.jar <input-file-path>...");
            return;
        }

        for (String filePath : args) {
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                System.out.println("Error: File not found - " + filePath);
                continue;
            }

            System.out.println("Reading file: " + filePath);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // Process each line as needed
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + filePath);
                e.printStackTrace();
            }
        }

        try (InputStream input = App.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            // Load properties
            Properties prop = new Properties();
            prop.load(input);

            // Access properties
            System.out.println("App Name: " + prop.getProperty("app.name"));
            System.out.println("App Version: " + prop.getProperty("app.version"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

### Step 3: Create the Properties File

Create the `config.properties` file in `src/main/resources/`:

```properties
app.name=MyApp
app.version=1.0.0
```

---

### Step 4: Update `pom.xml`

Ensure the `pom.xml` has the correct configurations for building the JAR:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0-SNAPSHOT</version>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.2</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.example.App</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

### Step 5: Build the JAR

1. Navigate to the project root.
2. Run the following command to package the JAR:

   ```bash
   mvn clean package
   ```

This creates a JAR file in the `target` directory, e.g., `my-app-1.0-SNAPSHOT.jar`.

---

### Step 6: Run the JAR

Run the JAR file using the `java` command:

```bash
java -jar target/my-app-1.0-SNAPSHOT.jar
```

---

### Output

When you run the JAR, the output should be:

```
App Name: MyApp
App Version: 1.0.0
```

---

### Shell Script: `run_app.sh`

```bash
#!/bin/bash

# Define the path to the JAR file
JAR_FILE="target/my-app-1.0-SNAPSHOT.jar"

# Check if the JAR file exists
if [ ! -f "$JAR_FILE" ]; then
  echo "Error: $JAR_FILE not found. Please build the project first using 'mvn clean package'."
  exit 1
fi

# Check if input file paths are provided
if [ "$#" -lt 1 ]; then
  echo "Usage: $0 <input-file-path>..."
  exit 1
fi

# Run the JAR file with input file paths
nohup java -jar "$JAR_FILE" "$@" > app.log 2>&1 &

# Get the process ID (PID) of the last background command
PID=$!

# Inform the user
echo "Application started with PID $PID. Logs are being written to app.log."
echo "To stop the application, use: kill $PID"
```

---

### Usage

1. **Make the Script Executable**:

   ```bash
   chmod +x run_app.sh
   ```

2. **Run the Script**:

   ```bash
   ./run_app.sh
   ```

3. **Stop the Application**:

   Use the `kill` command with the PID printed by the script:

   ```bash
   kill <PID>
   ```