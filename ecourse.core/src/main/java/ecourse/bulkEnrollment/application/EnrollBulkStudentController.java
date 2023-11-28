package ecourse.bulkEnrollment.application;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import eapli.framework.io.util.Console;
import ecourse.courseManagement.application.ListCourseService;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.openCloseEnrollment.application.ListCourseToEnrollmentService;
import ecourse.studentManagement.application.StudentService;
import ecourse.userManagement.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EnrollBulkStudentController {
    private static final Logger logger = LoggerFactory.getLogger(EnrollBulkStudentController.class);
    private final ListCourseService listCourseService = new ListCourseService();
    private final StudentService studentService = new StudentService(PersistenceContext.repositories().students());
    private final ListCourseToEnrollmentService listCourseToEnrollmentService = new ListCourseToEnrollmentService();

    /**
     * Asks for a csv and returns its data
     *
     * @return List of String[] with the data from the csv
     */
    public List<String[]> askForFileCSVPathAndGetItsData() {
        File file;
        do {
            String filePath = Console.readLine("Insert file path (.csv):");
            file = new File(filePath);
            if (!file.exists() || !file.isFile() || !file.getName().endsWith(".csv")) {
                logger.error("Invalid file path or file type!");
                System.out.println("Invalid file path or file type!");
                file = null;
            }
        } while (file == null);

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            logger.error("Error reading CSV file: {}", e.getMessage());
            System.out.println("Error reading CSV file: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Returns a list of courses available to enroll in from the csv data provided
     *
     * @param csvData CSV data
     * @return List of courses available to enroll in from the csv data provided
     */
    public List<Course> getListOfCoursesAvailable(List<String[]> csvData) {
        Set<String> csvCourses = csvData
                .stream()
                .map(line -> line[1])
                .collect(Collectors.toSet());

        List<Course> availableCoursesToEnrollInCsv = listCourseService
                .getCoursesByState(CourseStateEnum.ENROLL)
                .stream()
                .filter(course -> csvCourses.contains(course.name()))
                .collect(Collectors.toList());

        if (availableCoursesToEnrollInCsv.isEmpty()) {
            logger.error("No courses available to enroll!");
            return Collections.emptyList();
        }

        return availableCoursesToEnrollInCsv;
    }

    /**
     * Returns the number of students that can't be enrolled in a course
     *
     * @param csvData CSV data
     * @param course  Course
     * @return Number of students that can't be enrolled in a course
     */
    public long getAvailability(List<String[]> csvData, Course course) {
        long courseAvailableSlots = listCourseToEnrollmentService.getNumberOfAvailableEnrollments(course);

        long numberOfLinesWithCourseName = csvData
                .stream()
                .filter(line -> line[1].equals(course.name()))
                .count();

        if (courseAvailableSlots == numberOfLinesWithCourseName || courseAvailableSlots > numberOfLinesWithCourseName) {
            return 0;
        } else {
            return numberOfLinesWithCourseName - courseAvailableSlots;
        }

    }

    /**
     * Enrolls students in a course.
     *
     * @param csvData CSV data
     * @param course  Course
     * @return List of students that couldn't be enrolled in the course
     */
    public List<String> enrollStudents(List<String[]> csvData, Course course) {
        List<String[]> studentsToEnroll = csvData
                .stream()
                .filter(line -> line[1].equals(course.name()))
                .toList();

        List<String> notEnrolledStudents = new ArrayList<>();

        studentsToEnroll.forEach(line -> {
            if (!enroll(course, line[0])) {
                notEnrolledStudents.add(line[0]);
            }
        });

        return notEnrolledStudents;
    }

    private boolean enroll(Course course, String studentNumber) {
        Student student = studentService.findByMecanographicNumber(studentNumber);
        if (student == null) {
            logger.warn("Student with number {} not found!", studentNumber);
            return false;
        }

        return listCourseToEnrollmentService.enroll(course, student);
    }
}

