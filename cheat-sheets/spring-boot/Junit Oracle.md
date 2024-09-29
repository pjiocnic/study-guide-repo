<h1>Testing against Oracle Database</h1>

Here's how you can set up your Spring Boot JUnit test to work with the external Oracle database:

### 1. Maven Dependencies
Make sure you have the necessary dependencies:

```xml
<dependencies>
    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Oracle JDBC Driver -->
    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc8</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Spring Boot Starter Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- JUnit Jupiter for Spring Boot -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 2. Configuration for External Oracle Database
In your `application-test.properties` (or `application.yml`), configure the connection to your external Oracle database:

```properties
# application-test.properties

spring.datasource.url=jdbc:oracle:thin:@//<oracle-host>:<oracle-port>/<oracle-db-name>
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect
```

This ensures that your tests use the external Oracle database.

### 3. Sample Test Class
Here's an example of a JUnit test that connects to the external Oracle database and performs basic operations using a Spring Data JPA repository.

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ContextConfiguration(classes = {YourAppConfig.class})
class OracleExternalDatabaseTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testConnection() throws Exception {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            // Simple query to test connection
            ResultSet rs = stmt.executeQuery("SELECT 1 FROM DUAL");
            assertThat(rs.next()).isTrue();
            assertThat(rs.getInt(1)).isEqualTo(1);
        }
    }
}
```

### Explanation:

1. **`@DataJpaTest`**: This annotation brings up only the components related to JPA for testing, like repositories, and doesn't start the entire Spring Boot application.

2. **`@TestPropertySource`**: It specifies which properties file (in this case, `application-test.properties`) should be loaded to provide the external database configuration.

3. **`testConnection()`**: This method tests the connection to the Oracle database by executing a simple query (`SELECT 1 FROM DUAL`).

### 4. Testing a Repository
To test your repository against the external Oracle database, you can extend the test as follows:

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveAndFindStudent() {
        Student student = new Student();
        student.setName("Jane Doe");
        studentRepository.save(student);

        Student foundStudent = studentRepository.findByName("Jane Doe");
        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getName()).isEqualTo("Jane Doe");
    }
}
```

This test saves a `Student` entity to your Oracle database and verifies that it can be retrieved. Make sure the external database has the necessary tables and schema set up beforehand.

### Tips:
- Run migrations or ensure the external database has the correct schema before running tests.
- Use a separate schema or dedicated database for testing purposes to avoid corrupting production data.