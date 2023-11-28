package ecourse.scheduleMeeting.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.scheduleMeeting.domain.Meeting;
import ecourse.scheduleMeeting.domain.MeetingParticipant;

public interface MeetingParticipantRepository extends DomainRepository<Long, MeetingParticipant> {
    Iterable<MeetingParticipant> findMeetingParticipantsByMeeting(Meeting myMeeting);

    Iterable<MeetingParticipant> findMyInvitations(SystemUser user);
}
