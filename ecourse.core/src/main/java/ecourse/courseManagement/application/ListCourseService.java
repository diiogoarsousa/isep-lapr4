package ecourse.courseManagement.application;

import eapli.framework.application.ApplicationService;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.requestEnrollment.domain.ApprovalStatusEnum;
import ecourse.requestEnrollment.domain.Enrollment;
import ecourse.requestEnrollment.domain.EnrollmentId;
import ecourse.requestEnrollment.repositories.EnrollmentsRepository;
import ecourse.userManagement.domain.Student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * An application service to avoid code duplication since getting the list of all courses is needed
 * in several use cases.
 */
@ApplicationService
public class ListCourseService {
    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();
    private final EnrollmentsRepository enrollmentsRepository = PersistenceContext.repositories().enrollments();

    /**
     * Finds all courses
     *
     * @return an iterable of all courses
     */
    public Iterable<Course> allCourses() {
        return courseRepository.findAll();
    }

    /**
     * Finds a course by its state
     *
     * @param courseState the state of the course
     * @return a list of courses with the given state
     */
    public List<Course> findCourseByState(CourseStateEnum courseState) {
        List<Course> courses = new ArrayList<>();
        courseRepository.findCourseByState(courseState).forEach(courses::add);
        return courses;
    }

    /**
     * Toggles the state of a course between open and closed.
     */
    public void toggleOpenCloseCourse(Course course) {
        if (course.getState().equals(CourseStateEnum.OPEN)) {
            course.changeState(CourseStateEnum.CLOSED);
        } else {
            course.changeState(CourseStateEnum.OPEN);
        }
        courseRepository.save(course);
    }

    /**
     * Returns a list of exams of a course.
     *
     * @param course Course to get exams from.
     * @return List of exams of a course.
     */
    public List<String> getExamsOfACourse(Course course) {
        List<String> exams = new ArrayList<>();
        course.getExams().forEach(exam -> exams.add(exam.title()));
        return exams;
    }

    /**
     * Returns a list of courses with the given state.
     *
     * @param courseStateEnum the state of the course.
     * @return a list of courses with the given state.
     */
    public List<Course> getCoursesByState(CourseStateEnum courseStateEnum) {
        return StreamSupport.stream(allCourses().spliterator(), false)
                .filter(course -> course.courseState() == courseStateEnum)
                .collect(Collectors.toList());
    }

    /**
     * Requests enrollment in a course
     *
     * @param course to enroll in
     * @return true if the enrollment was requested successfully, false otherwise.
     */
    public boolean requestEnrollment(Course course, Student student) {
        Enrollment enrollment = new Enrollment(new EnrollmentId(course, student), Calendar.getInstance(), ApprovalStatusEnum.REQUESTED);

        return enrollmentsRepository.save(enrollment) != null;
    }

    /**
     * Lists all courses that a student is enrolled in with a given approval status.
     *
     * @param student            the student to get the courses from.
     * @param approvalStatusEnum the approval status of the enrollment.
     * @return a list of courses that a student is enrolled in with a given approval status.
     */
    public List<Course> listCoursesByStudent(Student student, ApprovalStatusEnum approvalStatusEnum) {
        List<Course> courses = new ArrayList<>();
        return StreamSupport.stream(enrollmentsRepository.findEnrollmentsByStudent(student).spliterator(), false)
                .filter(enrollment -> enrollment.getState() == approvalStatusEnum)
                .map(enrollment -> enrollment.identity().getCourse())
                .collect(Collectors.toList());
    }
}
