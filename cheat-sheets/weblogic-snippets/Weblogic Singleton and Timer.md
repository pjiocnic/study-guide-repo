<h1>Weblogic Singleton and Timer</h1>

# singleton session bean with a scheduled method to poll the JMS queue at fixed intervals

### Steps:

1. **Create a Singleton EJB**
   - Use `@Singleton` to ensure only one instance of the bean.
   - Use `@Startup` to initialize it on deployment.
   - Use `@Schedule` to trigger message polling every minute.

2. **Connect to JMS Queue**
   - Use `@Resource` to inject the connection factory and queue.
   - Use `MessageConsumer` to receive messages in a batch.

### Implementation:

```java
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.*;

@Singleton
@Startup
public class JmsQueuePoller {

    @Resource(lookup = "jms/MyConnectionFactory") // Change to your JMS connection factory JNDI
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/MyQueue") // Change to your queue JNDI
    private Queue queue;

    @Schedule(minute = "*/1", hour = "*", persistent = false) // Poll every 1 minute
    public void pollQueue() {
        try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
            JMSConsumer consumer = context.createConsumer(queue);
            int batchSize = 10; // Process up to 10 messages at a time

            for (int i = 0; i < batchSize; i++) {
                Message message = consumer.receiveNoWait(); // Non-blocking receive
                if (message == null) {
                    break; // No more messages in the queue
                }
                processMessage(message);
            }
        } catch (JMSException e) {
            e.printStackTrace(); // Log the error properly in production
        }
    }

    private void processMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                System.out.println("Processing message: " + text);
            } else {
                System.out.println("Received non-text message: " + message);
            }
        } catch (JMSException e) {
            e.printStackTrace(); // Log the error properly
        }
    }
}
```

### Explanation:
- `@Singleton @Startup`: Ensures a single instance runs when the application starts.
- `@Schedule`: Triggers every minute.
- `JMSContext`: Used instead of `JMSConnection` for simplified resource handling.
- `consumer.receiveNoWait()`: Non-blocking call to fetch messages in a batch.
- `batchSize`: Controls the number of messages processed per cycle.

This approach ensures that your application efficiently pulls messages in batches while avoiding unnecessary idle connections. Let me know if you need further adjustments! 🚀

---

# Running in cluster

To ensure that your singleton bean runs on only one managed server in a WebLogic 14 cluster, you should use the **@Singleton with @Startup** along with **@DependsOn** and **WebLogic's Singleton Service**.

### Solution:
1. **Use WebLogic's Singleton Service**
   - WebLogic provides a way to deploy a singleton service that runs on only one node in a cluster.

2. **Implement a WebLogic Singleton Service**
   - Instead of using `@Singleton @Startup`, implement `weblogic.cluster.singleton.SingletonService`.
   - This ensures the service runs on only one managed server at a time.
   - If the server where the singleton is running fails, WebLogic migrates it to another available node.

### Implementation:

#### 1️⃣ **Create the Singleton Service**
```java
import javax.annotation.Resource;
import javax.jms.*;
import weblogic.cluster.singleton.SingletonService;
import weblogic.cluster.singleton.SingletonServiceBase;

public class JmsQueuePollerSingleton extends SingletonServiceBase {

    @Resource(lookup = "jms/MyConnectionFactory") // Change to your JMS connection factory JNDI
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/MyQueue") // Change to your queue JNDI
    private Queue queue;

    private volatile boolean running = true;

    @Override
    public void activate() {
        System.out.println("JmsQueuePollerSingleton activated on " + System.getProperty("weblogic.Name"));
        new Thread(this::pollQueue).start();
    }

    @Override
    public void deactivate() {
        System.out.println("JmsQueuePollerSingleton deactivated on " + System.getProperty("weblogic.Name"));
        running = false;
    }

    private void pollQueue() {
        while (running) {
            try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
                JMSConsumer consumer = context.createConsumer(queue);
                int batchSize = 10;

                for (int i = 0; i < batchSize; i++) {
                    Message message = consumer.receiveNoWait();
                    if (message == null) {
                        break;
                    }
                    processMessage(message);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(60000); // Sleep for 1 minute before next poll
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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

#### 2️⃣ **Register the Singleton Service in WebLogic**
Create a deployment descriptor file `JmsQueuePollerSingletonDeployment.xml` in `WEB-INF`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<weblogic-cluster>
    <singleton-service>
        <name>JmsQueuePollerSingleton</name>
        <class-name>com.example.JmsQueuePollerSingleton</class-name>
        <preferred-server>ManagedServer1</preferred-server>
    </singleton-service>
</weblogic-cluster>
```

#### 3️⃣ **Deploy the Singleton Service**
- Place `JmsQueuePollerSingletonDeployment.xml` in `WEB-INF` of your application.
- Deploy the application across the WebLogic cluster.
- WebLogic will ensure that only **one instance** of `JmsQueuePollerSingleton` runs on a single server in the cluster.

### 🔹 Benefits:
✅ Runs on only one managed server at a time.
✅ If the server goes down, WebLogic automatically migrates it to another managed server.
✅ Avoids conflicts from multiple instances processing messages.

