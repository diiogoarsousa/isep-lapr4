package ecourse.courseClassManagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseClassDuration implements ValueObject {

    @Column
    int duration;

    public CourseClassDuration(int duration) {
        this.duration = duration;
    }

    protected CourseClassDuration() {

    }

    public int getDuration() {
        return duration;
    }
}
