
1. How to update an existing object followed by resort?

```java
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

class SortedSetUpdater<T> {
    private final SortedSet<T> sortedSet;
    private final Comparator<T> comparator;

    public SortedSetUpdater(Comparator<T> comparator) {
        this.comparator = comparator;
        this.sortedSet = new TreeSet<>(comparator);
    }

    public void add(T element) {
        sortedSet.add(element);
    }

    public void update(T oldElement, T newElement) {
        sortedSet.removeIf(e -> comparator.compare(e, oldElement) == 0);
        sortedSet.add(newElement);
    }

    public SortedSet<T> getSortedSet() {
        return sortedSet;
    }
}

class CustomObject {
    private int id;
    private String name;

    public CustomObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        SortedSetUpdater<CustomObject> sortedSetUpdater = new SortedSetUpdater<>(Comparator.comparingInt(CustomObject::getId));

        // Adding objects to the sorted set
        sortedSetUpdater.add(new CustomObject(1, "Object 1"));
        sortedSetUpdater.add(new CustomObject(2, "Object 2"));
        sortedSetUpdater.add(new CustomObject(3, "Object 3"));

        System.out.println("Before update: " + sortedSetUpdater.getSortedSet());

        // Updating an existing object
        CustomObject objectToUpdate = new CustomObject(2, "Updated Object 2");
        sortedSetUpdater.update(new CustomObject(2, "Object 2"), objectToUpdate);

        System.out.println("After update: " + sortedSetUpdater.getSortedSet());
    }
}
```