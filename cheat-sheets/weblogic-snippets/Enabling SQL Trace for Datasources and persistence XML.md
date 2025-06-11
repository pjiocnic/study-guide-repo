<h1>Enabling SQL Trace for Datasources and persistence XML</h1>

If you're using a **WebLogic DataSource** with **EclipseLink**, logging **bind variables** in SQL queries requires additional configuration because WebLogic manages the database connections.

---

### **1. Modify `persistence.xml`**
In your `persistence.xml`, you still need to enable EclipseLink logging:

```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.1">
    <persistence-unit name="yourPersistenceUnit">
        <jta-data-source>jdbc/YourWebLogicDataSource</jta-data-source>
        <properties>
            <!-- Enable SQL Logging -->
            <property name="eclipselink.logging.level" value="FINE"/>

            <!-- Show SQL with Bind Variables -->
            <property name="eclipselink.logging.parameters" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```

- **`jta-data-source`** → Uses the WebLogic DataSource (`jdbc/YourWebLogicDataSource`).
- **`eclipselink.logging.level=FINE`** → Enables detailed SQL logs.
- **`eclipselink.logging.parameters=true`** → Prints the actual **bind variable values**.

---

### **2. Enable Logging in WebLogic**
Since WebLogic handles the connections, EclipseLink logging might not show SQL logs by default. You need to configure **WebLogic logging**:

#### **Option 1: Enable Debug Logging in WebLogic Console**
1. **Login to WebLogic Admin Console** (`http://your-weblogic-server:7001/console`).
2. Navigate to **Servers** → Your Server (`AdminServer` or Managed Server).
3. Click on the **Logging** tab.
4. Under **Advanced**, set:
   - **Log Level**: `Debug`
   - **Log File Severity**: `Debug`
5. Save and restart WebLogic.

#### **Option 2: Configure WebLogic Debug Flags**
If you need **JDBC debugging**, enable **JDBC Debugging** in WebLogic by setting **debug flags**:

1. Go to **WebLogic Console** → `Servers` → Your Server.
2. Navigate to **Debug** → Expand `weblogic` → Expand `JDBC`.
3. Enable:
   - **`DebugJDBCConnection`** (Tracks connections)
   - **`DebugJDBCSQL`** (Logs SQL execution)
   - **`DebugJDBCDriverLogging`** (Shows details from the JDBC driver)
4. Save and restart WebLogic.

---

### **3. Expected SQL Log Output**
Once configured, your logs should now include SQL queries **with bind variables**:

```
[EL Fine]: SELECT * FROM users WHERE id = ?
[EL Fine]: bind => [123]
```

If WebLogic **still doesn't show bind variables**, you may need to configure **JDBC Driver Logging** (next step).

---

### **4. (Optional) Enable Driver-Specific Logging**
If your database driver supports logging, you can enable it:
- **Oracle** (for Oracle DataSource in WebLogic):
  ```xml
  <property name="oracle.jdbc.Trace" value="true"/>
  ```
  Or enable **`-Doracle.jdbc.Trace=true`** in WebLogic startup parameters.

- **MySQL/MariaDB**:
  - Add `logger=com.mysql.cj.log.Slf4JLogger` to your `jdbc:mysql://` URL.

- **PostgreSQL**:
  - Add `loggerLevel=TRACE` to your connection properties.

---

### **Final Thoughts**
1. **For most cases, just enabling `eclipselink.logging.parameters=true` is enough.**
2. **If logs don’t show bind variables**, enable **WebLogic JDBC Debugging**.
3. **For deeper debugging, enable JDBC driver logging**.

Let me know if you need help tweaking WebLogic settings! 🚀

---

The property `<property name="oracle.jdbc.Trace" value="true"/>` enables **Oracle JDBC driver tracing**, which helps capture SQL and bind variable details at the driver level. Since you're using **WebLogic with a DataSource**, you cannot set this in `persistence.xml` but need to configure it in **WebLogic DataSource settings** or **JVM startup arguments**.

---

## **1. Configure in WebLogic DataSource (Recommended)**
To enable Oracle JDBC tracing at the **WebLogic DataSource** level:

### **Steps:**
1. **Log into WebLogic Admin Console** (`http://your-weblogic-server:7001/console`).
2. Navigate to **Services** → **Data Sources**.
3. Select your **Oracle DataSource** (e.g., `jdbc/YourWebLogicDataSource`).
4. Click on the **Connection Pool** tab.
5. In the **Properties** field, add:
   ```
   oracle.jdbc.Trace=true
   ```
6. Save and restart WebLogic.

This ensures **all connections** from this DataSource have tracing enabled.

---

## **2. Configure in WebLogic Startup Arguments**
If you want to enable tracing **globally** for all applications using Oracle JDBC, add the following system property to WebLogic’s **JVM startup arguments**:

### **Modify `setDomainEnv.sh` (Linux/macOS) or `setDomainEnv.cmd` (Windows):**
1. Locate the script inside:
   ```
   $DOMAIN_HOME/bin/setDomainEnv.sh  (Linux/macOS)
   $DOMAIN_HOME\bin\setDomainEnv.cmd (Windows)
   ```
2. Add the following line near `JAVA_OPTIONS`:
   ```sh
   JAVA_OPTIONS="${JAVA_OPTIONS} -Doracle.jdbc.Trace=true"
   export JAVA_OPTIONS
   ```
   (For Windows, use `set JAVA_OPTIONS=%JAVA_OPTIONS% -Doracle.jdbc.Trace=true`)

3. Save the file and restart WebLogic.

---

## **3. Configure in `ojdbc.properties` (Alternative)**
If you are using **Oracle’s `ojdbc8.jar`** (or similar), you can also configure tracing in an **`ojdbc.properties`** file:

1. Create a file named `ojdbc.properties` in the same directory as `ojdbc8.jar`.
2. Add the following line:
   ```
   oracle.jdbc.Trace=true
   ```
3. Restart WebLogic.

---

### **4. Verify Logging**
Once enabled, Oracle JDBC tracing will output logs like:

```
[oracle.jdbc] SELECT * FROM users WHERE id = ?
[oracle.jdbc] bind => [123]
```

These logs will appear in **WebLogic logs (`server.log`)** or wherever WebLogic is configured to write logs.

---

### **Which Method Should You Use?**
- ✅ **Best for specific DataSource** → **Method 1 (WebLogic DataSource Properties)**
- ✅ **Best for global enablement** → **Method 2 (JVM Arguments)**
- ✅ **Alternative (if using Oracle's JDBC jar directly)** → **Method 3 (`ojdbc.properties`)**

Let me know if you need more details! 🚀