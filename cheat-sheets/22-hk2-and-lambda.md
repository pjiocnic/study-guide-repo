
1. pom.xml

```xml
<!-- Maven Example -->
<dependencies>
    <!-- HK2 Dependency -->
    <dependency>
        <groupId>org.glassfish.hk2</groupId>
        <artifactId>hk2-api</artifactId>
        <version>2.7.0-b41</version> <!-- Use the latest version available -->
    </dependency>
</dependencies>
```

2. MySingletonHelper

```java
import javax.inject.Singleton;

@Singleton
public class MySingletonHelper {
    // Your singleton helper logic here
}
```

3. MyApplicationBinder

```java
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(MySingletonHelper.class).to(MySingletonHelper.class).in(Singleton.class);
        bind(NestedClass.class).to(NestedClass.class).in(Singleton.class);
        // Bind other classes if needed
    }
}
```

4. Lambda

```java
import javax.inject.Inject;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

public class MyLambdaHandler implements RequestHandler<RequestType, ResponseType> {

    @Inject
    private NestedClass nestedClass;

    public MyLambdaHandler() {
        // Initialize HK2 ServiceLocator
        ServiceLocator serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();

        // Register the HK2 binder
        ServiceLocatorUtilities.bind(serviceLocator, new MyApplicationBinder());

        // Inject dependencies into this instance
        serviceLocator.inject(this);
    }

    @Override
    public ResponseType handleRequest(RequestType request, Context context) {
        // Access and use the injected dependencies, including nestedClass
        nestedClass.nestedMethod();
        // ...
        return new ResponseType();
    }
}

public class NestedClass {

    @Inject
    private MySingletonHelper singletonHelper;

    public void nestedMethod() {
        // Access and use the injected singletonHelper
        // ...
    }
}

```

5. Test

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

public class MySingletonHelperTest {

    @Test
    public void testSingletonBehavior() {
        // Initialize HK2 ServiceLocator
        ServiceLocator serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();

        // Register the HK2 binder
        ServiceLocatorUtilities.bind(serviceLocator, new MyApplicationBinder());

        // Inject dependencies into instances
        MySingletonHelper instance1 = serviceLocator.getService(MySingletonHelper.class);
        MySingletonHelper instance2 = serviceLocator.getService(MySingletonHelper.class);

        // Ensure that both instances reference the same object
        assertSame(instance1, instance2);
    }
}

```