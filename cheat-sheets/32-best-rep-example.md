
```java
import java.util.List;
import java.util.Optional;

class Person {
    private String name;
    private String title;
    private int age;
    private boolean bestTitle;

    public Person(String name, String title, int age, boolean bestTitle) {
        this.name = name;
        this.title = title;
        this.age = age;
        this.bestTitle = bestTitle;
    }

    public String getTitle() {
        return title;
    }

    public int getAge() {
        return age;
    }

    public boolean isBestTitle() {
        return bestTitle;
    }

    public void setBestTitle(boolean bestTitle) {
        this.bestTitle = bestTitle;
    }

    public String getName() {
        return name;
    }
}

public class YoungestFinder {
    public static void main(String[] args) {
        List<Person> persons = /* Your sorted list of persons */;

        Optional<Person> youngestDoctor = persons.stream()
                .filter(person -> person.getTitle().equals("doctor"))
                .findFirst();

        Optional<Person> youngestEngineer = persons.stream()
                .filter(person -> person.getTitle().equals("engineer"))
                .findFirst();

        youngestDoctor.ifPresent(person -> person.setBestTitle(true));
        youngestEngineer.ifPresent(person -> person.setBestTitle(true));

        persons.stream()
                .filter(person -> !person.isBestTitle())
                .forEach(person -> person.setBestTitle(false));

        Optional<Person> youngest = youngestDoctor.isPresent() ? youngestDoctor : youngestEngineer;
        youngest.ifPresent(person -> System.out.println("Youngest " + person.getTitle() + ": " + person.getName() + ", Best Title: " + person.isBestTitle()));
    }
}
```