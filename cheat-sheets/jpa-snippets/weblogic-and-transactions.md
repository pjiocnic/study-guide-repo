It sounds like you're running into an issue with Eclipse JPA when executing a **native query update** followed by a **commit**. Could you provide more details on the problem? Some common issues include:

1. **Transaction Handling**
   - If you're running an `UPDATE` statement using a native query, ensure that you begin and commit the transaction properly.
   - Example:
     ```java
     EntityManager em = entityManagerFactory.createEntityManager();
     EntityTransaction tx = em.getTransaction();
     try {
         tx.begin();
         Query query = em.createNativeQuery("UPDATE users SET name = ? WHERE id = ?");
         query.setParameter(1, "John Doe");
         query.setParameter(2, 1);
         query.executeUpdate();
         tx.commit();
     } catch (Exception e) {
         tx.rollback();
         e.printStackTrace();
     } finally {
         em.close();
     }
     ```

2. **Flush Mode**
   - Ensure that the changes are flushed before commit.
   - Try calling `entityManager.flush()` before `commit()`.

3. **Auto-commit vs. Manual Transactions**
   - If using a container-managed entity manager, ensure that transactions are properly handled by the application server.

4. **Cache Issues**
   - If updates are not reflecting, try `entityManager.clear()` or `entityManager.refresh(entity)`.
   - Example:
     ```java
     entityManager.clear(); // Clears persistence context
     ```

5. **Database Locking Issues**
   - If multiple transactions are involved, check if there's a lock preventing the update.

6. **Query Execution Failure**
   - Ensure that the native SQL query is correct and logs don't show syntax or constraint errors.

Can you clarify the exact issue you're facing (e.g., update not reflecting, transaction rollback, locking, etc.)?

---

Since you're using **Eclipse JPA in a WebLogic container-managed environment**, here are some key things to check when running a **native update query followed by a commit**:

### 1. **Ensure Transaction Management is Handled by WebLogic**
   - In a container-managed environment, WebLogic manages transactions automatically. Do **not** manually begin or commit transactions. Instead, ensure the method is annotated with `@Transactional` (if using CDI) or handled by the EJB transaction settings.

   ```java
   @PersistenceContext
   private EntityManager entityManager;

   public void updateUser(Long userId, String newName) {
       Query query = entityManager.createNativeQuery("UPDATE users SET name = ? WHERE id = ?");
       query.setParameter(1, newName);
       query.setParameter(2, userId);
       query.executeUpdate();
   }
   ```
   - If this method is inside an **EJB** or a **CDI bean**, the transaction should be automatically committed when the method returns.

### 2. **Flush Changes Manually (If Needed)**
   - In some cases, WebLogic may defer execution of native queries. If the update is not reflected immediately, explicitly flush:

   ```java
   entityManager.flush();
   ```

### 3. **Check for Caching Issues**
   - If you’re not seeing updates immediately:
     - Clear the persistence context:
       ```java
       entityManager.clear();
       ```
     - Ensure the query is executed against the database and not relying on cached data.
     - Use `@Cacheable(false)` for entities if needed.

### 4. **Use `@Transactional` for Spring-Based Applications**
   - If your project uses **Spring on WebLogic**, mark your method as transactional:

   ```java
   @Transactional
   public void updateUser(Long userId, String newName) {
       Query query = entityManager.createNativeQuery("UPDATE users SET name = ? WHERE id = ?");
       query.setParameter(1, newName);
       query.setParameter(2, userId);
       query.executeUpdate();
   }
   ```

### 5. **Verify Data Source Configuration**
   - If you're using a **JTA data source**, ensure it's properly configured in `persistence.xml`:

   ```xml
   <persistence-unit name="myPU" transaction-type="JTA">
       <jta-data-source>jdbc/myDataSource</jta-data-source>
   </persistence-unit>
   ```

   - If using **RESOURCE_LOCAL** transactions, WebLogic may not commit automatically.

### 6. **Check WebLogic Logs**
   - If updates are not persisting, check for **transaction rollbacks** in WebLogic logs:
     ```
     <BEA-XXXXX> Transaction rolled back due to...
     ```

Would you like help debugging specific logs or configuring your WebLogic transaction settings?
---

If you are seeing updates but your DBAs are **not seeing commits**, it likely means WebLogic's **transaction management** is handling the commit differently than expected. Here are a few things to check and troubleshoot:

---

### **1. Ensure You're Using a JTA Data Source**
Since WebLogic uses **container-managed transactions**, ensure that your **JPA persistence unit** is configured with a **JTA-managed data source**:

