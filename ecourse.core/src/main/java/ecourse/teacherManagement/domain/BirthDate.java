package ecourse.teacherManagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Embeddable
public class BirthDate {

    @Temporal(TemporalType.DATE)
    private Calendar day;

    public BirthDate(Calendar day) {
        this.day = day;
    }

    public BirthDate() {

    }
}
