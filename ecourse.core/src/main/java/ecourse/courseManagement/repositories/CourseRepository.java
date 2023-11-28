package ecourse.courseManagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;

import java.util.UUID;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface CourseRepository extends DomainRepository<UUID, Course> {

    /**
     * Finds a course by its name
     *
     * @param courseName course name.
     * @return course.
     */
    Course findCourseByName(String courseName);

    /**
     * Finds a course by its state
     *
     * @param valueOf
     */
    Iterable<Course> findCourseByState(CourseStateEnum valueOf);

    /**
     * Finds all courses that are not closed.
     *
     * @return the courses.
     */
    Iterable<Course> findAllCoursesNotClosed();

    /**
     * Finds all courses that the status is ENROLL.
     *
     * @return an iterable of all courses.
     */
    Iterable<Course> findEnrollCourses();
}
