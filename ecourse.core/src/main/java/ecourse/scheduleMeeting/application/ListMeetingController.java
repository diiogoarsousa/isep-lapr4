package ecourse.scheduleMeeting.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.scheduleMeeting.domain.Meeting;
import ecourse.scheduleMeeting.domain.MeetingParticipant;
import ecourse.scheduleMeeting.repositories.MeetingParticipantRepository;
import ecourse.scheduleMeeting.repositories.MeetingRepository;

import java.util.Optional;

public class ListMeetingController {

    private final MeetingRepository meetingsRepo = PersistenceContext.repositories().meetings();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final MeetingParticipantRepository meetingParticipantRepository = PersistenceContext.repositories().meetingParticipants();

    /**
     * Returns the current user
     *
     * @return the current user
     */
    private Optional<SystemUser> currentUser() {
        return authz.session().flatMap(s -> userSvc.userOfIdentity(s.authenticatedUser().username()));
    }

    /**
     * List participants of a meeting
     *
     * @param meeting meeting
     * @return list of participants
     */
    public Iterable<MeetingParticipant> listMeetingParticipants(Meeting meeting) {
        return meetingParticipantRepository.findMeetingParticipantsByMeeting(meeting);
    }

    /**
     * List all meetings of the current user.
     *
     * @return list of meetings.
     */
    public Iterable<Meeting> listMyMeetings() {
        return meetingsRepo.findMeetingsByUser(currentUser().get());
    }
}
