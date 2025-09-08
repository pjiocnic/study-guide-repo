package com.example.uploader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Default "business logic" merger. Replace with your own logic as needed.
 *
 * Merges Oracle row columns into the output as-is, and flattens Mongo fields with a 'mongo_' prefix.
 */
@Service
public class BusinessLogicService {

    private static final Logger log = LoggerFactory.getLogger(BusinessLogicService.class);

    public Map<String, Object> merge(Map<String, Object> oracleRow, Map<String, Object> mongoDoc) {
        Map<String, Object> out = new HashMap<>();
        if (oracleRow != null) {
            out.putAll(oracleRow);
        }
        if (mongoDoc != null) {
            for (Map.Entry<String, Object> e : mongoDoc.entrySet()) {
                String key = "mongo_" + e.getKey();
                // avoid overwriting if collision happens
                if (!out.containsKey(key)) {
                    out.put(key, e.getValue());
                }
            }
        }
        out.put("_merged_at", OffsetDateTime.now().toString());
        return out;
    }
}