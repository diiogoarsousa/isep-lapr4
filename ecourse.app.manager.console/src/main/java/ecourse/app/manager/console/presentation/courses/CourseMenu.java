package ecourse.app.manager.console.presentation.courses;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import ecourse.app.manager.console.presentation.courses.enrollments.OpenCloseEnrollmentsMenu;

public class CourseMenu extends Menu {

    private static final String TITLE = "Courses";
    private static final String SEPARATOR_LABEL = "--------------";

    private static final String RETURN = "Return ";


    private static final int EXIT_OPTION = 0;

    private static final int CREATE_A_COURSE_OPTION = 1;
    private static final int DELETE_A_COURSE_OPTION = 2;
    private static final int MANAGE_COURSES_OPTION = 3;
    private static final int LIST_PARTICIPANTS_COURSE_OPTION = 4;
    private static final int OPEN_A_COURSE_OPTION = 5;
    private static final int CLOSE_A_COURSE_OPTION = 6;
    private static final int ASSIGN_TEACHER_OPTION = 7;
    private static final int ENROLLMENTS_OPTION = 8;

    public CourseMenu() {
        super(TITLE);
        buildCoursesMenu();
    }

    private void buildCoursesMenu() {
        addItem(CREATE_A_COURSE_OPTION, "Create a Course", () -> new CreateCourseUI().show());
        addItem(DELETE_A_COURSE_OPTION, "Delete a Course", Actions.SUCCESS);
        addItem(MANAGE_COURSES_OPTION, "Manage Courses", Actions.SUCCESS);
        addItem(LIST_PARTICIPANTS_COURSE_OPTION, "List Course Participants", Actions.SUCCESS);
        addItem(OPEN_A_COURSE_OPTION, "Open a Course", () -> new OpenCourseUI().show());
        addItem(CLOSE_A_COURSE_OPTION, "Close a Course", () -> new CloseCourseUI().show());
        addItem(ASSIGN_TEACHER_OPTION, "Assign Teacher to Course", () -> new AssignTeacherUI().show());
        addSubMenu(ENROLLMENTS_OPTION, new OpenCloseEnrollmentsMenu());

        addItem(MenuItem.separator(SEPARATOR_LABEL));
        addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
    }
}
