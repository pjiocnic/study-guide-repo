
package com.example.pdfdownloader.scheduler;

import com.example.pdfdownloader.service.PdfService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class PdfBatchScheduler {

    private final PdfService pdfService;

    @Value("${schedule.interval.seconds}")
    private int intervalSeconds;

    public PdfBatchScheduler(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @Scheduled(fixedDelayString = "#{${schedule.interval.seconds} * 1000}")
    public void runBatchDownload() {
        try {
            pdfService.processBatchPdfs();
        } catch (Exception e) {
            System.err.println("Scheduled batch failed: " + e.getMessage());
        }
    }
}
