
## ✅ Upsert Example

```java
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.json.JsonParseException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.model.UpdateOptions;

public class JsonUpsertExample {

    public static void main(String[] args) throws Exception {
        // Step 1: Call remote REST service
        URL url = new URL("https://example.com/api/data"); // Replace with your real URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream responseStream = connection.getInputStream();

        // Step 2: Parse and augment JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);

        if (!(root instanceof ObjectNode)) {
            throw new IllegalStateException("Root must be a JSON object");
        }

        ObjectNode objectNode = (ObjectNode) root;

        // Add a new field at root level
        objectNode.put("source", "remote-service");

        // Optional: extract a field to use as _id or unique key
        String uniqueKey = objectNode.has("id") ? objectNode.get("id").asText() : null;

        // Step 3: Connect to MongoDB
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase db = mongoClient.getDatabase("your_db");
            MongoCollection<Document> collection = db.getCollection("your_collection");

            // Convert JsonNode to BSON Document
            Document doc = Document.parse(objectNode.toString());

            // Step 4: Upsert — based on "id" field
            if (uniqueKey != null) {
                collection.updateOne(
                        eq("id", uniqueKey),
                        new Document("$set", doc),
                        new UpdateOptions().upsert(true)
                );
            } else {
                System.err.println("Missing 'id' field; cannot perform upsert");
            }
        }
    }
}
```

