package ecourse.app.manager.console.presentation.adminSettings;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;

public class AdminSettingsMenu extends Menu {
    private static final String TITLE = "Admin Settings";
    private static final String RETURN = "Return ";

    private static final int EXIT_OPTION = 0;

    public AdminSettingsMenu() {
        super(TITLE);
        buildAdminSettingsMenu();
    }


    private void buildAdminSettingsMenu() {
        addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
    }
}
