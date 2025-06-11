If the `Professor` class joins the `Course` class through a different column (i.e., not using the same `course_id` column as the `Student` class), you can use a custom column name in the `@JoinColumn` annotation for the `ManyToOne` relationship in the `Professor` class.

Here’s how you can update the `Professor` class to join the `Course` through a different column, say `course_professor_id`.

### Updated `Professor` class:

```java
import javax.persistence.*;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String professorName;

    @ManyToOne
    @JoinColumn(name = "course_professor_id")  // Custom join column for Professor
    private Course course;

    // Constructors, getters, setters
}
```

### Updated `Course` class (remains the same):

```java
import javax.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    // OneToMany relationship with Student
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

    // OneToMany relationship with Professor
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Professor> professors;

    // Constructors, getters, setters
}
```

### Explanation:
- The `@JoinColumn(name = "course_professor_id")` annotation in the `Professor` class indicates that the foreign key column for the `Course` in the `Professor` table is `course_professor_id`, rather than `course_id`, which is used in the `Student` class.

- The `Course` class does not need to change its `OneToMany` mapping. The `mappedBy` attribute in the `OneToMany` annotation will still refer to the `course` field in both the `Student` and `Professor` classes.

### Example code to persist one `Course`, two `Students`, and two `Professors` using the custom join column:

```java
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        try {
            // Create Course
            Course course = new Course();
            course.setCourseName("Mathematics");

            // Create Students
            Student student1 = new Student();
            student1.setStudentName("Alice");
            student1.setCourse(course);  // Link Student to Course

            Student student2 = new Student();
            student2.setStudentName("Bob");
            student2.setCourse(course);  // Link Student to Course

            // Create Professors
            Professor professor1 = new Professor();
            professor1.setProfessorName("Dr. Brown");
            professor1.setCourse(course);  // Link Professor to Course (custom column)

            Professor professor2 = new Professor();
            professor2.setProfessorName("Dr. Davis");
            professor2.setCourse(course);  // Link Professor to Course (custom column)

            // Add students and professors to course
            course.setStudents(List.of(student1, student2));
            course.setProfessors(List.of(professor1, professor2));

            // Persist the Course (CascadeType.ALL ensures Students and Professors are saved)
            em.persist(course);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
```

### Summary:
- **Custom `JoinColumn` for Professor**: By specifying a custom column name (`course_professor_id`) in the `Professor` class, you can separate the foreign key columns for `Students` and `Professors` in the `Course` class.
- **No changes needed in `Course` class**: The `Course` class remains unchanged, as the custom column only impacts the `Professor` class.

# A Variation

If you want to establish a relationship where the `Course` table and the `Professor` table are linked by a custom foreign key (e.g., `course_professor_id`), and this column is present in both tables, you are describing a situation where the join is not through the primary key (`id`) but instead via a shared non-primary key column.

To implement this in JPA and Oracle, here’s how you can modify the mappings and the corresponding Oracle tables.

### Steps:

1. **Add a `course_professor_id` column in both `Course` and `Professor` tables**.
2. **Use this column to join the `Course` and `Professor` tables**, instead of using the default `id` column.

### Updated Entity Classes

#### `Course` Class:
```java
import javax.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    // Custom column for joining with Professor
    private Long courseProfessorId;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

    // OneToMany relationship with Professor
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_professor_id", referencedColumnName = "course_professor_id")
    private List<Professor> professors;

    // Constructors, getters, setters
}
```

#### `Professor` Class:
```java
import javax.persistence.*;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String professorName;

    // This is the custom column for joining with Course
    private Long courseProfessorId;

    @ManyToOne
    @JoinColumn(name = "course_professor_id", referencedColumnName = "course_professor_id", insertable = false, updatable = false)
    private Course course;

    // Constructors, getters, setters
}
```

### Explanation:
- **Custom `courseProfessorId` Column**: Both `Course` and `Professor` classes now have the `courseProfessorId` column. This column is not the primary key, but it is used to establish a relationship between the `Course` and `Professor` entities.
- **`@JoinColumn` with `referencedColumnName`**: In the `Professor` class, the `@JoinColumn` specifies that the join is done via the `course_professor_id` column in both tables.
- **Insertable and Updatable Restrictions**: The `insertable = false, updatable = false` attributes in the `Professor` class are necessary to prevent issues with updating or inserting values in the `course_professor_id` column in both tables, since `Course` controls this field.

### Oracle Table Definitions

#### 1. `Course` Table:
The `Course` table now has an additional column `course_professor_id` to link to the `Professor` table.