This ensures efficient and fault-tolerant message processing across your WebLogic cluster.

---

# WebLogic Singleton Service to ensure a single instance runs in the cluster and use ScheduledExecutorService to trigger polling every minute

Since you need the polling to run on a **schedule (every 1 minute)** while ensuring it runs on only **one managed server** in a WebLogic cluster, you should use **WebLogic’s Singleton Service with a Scheduled Executor**.

### ✅ **Final Solution**
We'll use **WebLogic Singleton Service** to ensure a single instance runs in the cluster and use **ScheduledExecutorService** to trigger polling every minute.

---

### **1️⃣ Implement the Singleton Service with Scheduled Polling**
```java
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.jms.*;
import weblogic.cluster.singleton.SingletonService;
import weblogic.cluster.singleton.SingletonServiceBase;

public class JmsQueuePollerSingleton extends SingletonServiceBase {

    @Resource(lookup = "jms/MyConnectionFactory") // Change this based on your WebLogic JMS
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/MyQueue") // Change this to your queue JNDI
    private Queue queue;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private volatile boolean running = true;

    @Override
    public void activate() {
        System.out.println("JmsQueuePollerSingleton activated on " + System.getProperty("weblogic.Name"));

        scheduler.scheduleAtFixedRate(this::pollQueue, 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public void deactivate() {
        System.out.println("JmsQueuePollerSingleton deactivated on " + System.getProperty("weblogic.Name"));
        running = false;
        scheduler.shutdown();
    }

    private void pollQueue() {
        if (!running) return;

        try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
            JMSConsumer consumer = context.createConsumer(queue);
            int batchSize = 10; // Process messages in batch

            for (int i = 0; i < batchSize; i++) {
                Message message = consumer.receiveNoWait();
                if (message == null) {
                    break;
                }
                processMessage(message);
            }
        } catch (JMSException e) {
            e.printStackTrace();
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

### **2️⃣ Register the Singleton in WebLogic**
Create a `JmsQueuePollerSingletonDeployment.xml` in `WEB-INF`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<weblogic-cluster>
    <singleton-service>
        <name>JmsQueuePollerSingleton</name>
        <class-name>com.example.JmsQueuePollerSingleton</class-name>
        <preferred-server>ManagedServer1</preferred-server> <!-- Optional -->
    </singleton-service>
</weblogic-cluster>
```

---

### **3️⃣ Deployment Steps**
1. Add `JmsQueuePollerSingletonDeployment.xml` in `WEB-INF` of your application.
2. Deploy the application across your WebLogic cluster.
3. WebLogic will ensure that only **one instance** of `JmsQueuePollerSingleton` runs on a single server.
4. The singleton will poll messages **every 1 minute**.

---

### **✔️ Why This Works**
✅ Runs on only **one** managed server in the cluster at a time.
✅ **Automatically fails over** if the active server crashes.
✅ Uses **ScheduledExecutorService** for precise scheduling.
✅ Processes messages in **batches** efficiently.

This is a **highly available, failover-ready** solution that ensures your polling runs smoothly in WebLogic 14. 🚀 Let me know if you need any refinements!

# Handling Failures

No, messages will **not be lost** if there's an abrupt shutdown or failure, as long as you properly configure **JMS durability, transactions, and WebLogic’s singleton failover mechanism**. Here’s how your system will handle failures:

---

## 🔹 **1. WebLogic Singleton Failover**
When a managed server running the singleton crashes, WebLogic **automatically migrates** the singleton to another available managed server in the cluster.
✅ **Effect:** The next available server takes over and resumes polling the queue.

---

## 🔹 **2. JMS Message Durability & Persistence**
### **IBM MQ (or WebLogic JMS) ensures messages are not lost.**
- Messages remain in the queue **until they are acknowledged and committed**.
- If your singleton crashes **before processing** a message, it stays in the queue for the next poll cycle.

✅ **Solution:** Ensure that messages are persistent and survive server crashes:
- **IBM MQ (or WebLogic JMS) should be configured with durable, persistent messaging.**
- Use **persistent delivery mode** (`DeliveryMode.PERSISTENT` in producer).
- Use **transactional sessions** for safe message processing.

---

## 🔹 **3. Avoiding Message Loss During Processing**
### **If the server crashes while processing messages, use transactions to ensure safe rollback.**
Modify your `pollQueue()` method to **use transactions**:

```java
private void pollQueue() {
    if (!running) return;

    try (JMSContext context = connectionFactory.createContext(JMSContext.SESSION_TRANSACTED)) {
        JMSConsumer consumer = context.createConsumer(queue);
        int batchSize = 10;

        for (int i = 0; i < batchSize; i++) {
            Message message = consumer.receiveNoWait();
            if (message == null) {
                break;
            }
            processMessage(message);
        }

        // Commit only after successfully processing all messages
        context.commit();
    } catch (JMSException e) {
        e.printStackTrace();
    }
}
```
### **Why use `SESSION_TRANSACTED`?**
✅ **Prevents message loss:** If the server crashes before `context.commit()`, IBM MQ/WebLogic JMS **automatically rolls back** the unprocessed messages, so they stay in the queue for retry.
✅ **Ensures atomic processing:** Either **all** messages in the batch are processed and committed, or **none** (rollback on failure).

