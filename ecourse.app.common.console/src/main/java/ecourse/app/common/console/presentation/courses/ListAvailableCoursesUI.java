package ecourse.app.common.console.presentation.courses;

import eapli.framework.presentation.console.AbstractUI;
import ecourse.availableCourses.application.listAllCoursesAvailableToMeController;
import ecourse.courseManagement.domain.Course;

import java.util.List;

import static ecourse.app.common.console.utils.UiUtils.listArrowIndicator;

public class ListAvailableCoursesUI extends AbstractUI {
    private final listAllCoursesAvailableToMeController controller = new listAllCoursesAvailableToMeController();

    @Override
    protected boolean doShow() {
        availableCourses();
        return false;
    }

    private void availableCourses() {
        List<Course> courses = controller.availableCourses();
        for (var course : courses) {
            System.out.println(listArrowIndicator + course.name());
        }
    }

    @Override
    public String headline() {
        return "List all available courses";
    }
}
