package ecourse.app.manager.console.presentation;

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
import ecourse.app.manager.console.presentation.adminSettings.AdminSettingsMenu;
import ecourse.app.manager.console.presentation.courses.CourseMenu;
import ecourse.app.manager.console.presentation.users.UsersMenu;
import ecourse.userManagement.domain.BaseRoles;

public class MainMenu extends AbstractUI {

    private static final String RETURN = "Return ";

    private static final int EXIT_OPTION = 0;
    private static final int LOGIN_OPTION = 1;
    private static final int MEETINGS_OPTION = 2;
    private static final int COURSES_OPTION = 3;

    //↓ MANAGER ONLY OPTIONS ↓
    private static final int USER_MANAGEMENT_OPTION = 4;
    private static final int SETTINGS_OPTION = 5;
    //↑ MANAGER ONLY OPTIONS ↑
    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

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
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {
        return authz.session().map(s -> "[ @" + s.authenticatedUser().identity() + " ]")
                .orElse("[ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();
        final Menu meetingMenu = new MeetingMenu();
        final Menu loginMenu = new LoginMenu();
        final Menu coursesMenu = new CourseMenu();

        mainMenu.addSubMenu(LOGIN_OPTION, loginMenu);
        mainMenu.addSubMenu(MEETINGS_OPTION, meetingMenu);
        mainMenu.addSubMenu(COURSES_OPTION, coursesMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        // if the logged-in user is a manager, show the manager extra menus
        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.MANAGER)) {
            final Menu usersMenu = new UsersMenu();
            final Menu settingsMenu = new AdminSettingsMenu();

            mainMenu.addSubMenu(USER_MANAGEMENT_OPTION, usersMenu);
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }
}
