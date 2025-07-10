
package com.example.pdfdownloader.service;

import com.example.pdfdownloader.repository.IdRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class PdfService {

    private final RestTemplate restTemplate;
    private final IdRepository idRepository;

    @Value("${remote.service.start.url}")
    private String startUrl;

    @Value("${remote.service.fetch.url}")
    private String fetchUrl;

    @Value("${local.pdf.dir}")
    private String localPdfDir;

    @Value("${batch.size}")
    private int batchSize;

    public PdfService(IdRepository idRepository) {
        this.idRepository = idRepository;
        this.restTemplate = new RestTemplate();
    }

    public void processBatchPdfs() throws Exception {
        List<Long> ids = idRepository.findBatchIds(batchSize);

        if (ids.isEmpty()) {
            System.out.println("No IDs left to process.");
            return;
        }

        for (Long id : ids) {
            try {
                String correlationId = callStartEndpoint(id);
                fetchPdfAndSave(correlationId, id);
                idRepository.markAsProcessed(id);
            } catch (Exception e) {
                System.err.println("Error processing id " + id + ": " + e.getMessage());
            }
        }
    }

    private String callStartEndpoint(Long id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> payload = Map.of("id", id);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(startUrl, entity, Map.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            String correlationId = (String) response.getBody().get("correlationId");
            if (correlationId != null) {
                System.out.println("Correlation ID for id " + id + ": " + correlationId);
                return correlationId;
            } else {
                throw new Exception("No correlationId returned for id " + id);
            }
        } else {
            throw new Exception("Start endpoint failed for id " + id);
        }
    }

    private void fetchPdfAndSave(String correlationId, Long id) throws Exception {
        String finalFetchUrl = fetchUrl + "/" + correlationId;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_PDF));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Resource> response = restTemplate.exchange(
                finalFetchUrl,
                HttpMethod.GET,
                entity,
                Resource.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            try (InputStream is = response.getBody().getInputStream();
                 FileOutputStream fos = new FileOutputStream(new File(localPdfDir + "/pdf_" + id + ".pdf"))) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                System.out.println("Saved PDF for id " + id);
            }
        } else {
            throw new Exception("PDF fetch failed for correlationId " + correlationId);
        }
    }
}