#### *Check `persistence.xml`:*
```xml
<persistence-unit name="myPU" transaction-type="JTA">
    <jta-data-source>jdbc/MyDataSource</jta-data-source>
</persistence-unit>
```
- If you're using a **non-JTA data source**, WebLogic may not commit properly.

---

### **2. Check if Updates Are in a Different Transaction**
If your DBAs **don't see the commits**, your updates might be occurring inside a different transaction scope that is not committed.

#### *Use `@TransactionAttribute(REQUIRES_NEW)` in EJBs (for immediate commit):*
```java
@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateUser(Long userId, String newName) {
        Query query = entityManager.createNativeQuery("UPDATE users SET name = ? WHERE id = ?");
        query.setParameter(1, newName);
        query.setParameter(2, userId);
        query.executeUpdate();
    }
}
```
- This ensures the update occurs in a **new transaction** and commits immediately.

---

### **3. Force a Flush Before Exiting the Method**
WebLogic may defer execution of native queries until the transaction commits. To force execution:

```java
entityManager.flush();
```
- This ensures the query is immediately sent to the database.

---

### **4. Verify Transaction Logs in WebLogic**
Check WebLogic logs for **transaction rollback warnings**. If transactions are being rolled back, you'll see something like:

```
<BEA-XXXXXXX> Transaction rolled back due to...
```
Run this SQL query in the database to check for **uncommitted transactions**:

```sql
SELECT * FROM v$transaction;
```
- If you see your transaction **still open**, WebLogic might be waiting to commit.

---

### **5. Check WebLogic Transaction Timeout Settings**
If WebLogic is not committing, your transaction might be **timing out before the commit happens**.

#### *Increase WebLogic transaction timeout (via console):*
1. Go to **WebLogic Admin Console**
2. Navigate to:
   **Domain → Services → JTA → Timeout (seconds)**
3. Increase the transaction timeout (default is **30 seconds**)

---

### **6. Verify Auto-Commit at the Database Level**
Some databases require explicit commit handling, and WebLogic might not be configured to auto-commit correctly.

- Check WebLogic JDBC Data Source settings:
  - **Uncheck** "Supports Global Transactions" if you don't want WebLogic managing commits.
  - Ensure **"Emulate Two-Phase Commit"** is enabled for JTA transactions.

- Check the database setting:
  ```sql
  SHOW VARIABLES LIKE 'autocommit';
  ```
  - If `autocommit = OFF`, you might need to commit manually in WebLogic.

---

### **7. Try Explicit Transaction Control (As a Last Resort)**
If nothing else works, try explicitly managing the transaction (though this is **not** recommended in a container-managed setup):

```java
@PersistenceContext
private EntityManager entityManager;

@Resource
private UserTransaction userTransaction;

public void updateUser(Long userId, String newName) {
    try {
        userTransaction.begin();
        Query query = entityManager.createNativeQuery("UPDATE users SET name = ? WHERE id = ?");
        query.setParameter(1, newName);
        query.setParameter(2, userId);
        query.executeUpdate();
        userTransaction.commit();
    } catch (Exception e) {
        userTransaction.rollback();
        e.printStackTrace();
    }
}
```
- This **forces** a commit, even if WebLogic is delaying it.

---

### **Summary of Fixes to Try**
✔️ **Ensure JPA is using a JTA Data Source**
✔️ **Use `REQUIRES_NEW` to force an immediate commit**
✔️ **Call `flush()` before the method exits**
✔️ **Check WebLogic logs for transaction rollbacks**
✔️ **Increase WebLogic transaction timeout**
✔️ **Check WebLogic JDBC auto-commit settings**
✔️ **As a last resort, use `UserTransaction` for manual commit**

Would you like help debugging WebLogic logs or checking specific database settings? 🚀

---

Yes! You can configure **Eclipse JPA (EclipseLink) in WebLogic** to log **SQL statements along with commits** by adding specific properties to your `persistence.xml`.

---

## **🔧 Step 1: Enable SQL Logging in `persistence.xml`**
Modify your `persistence.xml` to include the following **properties** inside the `<properties>` section:

