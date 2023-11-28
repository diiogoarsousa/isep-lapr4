package ecourse.app.manager.console.presentation.users;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;


public class UsersMenu extends Menu {

    private static final String TITLE = "Users";
    private static final String SEPARATOR_LABEL = "--------------";
    private static final String RETURN = "Return ";

    private static final int EXIT_OPTION = 0;
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACTIVATE_USER_OPTION = 4;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 5;

    public UsersMenu() {
        super(TITLE);
        buildUsersMenu();
    }


    private void buildUsersMenu() {
        addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        addItem(ACTIVATE_USER_OPTION, "Activate User", new ActivateUserAction());
        addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request", new AcceptRefuseSignupRequestAction());
        addItem(MenuItem.separator(SEPARATOR_LABEL));
        addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
    }
}
