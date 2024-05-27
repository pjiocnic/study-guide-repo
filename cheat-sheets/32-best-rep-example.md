
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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EmployeeManager {
    private Set<Employee> employees;
    public static final LocalDate INACTIVE_DATE = LocalDate.now();
    public static final LocalDate ACTIVE_DATE = LocalDate.of(9999, 12, 31);

    public EmployeeManager() {
        this.employees = new HashSet<>();
    }

    public void addEmployee(Employee newEmployee) {
        if (isEmployeePresent(newEmployee)) {
            System.out.println("Employee already present: " + newEmployee);
            return;
        }

        Optional<Employee> activeManager = getActiveEmployeeByJobTitle("Manager");

        if (newEmployee.getJobTitle().equals("Manager")) {
            handleManagerAddition(newEmployee, activeManager);
        } else if (newEmployee.getJobTitle().equals("Worker")) {
            handleWorkerAddition(newEmployee, activeManager);
        }

        employees.add(newEmployee);
    }

    private boolean isEmployeePresent(Employee newEmployee) {
        return employees.stream()
                .anyMatch(e -> e.getJobTitle().equals(newEmployee.getJobTitle()) && e.getStartDate().equals(newEmployee.getStartDate()));
    }

    private void handleManagerAddition(Employee newManager, Optional<Employee> activeManager) {
        if (activeManager.isPresent()) {
            Employee currentActiveManager = activeManager.get();
            if (newManager.getStartDate().isAfter(currentActiveManager.getStartDate())) {
                currentActiveManager.setEndDate(INACTIVE_DATE);
                newManager.setEndDate(ACTIVE_DATE);
                newManager.setBestInd(true);
                currentActiveManager.setBestInd(false);
            } else {
                newManager.setEndDate(INACTIVE_DATE);
            }
        } else {
            newManager.setEndDate(ACTIVE_DATE);
            newManager.setBestInd(true);
        }
    }

    private void handleWorkerAddition(Employee newWorker, Optional<Employee> activeManager) {
        if (activeManager.isPresent()) {
            newWorker.setEndDate(INACTIVE_DATE);
        } else {
            Optional<Employee> activeWorker = getActiveEmployeeByJobTitle("Worker");
            if (activeWorker.isPresent()) {
                Employee currentActiveWorker = activeWorker.get();
                if (newWorker.getStartDate().isAfter(currentActiveWorker.getStartDate())) {
                    currentActiveWorker.setEndDate(INACTIVE_DATE);
                    newWorker.setEndDate(ACTIVE_DATE);
                    newWorker.setBestInd(true);
                    currentActiveWorker.setBestInd(false);
                } else {
                    newWorker.setEndDate(INACTIVE_DATE);
                }
            } else {
                newWorker.setEndDate(ACTIVE_DATE);
                newWorker.setBestInd(true);
            }
        }
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

Driver class

```java
import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager();

        Employee manager1 = new Employee("Manager", LocalDate.of(2020, Month.JANUARY, 10), EmployeeManager.ACTIVE_DATE);
        Employee manager2 = new Employee("Manager", LocalDate.of(2021, Month.FEBRUARY, 20), EmployeeManager.ACTIVE_DATE);
        Employee worker1 = new Employee("Worker", LocalDate.of(2019, Month.MARCH, 15), EmployeeManager.ACTIVE_DATE);
        Employee worker2 = new Employee("Worker", LocalDate.of(2022, Month.APRIL, 5), EmployeeManager.ACTIVE_DATE);

        employeeManager.addEmployee(manager1);
        employeeManager.addEmployee(manager2);
        employeeManager.addEmployee(worker1);
        employeeManager.addEmployee(worker2);

        employeeManager.printEmployees();
    }
}
```

# Junit Tests

```java
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeManagerTest {
    private EmployeeManager employeeManager;

    @BeforeEach
    public void setUp() {
        employeeManager = new EmployeeManager();
    }

    @Test
    public void testAddManager_FirstManager_ShouldBeActive() {
        Employee manager = new Employee("Manager", LocalDate.of(2020, Month.JANUARY, 10), EmployeeManager.ACTIVE_DATE);
        employeeManager.addEmployee(manager);

        Optional<Employee> activeManager = employeeManager.getActiveEmployeeByJobTitle("Manager");
        assertTrue(activeManager.isPresent());
        assertEquals(manager, activeManager.get());
        assertTrue(activeManager.get().isBestInd());
    }

    @Test
    public void testAddManager_SecondManager_ShouldDeactivateFirstManager() {
        Employee manager1 = new Employee("Manager", LocalDate.of(2020, Month.JANUARY, 10), EmployeeManager.ACTIVE_DATE);
        Employee manager2 = new Employee("Manager", LocalDate.of(2021, Month.FEBRUARY, 20), EmployeeManager.ACTIVE_DATE);
        employeeManager.addEmployee(manager1);
        employeeManager.addEmployee(manager2);

        assertEquals(EmployeeManager.INACTIVE_DATE, manager1.getEndDate());
        assertEquals(EmployeeManager.ACTIVE_DATE, manager2.getEndDate());
        assertTrue(manager2.isBestInd());
        assertFalse(manager1.isBestInd());
    }

    @Test
    public void testAddManager_SecondManagerWithOlderDate_ShouldRemainInactive() {
        Employee manager1 = new Employee("Manager", LocalDate.of(2021, Month.FEBRUARY, 20), EmployeeManager.ACTIVE_DATE);
        Employee manager2 = new Employee("Manager", LocalDate.of(2020, Month.JANUARY, 10), EmployeeManager.ACTIVE_DATE);
        employeeManager.addEmployee(manager1);
        employeeManager.addEmployee(manager2);

        assertEquals(EmployeeManager.ACTIVE_DATE, manager1.getEndDate());
        assertEquals(EmployeeManager.INACTIVE_DATE, manager2.getEndDate());
        assertTrue(manager1.isBestInd());
        assertFalse(manager2.isBestInd());
    }

    @Test
    public void testAddWorker_WithActiveManager_ShouldRemainInactive() {
        Employee manager = new Employee("Manager", LocalDate.of(2020, Month.JANUARY, 10), EmployeeManager.ACTIVE_DATE);
        Employee worker = new Employee("Worker", LocalDate.of(2021, Month.FEBRUARY, 20), EmployeeManager.ACTIVE_DATE);
        employeeManager.addEmployee(manager);
        employeeManager.addEmployee(worker);

        assertEquals(EmployeeManager.ACTIVE_DATE, manager.getEndDate());
        assertEquals(EmployeeManager.INACTIVE_DATE, worker.getEndDate());
        assertFalse(worker.isBestInd());
    }

    @Test
    public void testAddWorker_WithNoActiveManager_ShouldBeActive() {
        Employee worker = new Employee("Worker", LocalDate.of(2021, Month.FEBRUARY, 20), EmployeeManager.ACTIVE_DATE);
        employeeManager.addEmployee(worker);

        Optional<Employee> activeWorker = employeeManager.getActiveEmployeeByJobTitle("Worker");
        assertTrue(activeWorker.isPresent());
        assertEquals(worker, activeWorker.get());
        assertTrue(activeWorker.get().isBestInd());
    }

    @Test
    public void testAddWorker_SecondWorker_ShouldDeactivateFirstWorker() {
        Employee worker1 = new Employee("Worker", LocalDate.of(2020, Month.JANUARY, 10), EmployeeManager.ACTIVE_DATE);
        Employee worker2 = new Employee("Worker", LocalDate.of(2021, Month.FEBRUARY, 20), EmployeeManager.ACTIVE_DATE);
        employeeManager.addEmployee(worker1);
        employeeManager.addEmployee(worker2);

        assertEquals(EmployeeManager.INACTIVE_DATE, worker1.getEndDate());
        assertEquals(EmployeeManager.ACTIVE_DATE, worker2.getEndDate());
        assertTrue(worker2.isBestInd());
        assertFalse(worker1.isBestInd());
    }

    @Test
    public void testAddDuplicateEmployee_ShouldNotAdd() {
        Employee worker1 = new Employee("Worker", LocalDate.of(2021, Month.FEBRUARY, 20), EmployeeManager.ACTIVE_DATE);
        Employee worker2 = new Employee("Worker", LocalDate.of(2021, Month.FEBRUARY, 20), EmployeeManager.ACTIVE_DATE);
        employeeManager.addEmployee(worker1);
        employeeManager.addEmployee(worker2);

        long count = employeeManager.employees.stream()
                .filter(e -> e.getJobTitle().equals("Worker") && e.getStartDate().equals(LocalDate.of(2021, Month.FEBRUARY, 20)))
                .count();
        assertEquals(1, count);
    }

    @Test
    public void testAddManagerThenWorker_ShouldDeactivateWorker() {
        Employee worker = new Employee("Worker", LocalDate.of(2020, Month.JANUARY, 10), EmployeeManager.ACTIVE_DATE);
        Employee manager = new Employee("Manager", LocalDate.of(2021, Month.FEBRUARY, 20), EmployeeManager.ACTIVE_DATE);
        employeeManager.addEmployee(worker);
        employeeManager.addEmployee(manager);

        assertEquals(EmployeeManager.INACTIVE_DATE, worker.getEndDate());
        assertEquals(EmployeeManager.ACTIVE_DATE, manager.getEndDate());
        assertTrue(manager.isBestInd());
        assertFalse(worker.isBestInd());
    }
}
```