```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.1">
    <persistence-unit name="myPU" transaction-type="JTA">
        <jta-data-source>jdbc/MyDataSource</jta-data-source>

        <properties>
            <!-- Enable SQL Logging -->
            <property name="eclipselink.logging.level.sql" value="FINE"/>

            <!-- Log SQL Parameters -->
            <property name="eclipselink.logging.parameters" value="true"/>

            <!-- Log Transactions (Begin, Commit, Rollback) -->
            <property name="eclipselink.logging.level.transaction" value="FINE"/>

            <!-- Show Connection Details -->
            <property name="eclipselink.logging.level.connection" value="FINE"/>

            <!-- Print Log to Console -->
            <property name="eclipselink.logging.logger" value="DefaultLogger"/>
        </properties>
    </persistence-unit>
</persistence>
```

---

## **🔍 Step 2: What This Will Log**
With these settings, WebLogic will **log SQL statements, parameters, and transaction details** in the persistence log.

For example, in the **WebLogic server logs**, you’ll see:
```
[EL Fine]: SQL: UPDATE users SET name = ? WHERE id = ?
	bind => [John Doe, 1]

[EL Fine]: txn: begin transaction

[EL Fine]: txn: commit transaction
```

---

## **🛠 Step 3: Where to Find Logs in WebLogic**
1. **In WebLogic Console:**
   - Navigate to **Servers → [Your Server] → Logging**
   - Set the log level to `FINE` or `FINEST` for detailed output.

2. **In WebLogic Log Files:**
   - Look for **`server.log`** or **`WebLogicConsole.log`** in the **logs directory** of your WebLogic domain.

3. **If Using a Custom Log File:**
   - Add this property to `persistence.xml` to redirect logging:
     ```xml
     <property name="eclipselink.logging.file" value="D:/logs/jpa.log"/>
     ```

---

## **📢 Optional: Log Using Java Util Logging (`logging.properties`)**
If you prefer **Java Util Logging (JUL)**, configure **`logging.properties`** instead:

- Edit `domain/config/logging.properties` and add:
  ```
  java.util.logging.ConsoleHandler.level = FINE
  org.eclipse.persistence.logging.level = FINE
  ```
- Restart WebLogic.

---

## **🧐 Next Steps**
- **If you don’t see commits in logs:** Check if **WebLogic is deferring transactions** until the request completes.
- **If logs are too noisy:** Change `FINE` to `INFO` or `WARNING` in `persistence.xml`.

Would you like help verifying this in WebLogic logs? 🚀

---

Yes, a **new transaction will start in `methodB`** despite `methodA` having `@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)`. Here's why:

---

### **🔍 How WebLogic Handles This Transaction Flow**
1. **`methodA` (`@TransactionAttribute.NOT_SUPPORTED`)**
   - This means **if a transaction exists, it is suspended**, and `methodA` runs **without** a transaction.
   - If there is no active transaction, `methodA` just runs in a **non-transactional** context.

2. **Calling `methodB` via `session.getBusinessObject(ClassA.class)`**
   - This ensures that `methodB` is called on the **EJB proxy**, meaning WebLogic will apply the transactional attributes correctly.

3. **`methodB` (`@TransactionAttribute.REQUIRES_NEW`)**
   - Since `methodB` is marked as `REQUIRES_NEW`, WebLogic **creates a new transaction** for this method, regardless of whether a transaction exists or not.
   - Since `methodA` was running **without** a transaction, `methodB` will **start a fresh new transaction**.

---

### **🛠 Example Code:**
#### **Singleton EJB**
```java
@Singleton
public class ClassA {

    @Resource
    private SessionContext sessionContext;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void methodA() {
        System.out.println("methodA: Running WITHOUT a transaction.");

        // Get the EJB proxy to invoke methodB with its own transaction settings
        ClassA proxy = sessionContext.getBusinessObject(ClassA.class);
        proxy.methodB();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void methodB() {
        System.out.println("methodB: New transaction started.");
        // Some database operation
    }
}
```

---

### **📝 Expected Behavior in Logs**
If logging is enabled, you might see something like:

```
methodA: Running WITHOUT a transaction.
[EL Fine]: txn: begin transaction  (WebLogic starts a new transaction for methodB)
methodB: New transaction started.
[EL Fine]: txn: commit transaction  (WebLogic commits methodB's transaction)
```

---

### **🚀 Conclusion**
✔️ **Yes, `methodB` will start a new transaction** because `REQUIRES_NEW` forces it.
✔️ **Using `sessionContext.getBusinessObject(ClassA.class)` ensures WebLogic applies the correct transactional behavior.**
✔️ **`methodA` will not be part of any transaction, and `methodB` will execute in its own transaction scope.**

Would you like to verify this with WebLogic transaction logs? 🚀