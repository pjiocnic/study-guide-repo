package com.example.observability.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.containers.ActiveMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
public class InfrastructureIntegrationTest {

    @Container
    static OracleContainer oracle = new OracleContainer("gvenzl/oracle-xe")
        .withUsername("system")
        .withPassword("oracle");

    @Container
    static GenericContainer<?> activemq = new GenericContainer<>("rmohr/activemq:5.15.9")
        .withExposedPorts(61616, 8161);

    @Test
    public void testContainersRunning() {
        assertThat(oracle.isRunning()).isTrue();
        assertThat(activemq.isRunning()).isTrue();
    }
}