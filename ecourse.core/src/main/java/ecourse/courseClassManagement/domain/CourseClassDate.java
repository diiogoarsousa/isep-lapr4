package ecourse.courseClassManagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;


public class CourseClassDate implements ValueObject {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "DATE")
    private LocalDate date;

    protected CourseClassDate() {
        // for ORM only
    }

    public  CourseClassDate(LocalDate date) {
          this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public boolean hasSameWeekDay(CourseClassDate otherDate) {
        return this.date.getDayOfWeek() == otherDate.getDate().getDayOfWeek();
    }

}
