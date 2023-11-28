package ecourse.scheduleMeeting.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MeetingDuration implements ValueObject {

    @Column
    int duration;

    public MeetingDuration(int duration) {
        this.duration = duration;
    }

    protected MeetingDuration() {

    }

    public int duration() {
        return duration;
    }

}
