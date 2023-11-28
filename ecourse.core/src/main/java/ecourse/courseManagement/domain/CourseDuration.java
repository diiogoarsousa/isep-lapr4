package ecourse.courseManagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import java.util.Calendar;

public class CourseDuration implements ValueObject {

    @Column(name = "days")
    private int duration;

    public CourseDuration(int duration) {
        this.duration = duration;
    }

    protected CourseDuration() {
    }

    public int getDuration(CourseEndDate endDate, CourseStartDate startDate) {
        duration = endDate.calendar().get(Calendar.DAY_OF_YEAR) - startDate.calendar().get(Calendar.DAY_OF_YEAR);
        return duration;
    }
}
