
```java
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    // ✅ Reads a file from resources as String
    public static String readFromFile(String fileName) throws Exception {
        Class<?> clazz = FileUtils.class;
        InputStream inputStream = clazz.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IOException("File not found: " + fileName);
        }

        return readFromInputStream(inputStream);
    }

    // ✅ Helper method to read stream into String
    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    // ✅ Writes full string content to a file (overwrites if exists)
    public static void writeToFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }

    // ✅ Writes a list of strings to file (each element becomes a line)
    public static void writeToFile(String fileName, List<String> lines) throws IOException {
        Files.write(Paths.get(fileName), lines);
    }

    public static List<String> readFilesInDirectory(String dirPath, String globPattern) throws IOException {
        List<String> fileContents = new ArrayList<>();

        Path directory = Paths.get(dirPath);
        if (!Files.isDirectory(directory)) {
            throw new IOException("Path is not a directory: " + dirPath);
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, globPattern)) {
            for (Path filePath : stream) {
                if (Files.isRegularFile(filePath)) {
                    fileContents.add(new String(Files.readAllBytes(filePath)));
                }
            }
        }

        return fileContents;
    }

  public static String readFileInDirectory(String dirPath, String fileName) throws IOException {
      Path filePath = Paths.get(dirPath, fileName);

      if (!Files.exists(filePath)) {
          throw new IOException("File not found: " + filePath);
      }
      if (!Files.isRegularFile(filePath)) {
          throw new IOException("Not a regular file: " + filePath);
      }

      return new String(Files.readAllBytes(filePath));
  }

  public static List<String> readFileInDirectory(String dirPath, String fileName) throws IOException {
      Path filePath = Paths.get(dirPath, fileName);

      if (!Files.exists(filePath)) {
          throw new IOException("File not found: " + filePath);
      }
      if (!Files.isRegularFile(filePath)) {
          throw new IOException("Not a regular file: " + filePath);
      }

      return Files.readAllLines(filePath);  // returns List<String>
  }

  public static void writeToFileAsJsonArray(String fileName, List<String> lines) throws IOException {
    Path path = Paths.get(fileName);
    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
        writer.write("[\n");

        for (int i = 0; i < lines.size(); i++) {
            writer.write(lines.get(i));
            if (i < lines.size() - 1) {
                writer.write(",\n");
            } else {
                writer.write("\n");
            }
        }

        writer.write("]");
    }
  }
}
```