package ecourse.app.teacher.console.presentation.listExamsInACourse;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;

public class CourseMenu extends Menu {
    private static final String MENU_TITLE = "Course Management";

    private static final int LIST_EXAMS_IN_A_COURSE_OPTION = 1;
    private static final int EXIT_OPTION = 0;


    public CourseMenu() {
        super(MENU_TITLE);
        buildCoursesMenu();
    }

    public void buildCoursesMenu() {
        addItem(MenuItem.of(LIST_EXAMS_IN_A_COURSE_OPTION, "List exams in a course", new ListExamsInACourseUI()::show));
        addItem(MenuItem.of(EXIT_OPTION, "Return ", Actions.SUCCESS));
    }

}
