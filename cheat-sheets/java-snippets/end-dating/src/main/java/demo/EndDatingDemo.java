package demo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EndDatingDemo {
	
	public static void main(String[] args) {
        // Example Course class with name, start date, and end date
        class Course {
            String name;
            LocalDate startDate;
            LocalDate endDate;

            public Course(String name, LocalDate startDate, LocalDate endDate) {
                this.name = name;
                this.startDate = startDate;
                this.endDate = endDate;
            }

            public String getName() {
                return name;
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

            @Override
            public String toString() {
                return "Course{" +
                        "name='" + name + '\'' +
                        ", startDate=" + startDate +
                        ", endDate=" + endDate +
                        '}';
            }
        }

        // Example list of courses
        List<Course> courses = Arrays.asList(
                new Course("Math", LocalDate.of(2024, 1, 10), LocalDate.of(2024, 6, 20)),
                new Course("Math", LocalDate.of(2024, 7, 1), null), // Missing end date
                new Course("History", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 8, 15)),
                new Course("Math", LocalDate.of(2024, 6, 25), null), // Missing end date
                new Course("History", LocalDate.of(2024, 9, 1), LocalDate.of(2024, 12, 15)),
                new Course("Math", LocalDate.of(2024, 8, 10), LocalDate.of(2024, 12, 1)) // Correct end date
        );

        // Group courses by name
        Map<String, List<Course>> groupedCourses = courses.stream()
                .collect(Collectors.groupingBy(Course::getName));

        // Update end dates for courses with missing end date
        groupedCourses.forEach((courseName, courseList) -> {
            // Sort by start date in descending order, so the latest course is at the front
            courseList.sort(Comparator.comparing(Course::getStartDate).reversed());

            // Set the latest course (first in the sorted list) end date to null
            Course latestCourse = courseList.get(0);
            latestCourse.setEndDate(null);

            // Set today's date as the end date for all other courses where endDate is null
            LocalDate today = LocalDate.now();
            courseList.stream()
                    .filter(course -> course != latestCourse && course.getEndDate() == null)  // Exclude the latest course and check if endDate is null
                    .forEach(course -> course.setEndDate(today));
        });

        // Print the updated courses
        courses.forEach(System.out::println);
    }
}
