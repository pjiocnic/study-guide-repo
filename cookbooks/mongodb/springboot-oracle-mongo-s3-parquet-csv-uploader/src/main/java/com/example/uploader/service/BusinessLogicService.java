package com.example.uploader.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Default "business logic" merger.
 * - Copies Oracle columns
 * - Adds Mongo fields with 'mongo_' prefix
 * - Flattens complex/nested Mongo values to JSON strings (for Parquet friendliness)
 */
@Service
public class BusinessLogicService {

    private static final Logger log = LoggerFactory.getLogger(BusinessLogicService.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public Map<String, Object> merge(Map<String, Object> oracleRow, Map<String, Object> mongoDoc) {
        Map<String, Object> out = new HashMap<>();
        if (oracleRow != null) out.putAll(oracleRow);
        if (mongoDoc != null) {
            for (Map.Entry<String, Object> e : mongoDoc.entrySet()) {
                String key = "mongo_" + e.getKey();
                Object val = e.getValue();
                // If value is not a simple primitive, store as JSON string
                if (!(val instanceof String || val instanceof Number || val instanceof Boolean)) {
                    try { val = mapper.writeValueAsString(val); } catch (Exception ex) { val = String.valueOf(val); }
                }
                if (!out.containsKey(key)) out.put(key, val);
            }
        }
        out.put("_merged_at", OffsetDateTime.now().toString());
        return out;
    }
}