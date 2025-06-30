
package com.example.batch;

import com.example.batch.service.BatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BatchServiceIntegrationTests
{
    @Autowired
    private BatchService batchService;

    @Test
    void testProcess() {
        batchService.process("IntegrationTestName");
        assertThat(batchService).isNotNull();
    }
}
