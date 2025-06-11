```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseExample {

    public static String getStringFromDatabase(String parameter) {
        String result = null;
        String query = "SELECT column_name FROM table_name WHERE some_column = ?";

        // Update with your database details
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Bind the parameter
            preparedStatement.setString(1, parameter);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Extract result if available
                if (resultSet.next()) {
                    result = resultSet.getString("column_name");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception or handle as needed
        }

        return result;
    }

    public static void main(String[] args) {
        String param = "example_value";
        String result = getStringFromDatabase(param);
        System.out.println("Result: " + result);
    }
}
```