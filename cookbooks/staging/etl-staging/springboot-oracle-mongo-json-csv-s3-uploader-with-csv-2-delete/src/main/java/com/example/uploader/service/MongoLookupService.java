package com.example.uploader.service;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MongoLookupService {

    private static final Logger log = LoggerFactory.getLogger(MongoLookupService.class);
    private final MongoTemplate mongoTemplate;

    public MongoLookupService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Map<String, Object> findByKey(String collectionName, String keyField, Object keyValue) {
        Query query = new Query(Criteria.where(keyField).is(keyValue));
        Document doc = mongoTemplate.findOne(query, Document.class, collectionName);
        if (doc == null) {
            return null;
        }
        // Convert to plain Map for easy JSON serialization
        return doc;
    }
}