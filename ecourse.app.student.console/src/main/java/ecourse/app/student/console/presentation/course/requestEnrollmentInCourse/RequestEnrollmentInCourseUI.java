package ecourse.app.student.console.presentation.course.requestEnrollmentInCourse;

import eapli.framework.presentation.console.AbstractUI;
import ecourse.requestEnrollment.application.RequestEnrollmentController;

import static eapli.framework.io.util.Console.readInteger;

public class RequestEnrollmentInCourseUI extends AbstractUI {
    private final RequestEnrollmentController controller = new RequestEnrollmentController();

    @Override
    protected boolean doShow() {
        for (int i = 0; i < controller.listReadyToEnrollCourses().size(); i++) {
            System.out.println((i + 1) + ". " + controller.listReadyToEnrollCourses().get(i).name());
        }

        int option = readInteger("Please, select a course:");

        if (controller.requestEnrollment(controller.listReadyToEnrollCourses().get(option - 1))) {
            System.out.println("Course enrollment request sent successfully!");
            return true;
        }

        System.out.println("Course enrollment request failed!");
        return false;
    }

    @Override
    public String headline() {
        return "Request Course Enrollment";
    }
}
