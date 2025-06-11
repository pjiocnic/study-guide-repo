Absolutely! Here's a **fully consolidated and production-ready setup** with everything you asked for:

---

# ✅ **1. `batch-config.properties`**

Place this file on the classpath (e.g., `src/main/resources`) or in a shared location:

```properties
# Polling schedule
poll.time=02:30     # Used by TimeEJB2 to trigger once daily

# Batch size settings
batch.size.default=500
batch.size.7-8PM=100
batch.size.8PM-7AM=500
batch.size.weekend=500
```

---

# ✅ **2. WebLogic Singleton (`JmsQueuePollerSingleton`)**

Ensures only **one server** in the cluster manages the scheduled timers.

```java
import weblogic.cluster.singleton.SingletonServiceBase;
import javax.naming.InitialContext;

public class JmsQueuePollerSingleton extends SingletonServiceBase {

    @Override
    public void activate() {
        System.out.println("Singleton activated on " + System.getProperty("weblogic.Name"));
        try {
            InitialContext ctx = new InitialContext();
            TimeWindowEjb timeEjb1 = (TimeWindowEjb) ctx.lookup("java:global/YourApp/YourModule/TimeWindowEjb");
            timeEjb1.start();  // TimerEJB1: runs on different windows

            TimerEjbDaily timeEjb2 = (TimerEjbDaily) ctx.lookup("java:global/YourApp/YourModule/TimerEjbDaily");
            timeEjb2.startPollingOnceADay(); // TimerEJB2: once daily + immediate
        } catch (Exception e) {
            throw new RuntimeException("Singleton startup error", e);
        }
    }

    @Override
    public void deactivate() {
        System.out.println("Singleton deactivated.");
    }
}
```

> 🔄 Replace `YourApp/YourModule` with your actual EAR/WAR module names.

---

# ✅ **3. `TimeWindowEjb` (TimerEjb1) — Runs Every Minute, Uses Time-Based Batch Size**

```java
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.jms.*;
import javax.naming.InitialContext;
import java.time.*;
import java.util.Properties;

@Singleton
@Startup
public class TimeWindowEjb {

    private ConnectionFactory connectionFactory;
    private Queue queue;
    private Properties properties = new Properties();

    @PostConstruct
    public void init() {
        try {
            InitialContext ctx = new InitialContext();
            connectionFactory = (ConnectionFactory) ctx.lookup("jms/MyConnectionFactory");
            queue = (Queue) ctx.lookup("jms/MyQueue");

            properties.load(getClass().getClassLoader().getResourceAsStream("batch-config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Schedule(minute = "*/1", hour = "*", persistent = false)
    public void pollEveryMinute() {
        int batchSize = determineBatchSize();
        System.out.println("Polling every minute. Batch size: " + batchSize);

        try (JMSContext context = connectionFactory.createContext(JMSContext.SESSION_TRANSACTED)) {
            JMSConsumer consumer = context.createConsumer(queue);
            for (int i = 0; i < batchSize; i++) {
                Message message = consumer.receive(1000);  // Wait 1 sec
                if (message == null) break;
                processMessage(message);
            }
            context.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("TimeWindowEjb started (scheduled every 1 minute).");
    }

    private int determineBatchSize() {
        LocalTime now = LocalTime.now();
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
            return Integer.parseInt(properties.getProperty("batch.size.weekend", "500"));
        if (now.isAfter(LocalTime.of(19, 0)) && now.isBefore(LocalTime.of(20, 0)))
            return Integer.parseInt(properties.getProperty("batch.size.7-8PM", "100"));
        if (now.isAfter(LocalTime.of(20, 0)) || now.isBefore(LocalTime.of(7, 0)))
            return Integer.parseInt(properties.getProperty("batch.size.8PM-7AM", "500"));
        return Integer.parseInt(properties.getProperty("batch.size.default", "500"));
    }

    private void processMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                System.out.println("Message: " + ((TextMessage) message).getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
```

---

# ✅ **4. `TimerEjbDaily` (TimerEjb2) — Immediate + Once Daily Based on `poll.time`**

