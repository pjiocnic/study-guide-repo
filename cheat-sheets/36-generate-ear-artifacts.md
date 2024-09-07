To generate two EAR files with different names using Maven, you can configure your pom.xml file to include multiple executions of the Maven EAR plugin, each with its own configuration. Here's how you can achieve that

1. Add the Maven EAR plugin to your pom.xml:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-ear-plugin</artifactId>
            <version>3.1.0</version>
            <executions>
                <execution>
                    <id>first-ear</id>
                    <phase>package</phase>
                    <goals>
                        <goal>ear</goal>
                    </goals>
                    <configuration>
                        <finalName>first-ear</finalName>
                        <!-- additional configuration for the first EAR file -->
                    </configuration>
                </execution>
                <execution>
                    <id>second-ear</id>
                    <phase>package</phase>
                    <goals>
                        <goal>ear</goal>
                    </goals>
                    <configuration>
                        <finalName>second-ear</finalName>
                        <!-- additional configuration for the second EAR file -->
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

2. Customize each EAR file configuration:

* The <finalName> tag within each <configuration> section specifies the name of the resulting EAR file.
* You can also specify different modules, libraries, and other settings for each EAR file if needed.

3. Build your project

```sh
mvn clean package
```

# Example with Custom Configurations

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-ear-plugin</artifactId>
            <version>3.1.0</version>
            <executions>
                <execution>
                    <id>first-ear</id>
                    <phase>package</phase>
                    <goals>
                        <goal>ear</goal>
                    </goals>
                    <configuration>
                        <finalName>first-ear</finalName>
                        <modules>
                            <jarModule>
                                <groupId>com.example</groupId>
                                <artifactId>first-module</artifactId>
                                <bundleFileName>first-module.jar</bundleFileName>
                            </jarModule>
                            <!-- additional modules for the first EAR file -->
                        </modules>
                    </configuration>
                </execution>
                <execution>
                    <id>second-ear</id>
                    <phase>package</phase>
                    <goals>
                        <goal>ear</goal>
                    </goals>
                    <configuration>
                        <finalName>second-ear</finalName>
                        <modules>
                            <jarModule>
                                <groupId>com.example</groupId>
                                <artifactId>second-module</artifactId>
                                <bundleFileName>second-module.jar</bundleFileName>
                            </jarModule>
                            <!-- additional modules for the second EAR file -->
                        </modules>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
