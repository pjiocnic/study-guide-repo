package com.example.etl.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MongoJsonReader {

    private final MongoTemplate mongoTemplate;
    private final ObjectMapper mapper;

    @Value("${app.mongo.collection}")
    private String collection;

    @Value("${app.mongo.id-field:_id}")
    private String idField;

    public MongoJsonReader(MongoTemplate mongoTemplate, ObjectMapper mapper) {
        this.mongoTemplate = mongoTemplate;
        this.mapper = mapper;
    }

    public Optional<JsonNode> fetchJsonById(String id) {
        Document doc = null;
        var coll = mongoTemplate.getDb().getCollection(collection);

        if ("_id".equals(idField)) {
            try {
                doc = coll.find(Filters.eq("_id", new ObjectId(id))).first();
            } catch (Exception ignore) {}
            if (doc == null) {
                doc = coll.find(Filters.eq("_id", id)).first();
            }
        } else {
            doc = coll.find(Filters.eq(idField, id)).first();
        }

        if (doc == null) return Optional.empty();

        try {
            String json = doc.toJson();
            return Optional.of(mapper.readTree(json));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Mongo JSON for id=" + id, e);
        }
    }
}
