package ecourse.app.student.console.presentation.myUser;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.myStudentUser.application.SignupController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignupRequestUI extends AbstractUI {
    private static final Logger logger = LoggerFactory.getLogger(SignupRequestUI.class);
    private final SignupController controller = new SignupController();

    @Override
    protected boolean doShow() {
        final UserDataWidget userData = new UserDataWidget();

        userData.show();

        final String mecanographicNumber = Console.readLine("Mecanographic Number");

        try {
            this.controller.signup(userData.username(), userData.password(),
                    userData.firstName(), userData.lastName(), userData.email(),
                    mecanographicNumber, userData.birthDay(), userData.taxNumber());
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            logger.error("Error performing the operation", e);
            System.out.println("Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Sign Up";
    }
}
