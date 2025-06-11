package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class EndDatingCoursesTest {

    @Test
    void testEndDatingForLecturers() {
        // Create tutors
        Tutor lecturer1 = new Tutor("Lecturer 1", TutorType.LECTURER);
        Tutor lecturer2 = new Tutor("Lecturer 2", TutorType.LECTURER);

        // Create courses with start dates in ascending order
        Course course1 = new Course("Course 1", LocalDate.of(2024, 1, 1), null, List.of(lecturer1));
        Course course2 = new Course("Course 2", LocalDate.of(2024, 2, 1), null, List.of(lecturer1));
        Course course3 = new Course("Course 3", LocalDate.of(2024, 3, 1), null, List.of(lecturer2));
        Course course4 = new Course("Course 4", LocalDate.of(2024, 4, 1), null, List.of(lecturer1, lecturer2));

        List<Course> courses = List.of(course1, course2, course3, course4);

        // Group courses by lecturer
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

        // End-date all courses except the latest for each lecturer
        LocalDate today = LocalDate.now();
        lecturerToCourses.forEach((tutor, tutorCourses) -> {
            tutorCourses.sort(Comparator.comparing(Course::getStartDate));
            for (int i = 0; i < tutorCourses.size() - 1; i++) {
                Course course = tutorCourses.get(i);
                if (course.getEndDate() == null) {
                    course.setEndDate(today);
                }
            }
        });

        // Assertions for Lecturer 1
        assertEquals(today, course1.getEndDate(), "Course 1 end date should be set");
        assertEquals(today, course2.getEndDate(), "Course 2 end date should be set");
        assertEquals(null, course4.getEndDate(), "Latest course for Lecturer 1 should not be end-dated");

        // Assertions for Lecturer 2
        assertEquals(today, course3.getEndDate(), "Course 3 end date should be set");
        assertEquals(null, course4.getEndDate(), "Latest course for Lecturer 2 should not be end-dated");
    }
    
    @Test
    void testEndDatingWithTodayAsEndDate() {
        // Create tutors
        Tutor lecturer1 = new Tutor("Lecturer 1", TutorType.LECTURER);
        Tutor lecturer2 = new Tutor("Lecturer 2", TutorType.LECTURER);

        // Create courses with start dates in ascending order
        Course course1 = new Course("Course 1", LocalDate.of(2024, 1, 1), null, List.of(lecturer1));
        Course course2 = new Course("Course 2", LocalDate.of(2024, 2, 1), null, List.of(lecturer1));
        Course course3 = new Course("Course 3", LocalDate.of(2024, 3, 1), null, List.of(lecturer2));
        Course course4 = new Course("Course 4", LocalDate.of(2024, 4, 1), null, List.of(lecturer2));
        Course course5 = new Course("Course 5", LocalDate.of(2024, 5, 1), null, List.of(lecturer1));
        Course course6 = new Course("Course 6", LocalDate.of(2024, 6, 1), null, List.of(lecturer2));

        List<Course> courses = List.of(course1, course2, course3, course4, course5, course6);

        // Group courses by lecturer
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

        // End-date all courses except the latest for each lecturer, using today as the end date
        LocalDate today = LocalDate.now();
        lecturerToCourses.forEach((tutor, tutorCourses) -> {
            tutorCourses.sort(Comparator.comparing(Course::getStartDate)); // Sort courses by start date
            for (int i = 0; i < tutorCourses.size() - 1; i++) {
                Course course = tutorCourses.get(i);
                if (course.getEndDate() == null) {
                    course.setEndDate(today); // Set the end date to today
                }
            }
        });

        // Assertions for Lecturer 1
        assertEquals(today, course1.getEndDate(), "Course 1 end date should be set to today");
        assertEquals(today, course2.getEndDate(), "Course 2 end date should be set to today");
        assertEquals(null, course5.getEndDate(), "Latest course for Lecturer 1 should not be end-dated");

        // Assertions for Lecturer 2
        assertEquals(today, course3.getEndDate(), "Course 3 end date should be set to today");
        assertEquals(today, course4.getEndDate(), "Course 4 end date should be set to today");
        assertEquals(null, course6.getEndDate(), "Latest course for Lecturer 2 should not be end-dated");
    }

}
