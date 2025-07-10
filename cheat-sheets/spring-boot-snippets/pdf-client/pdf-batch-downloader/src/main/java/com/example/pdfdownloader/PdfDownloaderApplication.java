
package com.example.pdfdownloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PdfDownloaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(PdfDownloaderApplication.class, args);
    }
}
