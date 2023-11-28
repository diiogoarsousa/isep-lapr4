package ecourse.app.manager.console.presentation.courses.enrollments;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;

public class OpenCloseEnrollmentsMenu extends Menu {

    private static final String OPEN_OR_CLOSE_ENROLLMENT = "Manage Enrollments";
    private static final String SEPARATOR_LABEL = "--------------";
    private static final String RETURN = "Return ";

    private static final int EXIT_OPTION = 0;
    private static final int OPEN_ENROLLMENT = 1;
    private static final int CLOSE_ENROLLMENT = 2;
    private static final int BULK_ENROLLMENT = 3;

    public OpenCloseEnrollmentsMenu() {
        super(OPEN_OR_CLOSE_ENROLLMENT);
        buildOpenCloseEnrollmentsMenu();
    }

    private void buildOpenCloseEnrollmentsMenu() {
        addItem(OPEN_ENROLLMENT, "Open Enrollment", () -> new OpenEnrollmentsUI().show());
        addItem(CLOSE_ENROLLMENT, "Close Enrollment", () -> new CloseEnrollmentsUI().show());
        addItem(BULK_ENROLLMENT, "Bulk Enrollment", () -> new EnrollBulkStudentUI().show());
        addItem(MenuItem.separator(SEPARATOR_LABEL));
        addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
    }
}
