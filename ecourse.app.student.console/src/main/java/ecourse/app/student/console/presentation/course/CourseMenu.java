package ecourse.app.student.console.presentation.course;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import ecourse.app.common.console.presentation.courses.ListAvailableCoursesUI;
import ecourse.app.student.console.presentation.course.requestEnrollmentInCourse.RequestEnrollmentInCourseUI;

public class CourseMenu extends Menu {
    private static final String TITLE = "Courses";
    private static final String SEPARATOR_LABEL = "--------------";
    private static final String RETURN = "Return ";

    private static final int EXIT_OPTION = 0;
    private static final int AVAILABLE_COURSES = 1;
    private static final int REQUEST_ENROLLMENT = 2;

    public CourseMenu() {
        super(TITLE);
        buildCoursesMenu();
    }

    private void buildCoursesMenu() {
        addItem(AVAILABLE_COURSES, "List Available Courses", () -> new ListAvailableCoursesUI().show());
        addItem(REQUEST_ENROLLMENT, "Request an Enrollment", () -> new RequestEnrollmentInCourseUI().show());
        addItem(MenuItem.separator(SEPARATOR_LABEL));
        addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
    }
}
