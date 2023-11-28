package ecourse.app.manager.console.presentation.courses.enrollments;


import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.courseManagement.repositories.CourseStateRepository;
import ecourse.openCloseEnrollment.application.OpenCloseEnrollmentsController;
import org.slf4j.LoggerFactory;

public class CloseEnrollmentsUI extends AbstractUI {

    private final OpenCloseEnrollmentsController theController = new OpenCloseEnrollmentsController();

    @Override
    protected boolean doShow() {
        closeEnrollments();

        return false;

    }

    @Override
    public String headline() {
        return "Close Enrollments in a Course";
    }

    private String askForCourseName() {
        return String.valueOf(Console.readLine("Course Name"));
    }

    private void closeEnrollments() {

        String IN_PROGRESS = "Event Close Enrollments";

        Iterable<Course> courses = theController.findCourseByStateInEnrollments(CourseStateEnum.ENROLL);
        if (theController.findCourseByStateInEnrollments(CourseStateEnum.IN_PROGRESS) == null) {
            System.out.println("No courses available");
        } else {
            System.out.println("Select a course to change the state Closed Enrollments");
        }

        Course course = selectCourse(courses);

        theController.changeCourseState(course);

        //Print the new state of the course
        System.out.println("The new state of the course is: " + course.courseState().toString());
    }

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