```java
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.*;
import javax.naming.InitialContext;
import java.io.InputStream;
import java.time.*;
import java.util.Properties;
import java.util.concurrent.*;

@Singleton
@Startup
public class TimerEjbDaily {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private ScheduledFuture<?> dailyTaskHandle;
    private Properties properties = new Properties();
    private LocalTime scheduledPollTime;

    private ConnectionFactory connectionFactory;
    private Queue queue;

    @PostConstruct
    public void init() {
        try {
            InitialContext ctx = new InitialContext();
            connectionFactory = (ConnectionFactory) ctx.lookup("jms/MyConnectionFactory");
            queue = (Queue) ctx.lookup("jms/MyQueue");

            loadProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startPollingOnceADay() {
        // Trigger immediately on startup
        scheduler.execute(this::pollQueue);

        // Schedule daily job
        scheduledPollTime = parsePollTime(properties);
        scheduleDailyPolling(scheduledPollTime);

        // Reload config every 5 minutes
        scheduler.scheduleAtFixedRate(this::reloadAndRescheduleIfNeeded, 5, 5, TimeUnit.MINUTES);
    }

    private void reloadAndRescheduleIfNeeded() {
        Properties newProps = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("batch-config.properties")) {
            if (input != null) newProps.load(input);
        } catch (Exception e) {
            System.err.println("Reload failed: " + e.getMessage());
            return;
        }

        LocalTime newTime = parsePollTime(newProps);
        if (!newTime.equals(scheduledPollTime)) {
            System.out.println("poll.time changed from " + scheduledPollTime + " to " + newTime);
            scheduledPollTime = newTime;
            properties = newProps;
            if (dailyTaskHandle != null) dailyTaskHandle.cancel(false);
            scheduleDailyPolling(newTime);
        } else {
            properties = newProps;
        }
    }

    private void scheduleDailyPolling(LocalTime pollTime) {
        long delay = computeInitialDelay(pollTime);
        dailyTaskHandle = scheduler.scheduleAtFixedRate(this::pollQueue, delay, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
        System.out.println("Daily polling scheduled at " + pollTime);
    }

    private long computeInitialDelay(LocalTime time) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime nextRun = now.with(time);
        if (now.compareTo(nextRun) >= 0) nextRun = nextRun.plusDays(1);
        return Duration.between(now, nextRun).toMillis();
    }

    private LocalTime parsePollTime(Properties props) {
        String timeStr = props.getProperty("poll.time", "02:30");
        try {
            return LocalTime.parse(timeStr);
        } catch (Exception e) {
            return LocalTime.of(2, 30);
        }
    }

    private void pollQueue() {
        int batchSize = determineBatchSize();
        System.out.println("Running daily poll, batch size: " + batchSize);
        try (JMSContext context = connectionFactory.createContext(JMSContext.SESSION_TRANSACTED)) {
            JMSConsumer consumer = context.createConsumer(queue);
            for (int i = 0; i < batchSize; i++) {
                Message message = consumer.receive(1000);
                if (message == null) break;
                processMessage(message);
            }
            context.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int determineBatchSize() {
        LocalTime now = LocalTime.now();
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
            return Integer.parseInt(properties.getProperty("batch.size.weekend", "500"));
        if (now.isAfter(LocalTime.of(19, 0)) && now.isBefore(LocalTime.of(20, 0)))
            return Integer.parseInt(properties.getProperty("batch.size.7-8PM", "100"));
        if (now.isAfter(LocalTime.of(20, 0)) || now.isBefore(LocalTime.of(7, 0)))
            return Integer.parseInt(properties.getProperty("batch.size.8PM-7AM", "500"));
        return Integer.parseInt(properties.getProperty("batch.size.default", "500"));
    }

    private void processMessage(Message message) {
        try {
            if (message instanceof TextMessage)
                System.out.println("Message: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
```

---

## ✅ Summary

| Component | Function |
|----------|----------|
| **`batch-config.properties`** | Stores `poll.time` and batch size config |
| **`JmsQueuePollerSingleton`** | Cluster-wide singleton that activates one node |
| **`TimeWindowEjb`** | Polls every minute with dynamic batch size based on time/day |
| **`TimerEjbDaily`** | Runs immediately on startup, then once a day at `poll.time` (reloads config live) |

This setup is scalable, highly available, dynamically configurable, and resilient for clustered WebLogic environments. Let me know if you’d like it as a deployable EAR/WAR project template too!