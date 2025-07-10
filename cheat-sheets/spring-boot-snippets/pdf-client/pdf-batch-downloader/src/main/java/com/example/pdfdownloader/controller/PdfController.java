
package com.example.pdfdownloader.controller;

import com.example.pdfdownloader.service.PdfService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {
    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/download/batch")
    public String downloadBatch() {
        try {
            pdfService.processBatchPdfs();
            return "Batch download triggered successfully";
        } catch (Exception e) {
            return "Batch download failed: " + e.getMessage();
        }
    }
}
