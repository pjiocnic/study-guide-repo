
1. Updating Best rep
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

2. Finding earliest hire/termination date

```java
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Employee {
    private String name;
    private String department;
    private LocalDateTime hireDate;
    private LocalDateTime terminateDate;

    // Constructor, getters, and setters
    // ...

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public LocalDateTime getTerminateDate() {
        return terminateDate;
    }
}

public class EmployeeAnalyzer {
    public static void main(String[] args) {
        List<Employee> employees = /* Your list of employees */;

        // Find employee with earliest hire date
        Optional<Employee> earliestHireDateEmployee = employees.stream()
                .min(Comparator.comparing(Employee::getHireDate));

        // Find employee with earliest termination date
        Optional<Employee> earliestTerminateDateEmployee = employees.stream()
                .filter(employee -> employee.getTerminateDate() != null) // Filter out employees with no termination date
                .min(Comparator.comparing(Employee::getTerminateDate));

        // Print results
        earliestHireDateEmployee.ifPresent(employee -> System.out.println("Employee with earliest hire date: " + employee.getName() + ", Hire Date: " + employee.getHireDate()));
        earliestTerminateDateEmployee.ifPresent(employee -> System.out.println("Employee with earliest termination date: " + employee.getName() + ", Termination Date: " + employee.getTerminateDate()));
    }
}
```

# Example 2

- Employee

```java
import java.time.LocalDate;

public class Employee {
    private String jobTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean bestInd;

    public Employee(String jobTitle, LocalDate startDate, LocalDate endDate) {
        this.jobTitle = jobTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bestInd = false;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isBestInd() {
        return bestInd;
    }

    public void setBestInd(boolean bestInd) {
        this.bestInd = bestInd;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "jobTitle='" + jobTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", bestInd=" + bestInd +
                '}';
    }
}
```

- Employee Management

```java
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Comparator;

public class EmployeeManager {
    private Set<Employee> employees;
    private static final LocalDate INACTIVE_DATE = LocalDate.now();
    private static final LocalDate ACTIVE_DATE = LocalDate.of(9999, 12, 31);

    public EmployeeManager() {
        this.employees = new HashSet<>();
    }

    public void addEmployee(Employee newEmployee) {
        Optional<Employee> activeEmployee = getActiveEmployeeByJobTitle(newEmployee.getJobTitle());

        if (activeEmployee.isPresent()) {
            Employee currentActive = activeEmployee.get();

            if (newEmployee.getStartDate().isAfter(currentActive.getStartDate())) {
                currentActive.setEndDate(INACTIVE_DATE);
                newEmployee.setEndDate(ACTIVE_DATE);
                newEmployee.setBestInd(true);
                currentActive.setBestInd(false);
            } else {
                newEmployee.setEndDate(INACTIVE_DATE);
            }
        } else {
            newEmployee.setEndDate(ACTIVE_DATE);
            newEmployee.setBestInd(true);
        }

        employees.add(newEmployee);
    }

    private Optional<Employee> getActiveEmployeeByJobTitle(String jobTitle) {
        return employees.stream()
                .filter(e -> e.getJobTitle().equals(jobTitle) && e.getEndDate().equals(ACTIVE_DATE))
                .findFirst();
    }

    public void printEmployees() {
        employees.forEach(System.out::println);
    }
}
```