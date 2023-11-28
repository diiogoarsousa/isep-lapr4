package ecourse.userManagement.application.eventhandlers;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.pubsub.EventHandler;
import ecourse.studentManagement.domain.events.SignupAcceptedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignupAcceptedWatchDog implements EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(SignupAcceptedWatchDog.class);

    @Override
    public void onEvent(final DomainEvent domainevent) {
        assert domainevent instanceof SignupAcceptedEvent;

        final SignupAcceptedEvent event = (SignupAcceptedEvent) domainevent;

        final AddUserOnSignupAcceptedController controller = new AddUserOnSignupAcceptedController();
        try {
            controller.addUser(event);
        } catch (final IntegrityViolationException e) {
            // TODO provably should send some warning email...
            logger.error("Unable to register new user on signup event", e);
        }
    }
}
