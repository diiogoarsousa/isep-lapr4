package ecourse.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.scheduleMeeting.domain.Meeting;
import ecourse.scheduleMeeting.domain.MeetingParticipant;
import ecourse.scheduleMeeting.repositories.MeetingParticipantRepository;

import java.util.HashMap;
import java.util.Map;

public class JpaMeetingParticipantsRepository extends JpaAutoTxRepository<MeetingParticipant, Long, Long>
        implements MeetingParticipantRepository {

        public JpaMeetingParticipantsRepository(String persistenceUnitName, String identityFieldName) {
                super(persistenceUnitName, identityFieldName);
        }

        public JpaMeetingParticipantsRepository(String persistenceUnitName, Map properties, String identityFieldName) {
                super(persistenceUnitName, properties, identityFieldName);
        }

        public JpaMeetingParticipantsRepository(TransactionalContext tx, String identityFieldName) {
                super(tx, identityFieldName);
        }

        public JpaMeetingParticipantsRepository(final String puname) {
                super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
        }

        @Override
        public Iterable<MeetingParticipant> findMeetingParticipantsByMeeting(Meeting myMeeting) {
        final Map<String, Object> params = new HashMap<>();
        params.put("myMeeting", myMeeting);
        return match("e.meeting = :myMeeting", params);
        }

        @Override
        public Iterable<MeetingParticipant> findMyInvitations(SystemUser user) {
                final Map<String, Object> params = new HashMap<>();
                params.put("user", user);
                return match("e.participant = :user", params);
        }

}
