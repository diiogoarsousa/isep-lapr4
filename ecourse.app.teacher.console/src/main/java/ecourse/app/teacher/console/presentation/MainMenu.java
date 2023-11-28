package ecourse.app.teacher.console.presentation;

import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import ecourse.Application;
import ecourse.app.common.console.mySchedules.MeetingMenu;
import ecourse.app.common.console.presentation.authz.LoginMenu;
import ecourse.app.teacher.console.presentation.courseClass.CourseClassMenu;
import ecourse.app.teacher.console.presentation.exams.ExamMenu;
import ecourse.app.teacher.console.presentation.listExamsInACourse.CourseMenu;
import ecourse.userManagement.domain.BaseRoles;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {
    private static final String SEPARATOR_LABEL = "--------------";
    private static final int EXIT_OPTION = 0;
    private static final int MY_USER_OPTION = 1;
    private static final int MEETING_OPTION = 2;
    private static final int EXAM_OPTION = 3;
    private static final int COURSE_OPTION = 4;
    private static final int COURSECLASS_OPTION = 5;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final MenuRenderer renderer;

    public MainMenu() {
        Menu menu = buildMainMenu();
        renderer = getRenderer(menu);
    }

    private MenuRenderer getRenderer(final Menu menu) {
        final MenuRenderer theRenderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            theRenderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            theRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return theRenderer;
    }

    @Override
    public boolean doShow() {
        return renderer.render();
    }

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "[ @" + s.authenticatedUser().identity() + " ]")
                .orElse("[ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();
        final Menu myUserMenu = new LoginMenu(BaseRoles.TEACHER);
        final Menu meetingMenu = new MeetingMenu();
        final Menu examMenu = new ExamMenu();
        final Menu courseMenu = new CourseMenu();
        final Menu courseClassMenu = new CourseClassMenu();

        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);
        mainMenu.addSubMenu(MEETING_OPTION, meetingMenu);
        mainMenu.addSubMenu(EXAM_OPTION, examMenu);
        mainMenu.addSubMenu(COURSE_OPTION, courseMenu);
        mainMenu.addSubMenu(COURSECLASS_OPTION, courseClassMenu);


        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }
        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }
}
