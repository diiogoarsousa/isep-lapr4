package ecourse.courseManagement.domain;

import ecourse.courseManagement.dto.CourseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private Course course;

    /**
     * Method to set up a course
     */
    @BeforeEach
    void setUp() {
        course = new Course(
                UUID.randomUUID(),
                "Test Course",
                CourseStateEnum.OPEN,
                Calendar.getInstance(),
                Calendar.getInstance(),
                10,
                20,
                "Test Head Teacher",
                "Test description"
        );
    }

    /**
     * Test to check if the course is close
     */
    @Test
    void courseStateClosed() {
        course.changeState(CourseStateEnum.CLOSED);
        assertEquals(CourseStateEnum.CLOSED, course.courseState());
    }

    /**
     * Test to get the course state
     */
    @Test
    void getState() {
        assertEquals(CourseStateEnum.OPEN, course.getState());
    }


    /**
     * Test to set the course state CLOSED
     */
    @Test
    void setStateClosed() {
        course.changeState(CourseStateEnum.CLOSED);
        assertEquals(CourseStateEnum.CLOSED, course.courseState());
    }

    /**
     * Test to set the course state OPEN
     */
    @Test
    void setStateOpen() {
        course.changeState(CourseStateEnum.OPEN);
        assertEquals(CourseStateEnum.OPEN, course.courseState());
    }

    @Test
    void testEquals() {

        UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        Course course1 = new Course(uuid, "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
        Course course2 = new Course(uuid, "Course 2", CourseStateEnum.CLOSED, Calendar.getInstance(), Calendar.getInstance(), 5, 15, "Teacher 2", "MyDescription 2");
        Course course3 = new Course(uuid, "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
        assertNotEquals(course1, course3);
        assertNotEquals(course1, course2);
    }

    @Test
    void testHashCode() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
        Course course2 = new Course(UUID.randomUUID(), "Course 2", CourseStateEnum.CLOSED, Calendar.getInstance(), Calendar.getInstance(), 5, 15, "Teacher 2", "MyDescription 2");
        Course course3 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");

        assertNotEquals(course1.hashCode(), course2.hashCode());
        assertNotEquals(course1.hashCode(), course3.hashCode());
    }

    @Test
    void sameAs() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
        Course course2 = new Course(UUID.randomUUID(), "Course 2", CourseStateEnum.CLOSED, Calendar.getInstance(), Calendar.getInstance(), 5, 15, "Teacher 2", "MyDescription 2");

        assertFalse(course1.sameAs(course2));
    }

    @Test
    void courseState() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");

        assertEquals(CourseStateEnum.OPEN, course1.courseState());
    }

    @Test
    void identity() {
        UUID uuid = UUID.randomUUID();
        Course course1 = new Course(uuid, "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
        Course course2 = course1;

        assertEquals(course2.identity(), course1.identity());
    }

    @Test
    void name() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");

        assertEquals("Course 1", course1.name());
    }

    @Test
    void isActive() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");

        assertFalse(course1.isActive());
    }

    @Test
    void toogleState() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");

        assertTrue(course1.toogleState());
        assertTrue(course1.isActive());
        assertFalse(course1.toogleState());
        assertFalse(course1.isActive());
    }

    @Test
    void toDTO() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
        CourseDTO courseDTO = course1.toDTO();

        assertEquals("Course 1", courseDTO.getName());
        assertEquals(CourseStateEnum.CLOSED.toString(), courseDTO.getState());
        assertEquals(10, courseDTO.getMinEnrollments());
        assertEquals(20, courseDTO.getMaxEnrollments());
        assertEquals("Teacher 1", courseDTO.getHeadTeacher());
        assertEquals("MyDescription 1", courseDTO.getDescription());
    }


    @Test
    void update() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");

        course1.changeState(CourseStateEnum.CLOSED);

        assertEquals(CourseStateEnum.CLOSED, course1.courseState());
        assertTrue(course1.isActive());
    }
}