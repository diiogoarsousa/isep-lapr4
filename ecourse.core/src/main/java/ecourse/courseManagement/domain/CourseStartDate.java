package ecourse.courseManagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

public class CourseStartDate implements ValueObject {
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "ofday")
    private Calendar day;

    protected CourseStartDate() {
    }

    public CourseStartDate(Calendar day) {
        this.day = day;
    }

    public Calendar calendar() {
        return this.day;
    }
}
