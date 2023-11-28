package ecourse.scheduleMeeting.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Embeddable
public class MeetingDate implements ValueObject {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar day;

    protected MeetingDate() {
    }

    public MeetingDate(Calendar day) {
        this.day = day;
    }

    public Calendar calendar() {
        return this.day;
    }

}
