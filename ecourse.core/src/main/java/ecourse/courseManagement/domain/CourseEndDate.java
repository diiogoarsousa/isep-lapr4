package ecourse.courseManagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

public class CourseEndDate implements ValueObject {
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "ofday")
    private Calendar day;

    protected CourseEndDate() {
    }

    public CourseEndDate(Calendar day) {
        this.day = day;
    }

    public Calendar calendar() {
        return this.day;
    }
}
