package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class LecturerToCoursesTest {

    @Test
    void testLecturerToCoursesMapping() {
        // Create tutors
        Tutor lecturer1 = new Tutor("Lecturer 1", TutorType.LECTURER);
        Tutor lecturer2 = new Tutor("Lecturer 2", TutorType.LECTURER);
        Tutor ta = new Tutor("TA 1", TutorType.TA);

        // Create courses
        Course course1 = new Course("Course 1", LocalDate.of(2024, 1, 1), null, List.of(lecturer1, lecturer2, ta));
        Course course2 = new Course("Course 2", LocalDate.of(2024, 2, 1), null, List.of(lecturer1));
        Course course3 = new Course("Course 3", LocalDate.of(2024, 3, 1), null, List.of(lecturer2));
        Course course4 = new Course("Course 4", LocalDate.of(2024, 4, 1), null, List.of(lecturer1));
        Course course5 = new Course("Course 5", LocalDate.of(2024, 5, 1), null, List.of(lecturer2));
        Course course6 = new Course("Course 6", LocalDate.of(2024, 6, 1), null, List.of(lecturer1, lecturer2));

        List<Course> courses = List.of(course1, course2, course3, course4, course5, course6);

        // Code under test
        Map<Tutor, List<Course>> lecturerToCourses = new HashMap<>();

        for (Course course : courses) {
            for (Tutor tutor : course.getTutors()) {
                if (tutor.getType() == TutorType.LECTURER) {
                    lecturerToCourses
                        .computeIfAbsent(tutor, t -> new ArrayList<>())
                        .add(course);
                }
            }
        }

        // Assertions
        assertEquals(4, lecturerToCourses.get(lecturer1).size(), "Lecturer 1 should teach 4 courses");
        assertEquals(4, lecturerToCourses.get(lecturer2).size(), "Lecturer 2 should teach 4 courses");

        // Check specific courses
        assertEquals(
            List.of(course1, course2, course4, course6),
            lecturerToCourses.get(lecturer1),
            "Lecturer 1 should have courses 1, 2, 4, 6"
        );

        assertEquals(
            List.of(course1, course3, course5, course6),
            lecturerToCourses.get(lecturer2),
            "Lecturer 2 should have courses 1, 3, 5, 6"
        );

        // Ensure TA is not in the map
        assertEquals(null, lecturerToCourses.get(ta), "TAs should not be included in the map");
    }
}
