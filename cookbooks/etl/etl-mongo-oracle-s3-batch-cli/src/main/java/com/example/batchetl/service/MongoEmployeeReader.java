package com.example.batchetl.service;

import com.example.batchetl.model.Employee;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MongoEmployeeReader {
    private final MongoTemplate mongo;
    @Value("${app.mongo.collection}") private String collection;
    @Value("${app.mongo.id-field:_id}") private String idField;

    public MongoEmployeeReader(MongoTemplate mongo) { this.mongo = mongo; }

    public Optional<Employee> findById(String id) {
        Query q;
        if ("_id".equals(idField)) {
            try { q = Query.query(Criteria.where("_id").is(new ObjectId(id))); }
            catch (IllegalArgumentException ex) { q = Query.query(Criteria.where("_id").is(id)); }
        } else {
            q = Query.query(Criteria.where(idField).is(id));
        }
        var list = mongo.find(q, Employee.class, collection); // required form
        return (list == null || list.isEmpty()) ? Optional.empty() : Optional.of(list.get(0));
    }
}
