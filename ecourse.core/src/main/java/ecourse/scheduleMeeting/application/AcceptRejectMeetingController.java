package ecourse.scheduleMeeting.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.scheduleMeeting.domain.InviteState;
import ecourse.scheduleMeeting.domain.MeetingParticipant;
import ecourse.scheduleMeeting.repositories.MeetingParticipantRepository;
import ecourse.scheduleMeeting.repositories.MeetingRepository;

public class AcceptRejectMeetingController {

    private final MeetingRepository meetingsRepo = PersistenceContext.repositories().meetings();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final MeetingParticipantRepository meetingParticipantRepository = PersistenceContext.repositories().meetingParticipants();


    public Iterable<MeetingParticipant> listMyInvitations() {
        SystemUser user = authz.session().flatMap(s -> userSvc.userOfIdentity(s.authenticatedUser().username())).get();
        return meetingParticipantRepository.findMyInvitations(user);
    }
    public MeetingParticipant acceptMeeting(MeetingParticipant meeting) {
        meeting.changeStateTo(InviteState.ACCEPTED);
        return meetingParticipantRepository.save(meeting);
    }

    public MeetingParticipant rejectMeeting(MeetingParticipant meeting) {
        meeting.changeStateTo(InviteState.REFUSED);
        return meetingParticipantRepository.save(meeting);
    }
}
