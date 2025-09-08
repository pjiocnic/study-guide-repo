package com.example.mongo.service;

import com.example.mongo.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final MongoTemplate mongo;

    public ItemService(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    public Item create(Item item) {
        Instant now = Instant.now();
        item.setCreatedAt(now);
        item.setUpdatedAt(now);
        return mongo.insert(item);
    }

    public Optional<Item> getById(String id) {
        return Optional.ofNullable(mongo.findById(id, Item.class));
    }

    public Page<Item> list(int page, int size, String nameContains) {
        Query query = new Query();
        if (nameContains != null && !nameContains.isBlank()) {
            query.addCriteria(Criteria.where("name").regex(nameContains, "i"));
        }
        long total = mongo.count(query, Item.class);
        PageRequest pr = PageRequest.of(page, size);
        query.with(pr).with(Sort.by(Sort.Direction.DESC, "updatedAt"));
        List<Item> data = mongo.find(query, Item.class);
        return new PageImpl<>(data, pr, total);
    }

    public Optional<Item> replace(String id, Item incoming) {
        Item existing = mongo.findById(id, Item.class);
        if (existing == null) return Optional.empty();

        existing.setName(incoming.getName());
        existing.setDescription(incoming.getDescription());
        existing.setQuantity(incoming.getQuantity());
        existing.setUpdatedAt(Instant.now());

        mongo.save(existing);
        return Optional.of(existing);
    }

    public Optional<Item> patch(String id, Item partial) {
        Query q = Query.query(Criteria.where("_id").is(id));
        Update u = new Update().currentDate("updatedAt");

        boolean hasAny = false;
        if (partial.getName() != null) { u.set("name", partial.getName()); hasAny = true; }
        if (partial.getDescription() != null) { u.set("description", partial.getDescription()); hasAny = true; }
        if (partial.getQuantity() != null) { u.set("quantity", partial.getQuantity()); hasAny = true; }

        if (!hasAny) return getById(id);

        var res = mongo.findAndModify(q, u, FindAndModifyOptions.options().returnNew(true), Item.class);
        return Optional.ofNullable(res);
    }

    public boolean delete(String id) {
        Query q = Query.query(Criteria.where("_id").is(id));
        return mongo.remove(q, Item.class).getDeletedCount() > 0;
    }

    public void deleteAll() {
        mongo.remove(new Query(), Item.class);
    }
}
