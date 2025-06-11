
## 1. Using streams API to map results to custom DTO

```java
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class NativeQueryExample {
    private EntityManager entityManager;

    public NativeQueryExample(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<UserOrderDTO> getUserOrderDetails(Long userId, Long orderId) {
        String sql = "SELECT u.username, o.order_name " +
                     "FROM users u " +
                     "JOIN orders o ON u.id = o.user_id " +
                     "WHERE u.id = :userId AND o.id = :orderId";

        Query query = entityManager.createNativeQuery(sql);

        // Set parameters
        query.setParameter("userId", userId);
        query.setParameter("orderId", orderId);

        // Map the results to UserOrderDTO using Streams API
        List<Object[]> results = query.getResultList();
        return results.stream()
                .map(result -> new UserOrderDTO((String) result[0], (String) result[1]))
                .collect(Collectors.toList());
    }
}
```

### 1.1 Example Usage

```java
EntityManager em = ...; // Obtain EntityManager
NativeQueryExample example = new NativeQueryExample(em);

// Example values for parameters
Long userId = 1L;
Long orderId = 101L;

List<UserOrderDTO> userOrderDetails = example.getUserOrderDetails(userId, orderId);

for (UserOrderDTO dto : userOrderDetails) {
    System.out.println("Username: " + dto.getUsername() + ", Order: " + dto.getOrderName());
}
```

# Mapping using EclipseLink's SqlResultSetMapping

In EclipseLink, there isn't a built-in equivalent to Hibernate's `ResultTransformer`, but you can still make use of EclipseLink's **`@ConstructorResult`** mapping or write a reusable utility for mapping results to DTOs.

Here are some options tailored for **EclipseLink**:

---

### **Option 1: EclipseLink `@ConstructorResult` with `SqlResultSetMapping`**

EclipseLink allows mapping native query results to DTOs using `@SqlResultSetMapping` and `@ConstructorResult`.

#### Step 1: Define the DTO Class
Ensure your DTO class has a constructor matching the query results.

```java
public class UserOrderDTO {
    private String username;
    private String orderName;

    public UserOrderDTO(String username, String orderName) {
        this.username = username;
        this.orderName = orderName;
    }

    // Getters and setters (if needed)
    public String getUsername() {
        return username;
    }

    public String getOrderName() {
        return orderName;
    }
}
```

#### Step 2: Define a `SqlResultSetMapping`
You can annotate your entity or configure this mapping in the `orm.xml` file.

```java
@SqlResultSetMapping(
    name = "UserOrderDTOMapping",
    classes = @ConstructorResult(
        targetClass = UserOrderDTO.class,
        columns = {
            @ColumnResult(name = "username", type = String.class),
            @ColumnResult(name = "orderName", type = String.class)
        }
    )
)
@Entity // Dummy entity to house the annotation
public class User {
    @Id
    private Long id;
}
```

#### Step 3: Create and Execute the Native Query
Reference the mapping by name in your `createNativeQuery` call.

```java
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class NativeQueryExample {
    private EntityManager entityManager;

    public NativeQueryExample(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<UserOrderDTO> getUserOrderDetails(Long userId, Long orderId) {
        String sql = "SELECT u.username, o.order_name " +
                     "FROM users u " +
                     "JOIN orders o ON u.id = o.user_id " +
                     "WHERE u.id = :userId AND o.id = :orderId";

        Query query = entityManager.createNativeQuery(sql, "UserOrderDTOMapping");
        query.setParameter("userId", userId);
        query.setParameter("orderId", orderId);

        return query.getResultList();
    }
}
```

---

### **Option 2: Custom Utility Method**

If `@SqlResultSetMapping` feels restrictive or verbose, you can use a **custom mapping utility** similar to the one described earlier. This approach is compatible with EclipseLink.

#### Utility for Reusable Mapping
```java
import java.util.List;
import java.util.stream.Collectors;

public class QueryResultMapper {
    public static <T> List<T> mapResults(List<Object[]> results, ResultMapper<T> mapper) {
        return results.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }
}

@FunctionalInterface
public interface ResultMapper<T> {
    T map(Object[] result);
}
```

#### Using the Utility in Your Code
```java
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class NativeQueryExample {
    private EntityManager entityManager;

    public NativeQueryExample(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<UserOrderDTO> getUserOrderDetails(Long userId, Long orderId) {
        String sql = "SELECT u.username, o.order_name " +
                     "FROM users u " +
                     "JOIN orders o ON u.id = o.user_id " +
                     "WHERE u.id = :userId AND o.id = :orderId";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId", userId);
        query.setParameter("orderId", orderId);

        List<Object[]> results = query.getResultList();

        return QueryResultMapper.mapResults(results, result ->
            new UserOrderDTO((String) result[0], (String) result[1])
        );
    }
}
```

---

### **Option 3: Dynamic DTO Mapping with EclipseLink's `ResultSet`**

If you need more control, you can fetch the results as a `ResultSet` and manually map them. This is less elegant but works if no better option is available.

```java
import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NativeQueryExample {
    private EntityManager entityManager;

    public NativeQueryExample(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<UserOrderDTO> getUserOrderDetails(Long userId, Long orderId) throws SQLException {
        String sql = "SELECT u.username, o.order_name " +
                     "FROM users u " +
                     "JOIN orders o ON u.id = o.user_id " +
                     "WHERE u.id = ? AND o.id = ?";

        // Use EclipseLink's JDBC capabilities
        javax.persistence.Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, userId);
        query.setParameter(2, orderId);

        ResultSet rs = (ResultSet) query.unwrap(java.sql.Statement.class).executeQuery();

        List<UserOrderDTO> dtoList = new ArrayList<>();
        while (rs.next()) {
            String username = rs.getString("username");
            String orderName = rs.getString("order_name");
            dtoList.add(new UserOrderDTO(username, orderName));
        }
        return dtoList;
    }
}
```

---

### Recommendation

- Use **Option 1** with `@SqlResultSetMapping` for a clean, declarative approach.
- Use **Option 2** if you want a reusable utility method without heavy configuration.
- Avoid **Option 3** unless you need raw `ResultSet` access for complex queries.

Let me know if you’d like further assistance setting up any of these approaches!