```sql
CREATE TABLE Course (
    id                   NUMBER(19)    PRIMARY KEY,
    course_name          VARCHAR2(255) NOT NULL,
    course_professor_id  NUMBER(19)    -- New column to join with Professor
);
```

#### 2. `Professor` Table:
The `Professor` table also has the `course_professor_id` column, which links it to the `Course` table.

```sql
CREATE TABLE Professor (
    id                   NUMBER(19)    PRIMARY KEY,
    professor_name       VARCHAR2(255) NOT NULL,
    course_professor_id  NUMBER(19),   -- New column to join with Course
    CONSTRAINT fk_professor_course FOREIGN KEY (course_professor_id)
    REFERENCES Course (course_professor_id)  -- Foreign key references the custom column in Course
);
```

### Example Data Insertion

In this scenario, `course_professor_id` needs to be the same in both the `Course` and `Professor` tables to establish the relationship.

```sql
-- Insert a Course with a specific course_professor_id
INSERT INTO Course (id, course_name, course_professor_id)
VALUES (1, 'Math', 1001);

-- Insert Professors who are linked to the Course by course_professor_id
INSERT INTO Professor (id, professor_name, course_professor_id)
VALUES (1, 'Dr. Brown', 1001);

INSERT INTO Professor (id, professor_name, course_professor_id)
VALUES (2, 'Dr. Davis', 1001);
```

### Summary:
- **Custom Join via Non-Primary Key**: You are joining the `Course` and `Professor` tables using the `course_professor_id` column, which exists in both tables, rather than using the primary key (`id`).
- **JPA Mapping**: The `@JoinColumn` annotation is used with `referencedColumnName` to specify the column in both tables that will be used for the join.
- **Oracle Tables**: Both the `Course` and `Professor` tables now include the `course_professor_id` column, and the `Professor` table has a foreign key referencing `course_professor_id` in the `Course` table.

This setup allows you to join `Course` and `Professor` through the custom column `course_professor_id`.

The main class needs to be updated to handle the new relationship where the `Course` and `Professor` entities are joined through a custom column (`course_professor_id`). The changes will involve ensuring that when persisting a `Course`, you also assign the correct `course_professor_id` to the `Professor` objects.

Here's how the main class would look with these adjustments:

### Updated `Main` Class:
```java
import javax.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        try {
            // Create Course
            Course course = new Course();
            course.setCourseName("Physics");
            course.setCourseProfessorId(1001L);  // Set custom course_professor_id for linking

            // Create Students
            Student student1 = new Student();
            student1.setStudentName("Alice");
            student1.setCourse(course);  // Link Student to Course

            Student student2 = new Student();
            student2.setStudentName("Bob");
            student2.setCourse(course);  // Link Student to Course

            // Create Professors and set the same course_professor_id
            Professor professor1 = new Professor();
            professor1.setProfessorName("Dr. Einstein");
            professor1.setCourseProfessorId(1001L);  // Set course_professor_id for linking to Course

            Professor professor2 = new Professor();
            professor2.setProfessorName("Dr. Newton");
            professor2.setCourseProfessorId(1001L);  // Set course_professor_id for linking to Course

            // Add students and professors to course
            course.setStudents(List.of(student1, student2));
            course.setProfessors(List.of(professor1, professor2));

            // Persist the Course (CascadeType.ALL ensures Students and Professors are saved)
            em.persist(course);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
```

### Key Changes:
1. **Setting `course_professor_id` for `Course` and `Professor`**:
   - The `Course` object has a `courseProfessorId` field that is set to `1001L`.
   - Similarly, the `Professor` objects are assigned the same `courseProfessorId` (i.e., `1001L`).
   - This ensures that when the `Professor` entities are persisted, they are linked to the correct `Course` using the `course_professor_id` column, rather than the primary key `id`.

2. **Cascading**:
   - Since the relationship between `Course`, `Student`, and `Professor` is set to `CascadeType.ALL`, persisting the `Course` object will also persist the associated `Students` and `Professors`.

### What’s Happening:
- **Course**: A new course (e.g., "Physics") is created with a specific `course_professor_id` (1001L).
- **Students**: Two students ("Alice" and "Bob") are created and linked to the course.
- **Professors**: Two professors ("Dr. Einstein" and "Dr. Newton") are created with the same `course_professor_id` (1001L), which links them to the course.

This setup ensures that the `Professor` table's `course_professor_id` column is used to establish the link between the `Course` and `Professor` entities.
