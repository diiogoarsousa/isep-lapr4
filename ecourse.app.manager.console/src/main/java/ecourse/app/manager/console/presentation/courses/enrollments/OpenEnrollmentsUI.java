package ecourse.app.manager.console.presentation.courses.enrollments;


import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.courseManagement.repositories.CourseStateRepository;
import ecourse.openCloseEnrollment.application.OpenCloseEnrollmentsController;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.StreamSupport;

import static ecourse.app.common.console.utils.UiUtils.chooseCourse;

public class OpenEnrollmentsUI extends AbstractUI {


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OpenEnrollmentsUI.class);
    private final OpenCloseEnrollmentsController theController = new OpenCloseEnrollmentsController();

    @Override
    protected boolean doShow() {
        List<Course> courses = StreamSupport.stream(theController.findCourseByStateInEnrollments(CourseStateEnum.OPEN).spliterator(), false)
                .toList();
        if (theController.findCourseByStateInEnrollments(CourseStateEnum.OPEN) == null) {
            System.out.println("No courses available");
        } else {
            System.out.println("Select a course to change the state Enroll:");
        }
        Course course = chooseCourse(courses);

        //Send the course to the controller (OpenCloseCourseController) to change the state to "open" or "closed" respectively
        theController.changeCourseState(course);

        System.out.println("The new state of the course is: " + course.courseState().getDescription());

        return false;
    }

    @Override
    public String headline() {
        return "Open Enrollments in a Course";
    }
}
