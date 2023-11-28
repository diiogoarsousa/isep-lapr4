package ecourse.scheduleMeeting.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.scheduleMeeting.domain.*;
import ecourse.scheduleMeeting.repositories.MeetingParticipantRepository;
import ecourse.scheduleMeeting.repositories.MeetingRepository;

import java.util.List;
import java.util.Optional;

@UseCaseController
public class MyMeetingController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final MeetingParticipantRepository meetingParticipantRepository = PersistenceContext.repositories().meetingParticipants();
    private final MeetingRepository meetingsRepo = PersistenceContext.repositories().meetings();

    /**
     * Returns all active users
     *
     * @return all active users
     */
    public Iterable<SystemUser> activeUsers() {
        return userSvc.activeUsers();
    }

    /**
     * Returns the current user
     *
     * @return the current user, if any
     */
    private Optional<SystemUser> currentUser() {
        return authz.session().flatMap(s -> userSvc.userOfIdentity(s.authenticatedUser().username()));
    }

    /**
     * Returns the meeting of the current user in the given date
     *
     * @param date     the date of the meeting
     * @param duration the duration of the meeting
     * @return the meeting of the current user in the given date
     */
    public Optional<Meeting> myMeetingFor(MeetingDate date, MeetingDuration duration) {
        return meetingsRepo.findByDayAndUser(date, currentUser().get(), duration);
    }

    /**
     * Returns the meeting of the current user in the given date for the current user.
     *
     * @param date     the date of the meeting
     * @param user     the user of the meeting
     * @param duration the duration of the meeting
     * @return the meeting of the current user in the given date for the given user.
     */
    public Optional<Meeting> inviteParticipantFor(MeetingDate date, SystemUser user, MeetingDuration duration) {
        return meetingsRepo.findByDayAndUser(date, user, duration);
    }

    /**
     * Schedules a meeting in the given date for a list of participants with the given duration.
     *
     * @param duration     the duration of the meeting
     * @param date         the date of the meeting
     * @param participants the participants of the meeting
     * @return the meeting scheduled
     */
    public Meeting scheduleMeeting(final MeetingDuration duration, final MeetingDate date, final List<SystemUser> participants) {
        SystemUser organizer = authz.session().flatMap(s -> userSvc.userOfIdentity(s.authenticatedUser().username())).get();
        return meetingsRepo.save(new Meeting(participants, duration, organizer, date));
    }

    /**
     * Lists all meetings of the current user.
     *
     * @return all meetings of the current user.
     */
    public Iterable<Meeting> listMyMeetings() {
        return meetingsRepo.findMeetingsByUser(currentUser().get());
    }

    /**
     * List all meeting participants.
     *
     * @param meeting the meeting to list the participants
     * @return all meeting participants.
     */
    public Iterable<MeetingParticipant> listMeetingParticipants(Meeting meeting) {
        return meetingParticipantRepository.findMeetingParticipantsByMeeting(meeting);
    }
}
