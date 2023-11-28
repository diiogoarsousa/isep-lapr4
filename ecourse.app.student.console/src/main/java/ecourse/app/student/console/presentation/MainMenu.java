package ecourse.app.student.console.presentation;

import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import ecourse.app.common.console.mySchedules.MeetingMenu;
import ecourse.app.common.console.presentation.authz.LoginMenu;
import ecourse.app.student.console.presentation.course.CourseMenu;
import ecourse.app.student.console.presentation.exam.ExamMenu;

class MainMenu extends StudentUI {
    private static final String SEPARATOR_LABEL = "--------------";

    private static final int EXIT_OPTION = 0;
    private static final int LOGIN_OPTION = 1;
    private static final int MEETINGS_OPTION = 2;
    private static final int COURSES_OPTION = 3;
    private static final int EXAMS_OPTION = 4;

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer =
                new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new LoginMenu();
        mainMenu.addSubMenu(LOGIN_OPTION, myUserMenu);

        final Menu meetingMenu = new MeetingMenu();
        mainMenu.addSubMenu(MEETINGS_OPTION, meetingMenu);

        final Menu coursesMenu = new CourseMenu();
        mainMenu.addSubMenu(COURSES_OPTION, coursesMenu);

        final Menu examsMenu = new ExamMenu();
        mainMenu.addSubMenu(EXAMS_OPTION, examsMenu);

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }
}
