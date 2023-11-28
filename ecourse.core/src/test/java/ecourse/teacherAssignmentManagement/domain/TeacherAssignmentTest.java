package ecourse.teacherAssignmentManagement.domain;

import eapli.framework.infrastructure.authz.domain.model.*;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.teacherManagement.domain.Acronym;
import ecourse.teacherManagement.domain.BirthDate;
import ecourse.teacherManagement.domain.TaxNumber;
import ecourse.userManagement.domain.BaseRoles;
import ecourse.userManagement.domain.Teacher;
import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherAssignmentTest {

    private Course course;
    private Teacher teacher;

    public static SystemUser dummyUser(final String username, final Role... roles) {
        // should we load from spring context?
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy", BaseRoles.TEACHER);
    }

    /**
     * Method to set up a course
     */
    @BeforeEach
    void setUp() {
        course = new Course(UUID.randomUUID(), "Test Course", CourseStateEnum.OPEN, Calendar.getInstance(),
                Calendar.getInstance(), 10, 20, "Test Head Teacher", "Test description");

        teacher = new Teacher(getNewDummyUser(), new Acronym("ABC"), new BirthDate(Calendar.getInstance()), new TaxNumber(123456));


    }

    @org.junit.jupiter.api.Test
    public void ensureTeacherIsNotHEadTeacher() {
        final TeacherAssignment ta = new TeacherAssignment(teacher, course, false);
        assertFalse(ta.isHeadTeacher());
    }

    @org.junit.jupiter.api.Test
    public void ensureTeacherIsHEadTeacher() {
        final TeacherAssignment ta = new TeacherAssignment(teacher, course, true);
        assertTrue(ta.isHeadTeacher());
    }

    @org.junit.jupiter.api.Test
    public void ensureTeacherAssignmentEqualsFailsForTheSameHeadTeacher() throws Exception {
        final TeacherAssignment ta = new TeacherAssignment(teacher, course, true);
        final TeacherAssignment tb = new TeacherAssignment(teacher, course, false);
        assertNotEquals(ta, tb);
    }

    @org.junit.jupiter.api.Test
    public void ensureTeacherAssignmentEqualsPassesForTheSameHeadTeacher() throws Exception {
        final TeacherAssignment ta = new TeacherAssignment(teacher, course, true);
        final TeacherAssignment tb = new TeacherAssignment(teacher, course, true);
        assertNotEquals(ta, tb);
    }

    @org.junit.jupiter.api.Test
    public void ensureTeacherAssignmentEqualsFailsForDifferentTeacher() throws Exception {
        final TeacherAssignment ta = new TeacherAssignment(teacher, course, true);
        Teacher anotherTeacher = new Teacher(getNewDummyUser(), new Acronym("ABC"), new BirthDate(Calendar.getInstance()), new TaxNumber(123456));

        final TeacherAssignment tb = new TeacherAssignment(anotherTeacher, course, true);
        assertNotEquals(ta, tb);
    }

    @org.junit.jupiter.api.Test
    public void ensureTeacherAssignmentEqualsFailsForDifferentCourse() throws Exception {
        final TeacherAssignment ta = new TeacherAssignment(teacher, course, true);
        Course anotherCourse = new Course(UUID.randomUUID(), "Test Course", CourseStateEnum.OPEN, Calendar.getInstance(),
                Calendar.getInstance(), 15, 20, "Test Head Teacher", "Test description");

        final TeacherAssignment tb = new TeacherAssignment(teacher, anotherCourse, true);
        assertNotEquals(ta, tb);
    }
}