---

## 🔹 **4. Singleton Migration & Resume Polling**
When WebLogic migrates the singleton to another server:
- It **resumes polling** from where the previous instance left off.
- **Uncommitted messages** (from a failed instance) remain in the queue and are retried by the new active singleton.

### **🔄 Flow of Failover Handling**
1️⃣ Singleton runs on `ManagedServer1` and polls messages.
2️⃣ `ManagedServer1` crashes unexpectedly.
3️⃣ WebLogic **migrates** the singleton to `ManagedServer2`.
4️⃣ IBM MQ/WebLogic JMS **rolls back** any uncommitted messages.
5️⃣ The new singleton instance starts polling from `ManagedServer2`, processing remaining messages.

---

## 🔹 **Final Failover-Proof Approach**
✔ **Use WebLogic Singleton Service for auto-migration** ✅
✔ **Ensure JMS messages are persistent** ✅
✔ **Use `SESSION_TRANSACTED` mode for safe rollback** ✅
✔ **Commit only after full batch processing to prevent partial loss** ✅

### **🚀 Result:** Even if the server crashes during polling or processing, no messages are lost—everything is safely retried! Let me know if you need any refinements. 😊

---

# How to run multiple singletons

Yes, **WebLogic supports multiple `weblogic.cluster.singleton.SingletonService` instances** deployed in the **same EAR** or **different EARs** within the same WebLogic cluster. However, each singleton service must have a **unique name** to avoid conflicts.

---

## ✅ **Key Considerations for Multiple Singleton Services**
### 1️⃣ **Unique Singleton Service Names**
Each singleton service must have a unique name in **WebLogic's configuration** to ensure proper failover behavior.

### 2️⃣ **Preferred Server (Optional, for Load Distribution)**
If you want **different singleton services to run on different servers**, configure **preferred servers** to distribute the singleton load.

### 3️⃣ **Independent Failover Handling**
Each singleton service is migrated independently by WebLogic if a failure occurs.

---

## **🔹 What Changes Are Needed?**
### **1. Update Each Singleton Service with a Unique Name**
Modify each singleton implementation by assigning **different class names** and ensuring each has **a unique service name**.

#### **Example: First Singleton (JMS Poller)**
```java
public class JmsQueuePollerSingleton extends SingletonServiceBase {
    @Override
    public void activate() {
        System.out.println("JmsQueuePollerSingleton activated on " + System.getProperty("weblogic.Name"));
    }

    @Override
    public void deactivate() {
        System.out.println("JmsQueuePollerSingleton deactivated on " + System.getProperty("weblogic.Name"));
    }
}
```

#### **Example: Second Singleton (Another Background Job)**
```java
public class SomeOtherSingleton extends SingletonServiceBase {
    @Override
    public void activate() {
        System.out.println("SomeOtherSingleton activated on " + System.getProperty("weblogic.Name"));
    }

    @Override
    public void deactivate() {
        System.out.println("SomeOtherSingleton deactivated on " + System.getProperty("weblogic.Name"));
    }
}
```

---

### **2. Create WebLogic Cluster Deployment Descriptors**
Each singleton must have **a separate WebLogic configuration entry**.

#### **First Singleton Deployment Descriptor (`WEB-INF/JmsQueuePollerSingletonDeployment.xml`)**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<weblogic-cluster>
    <singleton-service>
        <name>JmsQueuePollerSingleton</name>
        <class-name>com.example.JmsQueuePollerSingleton</class-name>
        <preferred-server>ManagedServer1</preferred-server>
    </singleton-service>
</weblogic-cluster>
```

#### **Second Singleton Deployment Descriptor (`WEB-INF/SomeOtherSingletonDeployment.xml`)**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<weblogic-cluster>
    <singleton-service>
        <name>SomeOtherSingleton</name>
        <class-name>com.example.SomeOtherSingleton</class-name>
        <preferred-server>ManagedServer2</preferred-server>
    </singleton-service>
</weblogic-cluster>
```

---

### **3. Deployment Approaches**
- ✅ **Within the same EAR:**
  - Place both singleton classes in separate WAR modules inside the same EAR.
  - Each module should have its own **WebLogic descriptor (`WEB-INF/weblogic-cluster.xml`)**.

- ✅ **Across different EARs:**
  - Deploy each singleton service in separate EARs.
  - Each EAR contains only one `weblogic-cluster.xml` file for its respective singleton.

---

## 🔥 **Final Result**
- **WebLogic will run multiple singleton services**, each on a separate managed server.
- If a preferred server crashes, WebLogic will **fail over each singleton service independently**.
- You **avoid conflicts** because each singleton has **a unique name**.

🚀 **Now you can run multiple singleton services reliably across the cluster!** Let me know if you need any refinements. 😊