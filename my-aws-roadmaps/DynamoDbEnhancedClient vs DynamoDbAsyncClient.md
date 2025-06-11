<h1>DynamoDbEnhancedClient vs DynamoDbAsyncClient</h1>

# 1. DynamoDbEnhancedClient

```java
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import software.amazon.awssdk.enhanced.dynamodb.model.CreateTableEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@DynamoDbBean
public class DynamoDBExample {

    // Define a data model class with annotations
    @DynamoDbBean
    public static class MyItem {
        private String id;
        private String timestamp;

        @DynamoDbPartitionKey
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @DynamoDbSortKey
        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static void main(String[] args) {
        // Create a DynamoDB client
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1) // Replace with your AWS region
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        // Create a DynamoDB Enhanced Client
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        // Define a table schema
        TableSchema<MyItem> tableSchema = TableSchema.fromBean(MyItem.class);

        // Create a table if it doesn't exist
        try {
            enhancedClient.createTable(CreateTableEnhancedRequest.builder(MyItem.class).build());
            System.out.println("Table created successfully.");
        } catch (DynamoDbException e) {
            System.err.println("Table creation failed: " + e.getMessage());
        }

        // Perform CRUD operations

        // Create
        MyItem itemToCreate = new MyItem();
        itemToCreate.setId("1");
        itemToCreate.setTimestamp("2023-09-30T12:00:00");
        enhancedClient.table(MyItem.class.getSimpleName()).putItem(PutItemEnhancedRequest.create(itemToCreate));

        // Read
        MyItem itemRead = enhancedClient.table(MyItem.class.getSimpleName())
                .getItem(Key.builder().partitionValue("1").build());
        System.out.println("Read item: " + itemRead.getId() + " - " + itemRead.getTimestamp());

        // Update
        UpdateItemEnhancedRequest<MyItem> updateRequest = UpdateItemEnhancedRequest.builder(MyItem.class)
                .item(itemRead)
                .ignoreNulls(true)
                .build();
        enhancedClient.table(MyItem.class.getSimpleName()).updateItem(updateRequest);

        // Query
        QueryConditional queryCondition = QueryConditional
                .keyEqualTo(Key.builder().partitionValue("1").build());
        List<MyItem> queryResults = StreamSupport.stream(enhancedClient.table(MyItem.class.getSimpleName())
                .query(r -> r.queryConditional(queryCondition))
                .items().spliterator(), false)
                .collect(Collectors.toList());
        System.out.println("Query results:");
        for (MyItem resultItem : queryResults) {
            System.out.println(resultItem.getId() + " - " + resultItem.getTimestamp());
        }

        // Delete
        enhancedClient.table(MyItem.class.getSimpleName()).deleteItem(Key.builder().partitionValue("1").build());

        // Clean up: Delete the table
        dynamoDbClient.deleteTable(b -> b.tableName(MyItem.class.getSimpleName()));
    }
}
```

# 2. Using DynamoDbAsyncClient

```java
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.paginators.ScanPublisher;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DynamoDBAsyncExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Create a DynamoDB async client
        DynamoDbAsyncClient dynamoDbAsyncClient = DynamoDbAsyncClient.builder()
                .region(Region.US_EAST_1) // Replace with your AWS region
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        // Perform CRUD operations asynchronously

        // Create
        createItemAsync(dynamoDbAsyncClient, "1", "2023-09-30T12:00:00").get();

        // Read
        readItemAsync(dynamoDbAsyncClient, "1").get();

        // Update
        updateItemAsync(dynamoDbAsyncClient, "1", "2023-10-01T14:00:00").get();

        // Query (Scan in this example)
        scanItemsAsync(dynamoDbAsyncClient).get();

        // Delete
        deleteItemAsync(dynamoDbAsyncClient, "1").get();

        // Shutdown the client to release resources
        dynamoDbAsyncClient.close();
    }

    // Helper methods for async CRUD operations

    private static CompletableFuture<Void> createItemAsync(DynamoDbAsyncClient client, String id, String timestamp) {
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName("YourTableName") // Replace with your table name
                .item(Item.builder()
                        .put("id", AttributeValue.builder().s(id).build())
                        .put("timestamp", AttributeValue.builder().s(timestamp).build())
                        .build())
                .build();

        return client.putItem(putItemRequest).thenApply(ignored -> null);
    }

    private static CompletableFuture<Void> readItemAsync(DynamoDbAsyncClient client, String id) {
        GetItemRequest getItemRequest = GetItemRequest.builder()
                .tableName("YourTableName") // Replace with your table name
                .key(Map.of("id", AttributeValue.builder().s(id).build()))
                .build();

        return client.getItem(getItemRequest).thenApply(getItemResponse -> {
            System.out.println("Read item: " + getItemResponse.item().get("id").s() + " - " +
                    getItemResponse.item().get("timestamp").s());
            return null;
        });
    }

    private static CompletableFuture<Void> updateItemAsync(DynamoDbAsyncClient client, String id, String newTimestamp) {
        UpdateItemRequest updateItemRequest = UpdateItemRequest.builder()
                .tableName("YourTableName") // Replace with your table name
                .key(Map.of("id", AttributeValue.builder().s(id).build()))
                .updateExpression("SET timestamp = :newTimestamp")
                .expressionAttributeValues(Map.of(":newTimestamp", AttributeValue.builder().s(newTimestamp).build()))
                .build();

        return client.updateItem(updateItemRequest).thenApply(ignored -> null);
    }

    private static CompletableFuture<Void> deleteItemAsync(DynamoDbAsyncClient client, String id) {
        DeleteItemRequest deleteItemRequest = DeleteItemRequest.builder()
                .tableName("YourTableName") // Replace with your table name
                .key(Map.of("id", AttributeValue.builder().s(id).build()))
                .build();

        return client.deleteItem(deleteItemRequest).thenApply(ignored -> null);
    }

    private static CompletableFuture<Void> scanItemsAsync(DynamoDbAsyncClient client) {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName("YourTableName") // Replace with your table name
                .build();

        ScanPublisher scanPublisher = client.scanPaginator(scanRequest);

        return CompletableFuture.runAsync(() -> {
            scanPublisher.items().forEach(item -> {
                System.out.println("Scanned item: " + item.get("id").s() + " - " + item.get("timestamp").s());
            });
        });
    }
}

```