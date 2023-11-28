package ecourse.scheduleMeeting.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class MeetingParticipant implements AggregateRoot<Long> {
    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser participant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InviteState inviteState;

    @Column(nullable = false)
    private ParticipantResponse response;

    public MeetingParticipant(Meeting meeting, final SystemUser participant, final ParticipantResponse response) {
        Preconditions.noneNull(meeting, participant, response);
        this.response = response;
        this.meeting = meeting;
        this.participant = participant;
        this.inviteState = InviteState.INVITED;
    }

    protected MeetingParticipant() {
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return null;
    }

    public SystemUser participant(){
        return this.participant;
    }

    public InviteState state(){
        return this.inviteState;
    }

    public Meeting meeting(){
        return this.meeting;
    }

    public void changeStateTo(InviteState state){
        this.inviteState = state;
    }
}
