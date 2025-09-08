package com.example.mongocli;

import com.example.mongocli.model.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class MongoCliCrudApplication implements CommandLineRunner {

    private final MongoTemplate mongo;

    public MongoCliCrudApplication(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoCliCrudApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("=== MongoTemplate CLI CRUD demo ===");

        // Clean slate (optional)
        mongo.dropCollection(Book.class);

        // 1) INSERT
        Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
        mongo.insert(book);
        System.out.println("Inserted: " + book);

        // 2) READ - find all
        System.out.println("All books:");
        mongo.findAll(Book.class).forEach(System.out::println);

        // 3) READ - by criteria
        Query q = new Query(Criteria.where("author").is("J.R.R. Tolkien"));
        System.out.println("Books by Tolkien: " + mongo.find(q, Book.class));

        // 4) UPDATE - modify a field and save
        book.setYear(1951);
        mongo.save(book);
        System.out.println("Updated book: " + mongo.findById(book.getId(), Book.class));

        // 5) DELETE - remove the book
        mongo.remove(book);
        System.out.println("Deleted book with id " + book.getId());

        System.out.println("Final books in DB: " + mongo.findAll(Book.class));
    }
}
