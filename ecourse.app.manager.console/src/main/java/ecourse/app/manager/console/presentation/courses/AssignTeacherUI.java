package ecourse.app.manager.console.presentation.courses;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.courseManagement.domain.Course;
import ecourse.teacherAssignmentManagement.application.SetCourseTeachersController;
import ecourse.userManagement.domain.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssignTeacherUI extends AbstractUI {
    private static final Logger logger = LoggerFactory.getLogger(AssignTeacherUI.class);
    private final SetCourseTeachersController theController = new SetCourseTeachersController();


    @Override
    protected boolean doShow() {
        try {
            //get all courses not closed
            Iterable<Course> courses = theController.getCourses();
            //select the course to assign teachers
            theController.setCourse(selectCourse(courses));
            //get all teachers
            Iterable<Teacher> teachers = theController.getTeachers();
            //select the teachers to assign to the course
            selectTeachers(teachers);
            //select the Head Teacher from the teachers selected
            selectHeadTeacher();
            //save the teacher assignments
            theController.saveTeacherAssignments();
            return true;

        } catch (Exception e) {
            logger.error("An error occurred while assigning teachers to a course", e);
            return false;
        }
    }


    @Override
    public String headline() {
        return "Assign Teacher to Course";
    }

    /**
     * Method to select a course from a list of courses
     *
     * @param courses to select from
     * @return course
     */
    private Course selectCourse(Iterable<Course> courses) {

        int counter = 0;
        for (Course course : courses) {
            counter++;
            System.out.println(counter + ". " + course.name());
        }
        int option = Console.readInteger("Please select a course");
        counter = 0;
        for (Course course : courses) {
            counter++;
            if (counter == option) {
                return course;
            }
        }
        return null;
    }

    private Teacher selectTeacher(Iterable<Teacher> teachers, String message) {

        int counter = 0;
        for (Teacher teacher : teachers) {
            counter++;
            System.out.println(counter + ". " + teacher.toString());
        }
        int option = Console.readInteger(message);
        counter = 0;
        for (Teacher teacher : teachers) {
            counter++;
            if (counter == option) {
                return teacher;
            }
        }
        return null;
    }

    private void selectTeachers(Iterable<Teacher> teachers) {
        Teacher t;

        int option;
        do {
            t = selectTeacher(teachers, "Please select a teacher to assign to the course");
            if (t != null)
                theController.setTeacher(t);
            option = Console.readInteger("Do you want to add another teacher? (1 - Yes | 0 - No)");
        } while (option != 0);

    }

    private void selectHeadTeacher() {
        theController.setHeadTeacher(selectTeacher(theController.getTeachers(), "Please select the Head Teacher"));
    }
}