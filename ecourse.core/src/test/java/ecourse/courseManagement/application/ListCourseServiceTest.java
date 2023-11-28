package ecourse.courseManagement.application;

import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseBuilder;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCourseServiceTest {

    private ListCourseService service;

    private Course course;

    private Course course1;

    private Course course2;

    @BeforeEach
    void setUp() {


        service = new ListCourseService();

        course = new CourseBuilder()
                .identified(UUID.randomUUID())
                .named("Test Course")
                .withState(CourseStateEnum.OPEN)
                .withStartDate(Calendar.getInstance())
                .withEndDate(Calendar.getInstance())
                .withMaxEnrolments(10)
                .described("Test description")
                .build();

        course1 = new CourseBuilder()
                .identified(UUID.randomUUID())
                .named("Test Course 1")
                .withState(CourseStateEnum.OPEN)
                .withStartDate(Calendar.getInstance())
                .withEndDate(Calendar.getInstance())
                .withMaxEnrolments(10)
                .described("Test description 1")
                .build();

        course2 = new CourseBuilder()
                .identified(UUID.randomUUID())
                .named("Test Course 2")
                .withState(CourseStateEnum.CLOSED)
                .withStartDate(Calendar.getInstance())
                .withEndDate(Calendar.getInstance())
                .withMaxEnrolments(20)
                .described("Test description 2")
                .build();

        List<Course> courses = List.of(course, course1, course2);

        CourseRepository repo = PersistenceContext.repositories().courses();
    }

    @Test
    void allCourses() {
        List<Course> courses = List.of(course, course1, course2);

        CourseRepository repo = PersistenceContext.repositories().courses();

        Iterable<Course> actualCourses = service.allCourses();

        assertEquals(repo, actualCourses);
    }

//    @Test
    //TODO : UPDATE WITH ENUM
//    void findCourseByState() {
//        List<Course> expectedCourses = List.of(course, course1);
//
//        List<Course> actualCourses = service.findCourseByState("OPEN");
//
//        assertEquals(expectedCourses, actualCourses);
//    }

    @Test
    void toggleOpenCloseCourse() {
        assertEquals(CourseStateEnum.OPEN, course.getState());

        service.toggleOpenCloseCourse(course);

        assertEquals(CourseStateEnum.CLOSED, course.getState());
    }
}