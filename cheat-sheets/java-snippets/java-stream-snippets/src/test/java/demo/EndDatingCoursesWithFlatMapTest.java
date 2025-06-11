package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class EndDatingCoursesWithFlatMapTest {

	@Test
    void testEndDatingForLecturersUsingFlatMap() {
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

        // Get all LECTURER courses and group them by tutor using flatMap
        LocalDate today = LocalDate.now();

        Map<Tutor, List<Course>> lecturerToCourses = courses.stream()
                .flatMap(course -> course.getTutors().stream()  // Flatten all tutors
                        .filter(tutor -> tutor.getType() == TutorType.LECTURER) // Filter for LECTURERs
                        .map(tutor -> Map.entry(tutor, course))) // Create a pair (tutor, course)
                .collect(Collectors.groupingBy(Map.Entry::getKey, // Group by tutor
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList()))); // Collect courses

        // End-date all courses except the latest for each lecturer, using today as the end date
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
	
	@Test
    void testEndDatingWithTodayAsEndDateUsingFlatMap() {
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

        // Get all LECTURER courses and group them by tutor using flatMap
        LocalDate today = LocalDate.now();

        Map<Tutor, List<Course>> lecturerToCourses = courses.stream()
                .flatMap(course -> course.getTutors().stream()  // Flatten all tutors
                        .filter(tutor -> tutor.getType() == TutorType.LECTURER) // Filter for LECTURERs
                        .map(tutor -> Map.entry(tutor, course))) // Create a pair (tutor, course)
                .collect(Collectors.groupingBy(Map.Entry::getKey, // Group by tutor
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList()))); // Collect courses

        // End-date all courses except the latest for each lecturer, using today as the end date
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
	
	@Test
    void testFlatMapAndGroupByStepwiseWithDebug() {
        // Create tutors
        Tutor lecturer1 = new Tutor("Lecturer 1", TutorType.LECTURER);
        Tutor lecturer2 = new Tutor("Lecturer 2", TutorType.LECTURER);

        // Create courses with start dates in ascending order
        Course course1 = new Course("Course 1", LocalDate.of(2024, 1, 1), null, List.of(lecturer1, lecturer2));
        Course course2 = new Course("Course 2", LocalDate.of(2024, 2, 1), null, List.of(lecturer1));
        Course course3 = new Course("Course 3", LocalDate.of(2024, 3, 1), null, List.of(lecturer2));

        List<Course> courses = List.of(course1, course2, course3);

        // Step 1: Apply flatMap to get a stream of Map.Entry<Tutor, Course>
        System.out.println("=== Step 1: Applying flatMap ===");
        List<Map.Entry<Tutor, Course>> tutorCoursePairs = courses.stream()
                .flatMap(course -> {
                    System.out.println("Processing course: " + course.getName());
                    return course.getTutors().stream()
                            .peek(tutor -> System.out.println("  Found tutor: " + tutor.getName()))
                            .filter(tutor -> {
                                boolean isLecturer = tutor.getType() == TutorType.LECTURER;
                                System.out.println("  Is lecturer? " + isLecturer);
                                return isLecturer;
                            })
                            .map(tutor -> {
                                System.out.println("  Mapping tutor-course pair: " + tutor.getName() + " -> " + course.getName());
                                return Map.entry(tutor, course);
                            });
                })
                .collect(Collectors.toList());

        // Debug output after flatMap
        System.out.println("\nFlatMap Result:");
        tutorCoursePairs.forEach(pair -> System.out.println(pair.getKey().getName() + " -> " + pair.getValue().getName()));
        assertEquals(4, tutorCoursePairs.size(), "The flatMap should produce 4 pairs.");

        // Step 2: Apply groupingBy to group by tutor
        System.out.println("\n=== Step 2: Applying groupingBy ===");
        Map<Tutor, List<Course>> lecturerToCourses = tutorCoursePairs.stream()
                .peek(pair -> System.out.println("Grouping pair: " + pair.getKey().getName() + " -> " + pair.getValue().getName()))
                .collect(Collectors.groupingBy(Map.Entry::getKey, // Group by tutor
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        // Debug output after groupingBy
        System.out.println("\nGroupingBy Result:");
        lecturerToCourses.forEach((tutor, coursesList) -> {
            System.out.println(tutor.getName() + " -> " + coursesList.stream()
                    .map(Course::getName)
                    .collect(Collectors.joining(", ")));
        });
        assertEquals(2, lecturerToCourses.size(), "There should be 2 groups: Lecturer 1 and Lecturer 2.");
    }

}
