package com.example.batchemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/send-batch")
public class EmailBatchController {

    @Autowired
    private EmailBatchService batchService;

    @PostMapping("/{table}")
    public String triggerBatch(@PathVariable String table) {
        batchService.processBatch(table, 10); // Default batch size for manual trigger
        return "Triggered batch for " + table;
    }
}
