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
        // For demo, we use only one table. Extend here to support more.
        batchService.sendPendingEmails();
        return "Triggered batch for " + table;
    }
}
