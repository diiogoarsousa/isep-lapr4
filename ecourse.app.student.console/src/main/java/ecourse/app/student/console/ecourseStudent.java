package ecourse.app.student.console;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import ecourse.app.common.console.BaseApplication;
import ecourse.app.student.console.presentation.FrontMenu;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.userManagement.domain.BasePasswordPolicy;

public final class ecourseStudent extends BaseApplication {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private ecourseStudent() {
    }

    public static void main(final String[] args) {

        AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(),
                new PlainTextEncoder());

        new ecourseStudent().run(args);
    }

    @Override
    protected void doMain(String[] args) {
        new FrontMenu().show();

    }

    @Override
    protected String appTitle() {
        return "Student App";
    }

    @Override
    protected String appGoodbye() {
        return "Bye, bye";
    }

    @Override
    protected void doSetupEventHandlers(EventDispatcher dispatcher) {
        // NOP
    }
}
