package ecourse.app.manager.console.presentation.courses;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.courseManagement.application.OpenCloseCourseController;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.courseManagement.repositories.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenCourseUI extends AbstractUI {
    private final OpenCloseCourseController theController = new OpenCloseCourseController();

    /**
     * Method to show the UI
     *
     * @return false
     */
    @Override
    protected boolean doShow() {

        openCloseCourse();

        return false;
    }

    @Override
    public String headline() {
        return "Open a Course";
    }

    /**
     * Method to open and close a course (openCloseCourse)
     */
    private void openCloseCourse() {
        //Send the option to the controller (OpenCloseCourseController) and get a list of courses with state "close"
        // or "open" to change the state to "open" or "closed" respectively
        Iterable<Course> courses = theController.findCourseByState(CourseStateEnum.CLOSE);

        //Ask the user to select a course from the list of courses returned by the controller (OpenCloseCourseController)
        Course course = selectCourse(courses);

        //Send the course to the controller (OpenCloseCourseController) to change the state to "open" or "closed" respectively
        theController.openCloseCourse(course);

        //Print the new state of the course
        System.out.println("The new state of the course is: " + course.getState().toString());

    }

    /**
     * Method to select a course from a list of courses
     *
     * @param courses
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

}
