```java
import java.util.ArrayList;
import java.util.List;

public class ListSplitter {

    public static void main(String[] args) {
        // Example: Create a list of 50,000 elements
        List<Integer> largeList = new ArrayList<>();
        for (int i = 1; i <= 50000; i++) {
            largeList.add(i);
        }

        // Split the list into chunks of 1,000
        List<List<Integer>> chunks = splitList(largeList, 1000);

        // Print the size of each chunk to verify
        System.out.println("Number of chunks: " + chunks.size());
        for (int i = 0; i < chunks.size(); i++) {
            System.out.println("Chunk " + (i + 1) + " size: " + chunks.get(i).size());
        }
    }

    public static <T> List<List<T>> splitList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        int listSize = list.size();

        for (int i = 0; i < listSize; i += chunkSize) {
            int end = Math.min(listSize, i + chunkSize); // Ensure the last chunk doesn't go out of bounds
            chunks.add(list.subList(i, end));
        }

        return chunks;
    }
}
```