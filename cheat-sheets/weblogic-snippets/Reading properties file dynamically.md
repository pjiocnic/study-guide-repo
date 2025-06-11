To implement this improvement, we need to:
✅ **Read the properties file from a Unix server location.**
✅ **Reload the properties file periodically** to pick up changes dynamically.
✅ **Adjust the batch size** based on the time window conditions.

---

## 🔹 **1. Updating the Singleton to Read and Reload Properties**
We'll introduce a **properties file** that defines `batch.size`. The singleton will:
- Load the properties from a specified **Unix file path**.
- Periodically **reload the file** to capture changes dynamically.
- **Adjust batch size** based on the time of day.

### **Example `batch-config.properties` File (Stored on Unix)**
```
batch.size.default=500
batch.size.7-8PM=100
batch.size.8PM-7AM=500
batch.size.weekend=500
```
---

## 🔹 **2. Modify Singleton to Read & Reload Properties**
We'll introduce:
- A **scheduler** to reload the properties file every 5 minutes.
- A **method to determine the correct batch size** based on the time window.

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.time.*;
import java.util.Properties;
import java.util.concurrent.*;

import javax.annotation.Resource;
import javax.jms.*;
import weblogic.cluster.singleton.SingletonServiceBase;

public class JmsQueuePollerSingleton extends SingletonServiceBase {

    private static final String PROPERTIES_FILE_PATH = "/opt/config/batch-config.properties"; // Change to actual Unix path
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Resource(lookup = "jms/MyConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/MyQueue")
    private Queue queue;

    private volatile boolean running = true;
    private volatile Properties properties = new Properties();

    @Override
    public void activate() {
        System.out.println("JmsQueuePollerSingleton activated on " + System.getProperty("weblogic.Name"));

        // Load properties initially
        loadProperties();

        // Schedule property reload every 5 minutes
        scheduler.scheduleAtFixedRate(this::loadProperties, 0, 5, TimeUnit.MINUTES);

        // Schedule message polling every minute
        scheduler.scheduleAtFixedRate(this::pollQueue, 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public void deactivate() {
        System.out.println("JmsQueuePollerSingleton deactivated on " + System.getProperty("weblogic.Name"));
        running = false;
        scheduler.shutdown();
    }

    private void loadProperties() {
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE_PATH)) {
            Properties newProperties = new Properties();
            newProperties.load(fis);
            properties = newProperties;
            System.out.println("Properties reloaded: " + properties);
        } catch (IOException e) {
            System.err.println("Failed to load properties file: " + e.getMessage());
        }
    }

    private void pollQueue() {
        if (!running) return;

        int batchSize = determineBatchSize();
        System.out.println("Polling JMS Queue with batch size: " + batchSize);

        try (JMSContext context = connectionFactory.createContext(JMSContext.SESSION_TRANSACTED)) {
            JMSConsumer consumer = context.createConsumer(queue);

            for (int i = 0; i < batchSize; i++) {
                Message message = consumer.receiveNoWait();
                if (message == null) {
                    break;
                }
                processMessage(message);
            }

            // Commit after processing batch
            context.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private int determineBatchSize() {
        LocalTime now = LocalTime.now();
        DayOfWeek day = LocalDate.now().getDayOfWeek();

        boolean isWeekend = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);

        if (isWeekend) {
            return Integer.parseInt(properties.getProperty("batch.size.weekend", "500"));
        } else if (now.isAfter(LocalTime.of(19, 0)) && now.isBefore(LocalTime.of(20, 0))) {
            return Integer.parseInt(properties.getProperty("batch.size.7-8PM", "100"));
        } else if (now.isAfter(LocalTime.of(20, 0)) || now.isBefore(LocalTime.of(7, 0))) {
            return Integer.parseInt(properties.getProperty("batch.size.8PM-7AM", "500"));
        } else {
            return Integer.parseInt(properties.getProperty("batch.size.default", "500"));
        }
    }

    private void processMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                System.out.println("Processing message: " + ((TextMessage) message).getText());
            } else {
                System.out.println("Received non-text message: " + message);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 🔹 **3. How This Works**
✔ **Reads batch size from the properties file** (stored on Unix).
✔ **Reloads the properties file every 5 minutes**, so changes take effect without restarting WebLogic.
✔ **Dynamically adjusts batch size** based on the time window:
   - **7-8 PM:** `100`
   - **8 PM - 7 AM:** `500`
   - **Weekends:** `500`
✔ **Ensures transactions are committed** to prevent message loss.
✔ **Runs only on one managed server** in the WebLogic cluster, thanks to WebLogic Singleton Service.

---

### 🚀 **Final Result**
- The singleton reads and reloads **batch-config.properties** dynamically.
- Batch size changes **take effect within 5 minutes** of updating the file.
- The correct batch size is applied based on **time-of-day and weekends**.
- The singleton service **automatically fails over** to another WebLogic server if needed.

This is a **fully dynamic, failover-ready solution!** 🎯 Let me know if you need any refinements. 😊