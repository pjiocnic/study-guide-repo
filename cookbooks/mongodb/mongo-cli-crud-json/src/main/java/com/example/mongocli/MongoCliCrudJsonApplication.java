package com.example.mongocli;

import com.example.mongocli.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class MongoCliCrudJsonApplication implements CommandLineRunner {

    private final MongoTemplate mongo;
    private final ObjectMapper mapper;

    public MongoCliCrudJsonApplication(MongoTemplate mongo) {
        this.mongo = mongo;
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoCliCrudJsonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== MongoTemplate CLI CRUD + JSON demo ===");

        // Clean slate (optional)
        mongo.dropCollection(Book.class);

        // 1) INSERT a couple of books
        Book hobbit = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
        Book dune = new Book("Dune", "Frank Herbert", 1965);
        mongo.insert(hobbit);
        mongo.insert(dune);

        // 2) READ - list all as domain objects
        List<Book> all = mongo.findAll(Book.class);
        System.out.println("# All as POJOs:");
        all.forEach(System.out::println);

        // 3) READ - query and get as POJOs
        Query q = new Query(Criteria.where("author").is("J.R.R. Tolkien"));
        List<Book> tolkiens = mongo.find(q, Book.class);
        System.out.println("\n# Tolkien books as POJOs:");
        tolkiens.forEach(System.out::println);

        // 4) JSON: Serialize a POJO to JSON string, then deserialize back
        System.out.println("\n# Serialize POJO -> JSON -> POJO");
        String json = mapper.writeValueAsString(hobbit);
        System.out.println("JSON string:\n" + json);
        Book fromJson = mapper.readValue(json, Book.class);
        System.out.println("Deserialized back: " + fromJson);

        // 5) JSON array: serialize a list, then deserialize back
        System.out.println("\n# Serialize List<Book> -> JSON array -> List<Book>");
        String jsonArray = mapper.writeValueAsString(all);
        System.out.println("JSON array:\n" + jsonArray);
        List<?> fromArray = mapper.readValue(jsonArray, mapper.getTypeFactory().constructCollectionType(List.class, Book.class));
        System.out.println("Deserialized list size: " + fromArray.size());

        // 6) Raw Mongo Document to JSON, then map to POJO
        System.out.println("\n# Fetch raw Document from Mongo and convert via Jackson");
        Document raw = mongo.getDb().getCollection("books").find(new Document("title", "Dune")).first();
        if (raw != null) {
            String rawJson = raw.toJson();
            System.out.println("Raw Document JSON from Mongo driver:\n" + rawJson);
            Book dunePojo = mapper.readValue(rawJson, Book.class);
            System.out.println("Mapped to POJO via Jackson: " + dunePojo);
        }

        // 7) UPDATE and DELETE to finish the demo
        hobbit.setYear(1951);
        mongo.save(hobbit);
        System.out.println("\nUpdated Hobbit: " + mongo.findById(hobbit.getId(), Book.class));

        mongo.remove(dune);
        System.out.println("Deleted Dune. Remaining: " + mongo.findAll(Book.class));
    }
}
