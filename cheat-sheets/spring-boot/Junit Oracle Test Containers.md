Here’s an example of how you can set up and write a Spring Boot JUnit test case for testing against an Oracle database using **Testcontainers** to spin up an Oracle database instance, along with Spring's `@DataJpaTest`.

### Maven Dependencies
Ensure you have the necessary dependencies in your `pom.xml`:

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

    <!-- Testcontainers for Oracle -->
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>oracle-xe</artifactId>
        <version>1.19.0</version>
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

### Oracle TestContainer Configuration
First, set up a TestContainer for Oracle XE database.

```java
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.OracleContainer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OracleDatabaseTest {

    private static OracleContainer oracleContainer;

    @Autowired
    private DataSource dataSource;

    @BeforeAll
    static void startOracleContainer() {
        oracleContainer = new OracleContainer("gvenzl/oracle-xe:21-slim-faststart")
                .withUsername("system")
                .withPassword("oracle")
                .withDatabaseName("testdb");

        oracleContainer.start();

        // Set JDBC properties for the tests
        System.setProperty("spring.datasource.url", oracleContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", oracleContainer.getUsername());
        System.setProperty("spring.datasource.password", oracleContainer.getPassword());
    }

    @Test
    void testConnection() throws Exception {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            // Perform a simple query to ensure the connection works
            ResultSet rs = stmt.executeQuery("SELECT 1 FROM DUAL");
            assertThat(rs.next()).isTrue();
            assertThat(rs.getInt(1)).isEqualTo(1);
        }
    }

    // Additional repository tests can go here
}
```

### Breakdown of the Test

1. **OracleContainer Configuration**:
   - We use Testcontainers' `OracleContainer` to start an Oracle XE database instance in Docker.
   - `System.setProperty` is used to override the datasource properties, ensuring Spring Boot uses this test database.

2. **@DataJpaTest**:
   - This annotation is used to configure only the JPA-related components (like repositories) without starting the entire Spring context.

3. **Connection Test**:
   - A simple test that validates the connection by executing a `SELECT 1 FROM DUAL` query to ensure the Oracle container is running and accessible.

### Test Repository with Oracle Database

You can extend the test to cover your actual repositories:

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveStudent() {
        Student student = new Student();
        student.setName("John Doe");
        studentRepository.save(student);

        assertThat(studentRepository.findByName("John Doe")).isNotNull();
    }
}
```

This test validates the integration of your JPA repository with the Oracle database.