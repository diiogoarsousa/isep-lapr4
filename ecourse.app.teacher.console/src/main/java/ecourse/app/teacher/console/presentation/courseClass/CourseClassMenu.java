package ecourse.app.teacher.console.presentation.courseClass;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;

public class CourseClassMenu extends Menu {


    private static final String MENU_TITLE = "Course Class";
    private static final String SEPARATOR_LABEL = "--------------";

    private static final String RETURN = "Return ";


    private static final int EXIT_OPTION = 0;

    private static final int SCHEDULE_A_COURSE_CLASS_OPTION = 1;



    public CourseClassMenu() {
        super(MENU_TITLE);
        buildCourseClassMenu();
    }

    public void buildCourseClassMenu() {
        addItem(SCHEDULE_A_COURSE_CLASS_OPTION, "Schedule a Course Class", () -> new ScheduleCourseClassUI().show());
        addItem(MenuItem.separator(SEPARATOR_LABEL));
        addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
    }
}
