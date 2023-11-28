package ecourse.scheduleMeeting.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Meeting implements AggregateRoot<Long> {
    @OneToMany(cascade = CascadeType.ALL)
    private final List<MeetingParticipant> participants = new ArrayList<>();
    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private SystemUser organizer;
    @Column(nullable = false)
    private MeetingDate date;
    @Column(nullable = false)
    private MeetingDuration duration;

    protected Meeting() {
        // for ORM
    }

    public Meeting(final List<SystemUser> participants, final MeetingDuration duration, final SystemUser organizer, final MeetingDate date) {
        Preconditions.noneNull(duration, organizer, date, participants);
        this.duration = duration;
        this.date = date;
        this.organizer = organizer;
        for (SystemUser user : participants) {
            MeetingParticipant participantsList = new MeetingParticipant(this, user, new ParticipantResponse(organizer));
            this.participants.add(participantsList);
        }
    }

    @Override
    public Long identity() {
        return id;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    public Calendar day() {
        return this.date.calendar();
    }

    public SystemUser organizer() {
        return this.organizer;
    }

    public List<MeetingParticipant> participants() {
        return this.participants;
    }

    public MeetingDuration duration() {
        return this.duration;
    }

    public MeetingDate date() {
        return this.date;
    }

    public Long version() {
        return version;
    }
}
