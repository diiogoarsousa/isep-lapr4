package ecourse.scheduleMeeting.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.scheduleMeeting.domain.Meeting;
import ecourse.scheduleMeeting.repositories.MeetingRepository;

import java.util.Optional;

public class CancelMeetingController {
    private final MeetingRepository meetingsRepo = PersistenceContext.repositories().meetings();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Return the current user meetings
     * @return Iterable<Meeting>
     */
    public Iterable<Meeting> listMyMeetings() {
        return meetingsRepo.findMeetingsByUser(currentUser().get());
    }

    /**
     * Returns the current user
     *
     * @return currentUser
     */
    private Optional<SystemUser> currentUser() {
        return authz.session().flatMap(s -> userSvc.userOfIdentity(s.authenticatedUser().username()));
    }

    /**
     *  Delete meeting
     * @param meeting
     */
    public void cancelMeeting(final Meeting meeting) {
        meetingsRepo.delete(meeting);
    }
}
