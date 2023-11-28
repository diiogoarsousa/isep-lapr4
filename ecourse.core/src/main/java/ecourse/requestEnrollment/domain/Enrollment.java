package ecourse.requestEnrollment.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "ENROLLMENT")
public class Enrollment implements Comparable<EnrollmentId>, AggregateRoot<EnrollmentId> {
    @EmbeddedId
    private EnrollmentId id;

    @Enumerated(EnumType.STRING)
    private ApprovalStatusEnum state;

    private Calendar enrollmentDate;

    public Enrollment(EnrollmentId enrollmentId, Calendar enrollmentDate, ApprovalStatusEnum state) {
        this.id = enrollmentId;
        this.enrollmentDate = enrollmentDate;
        this.state = state;
    }

    public Enrollment() {
    }

    @Override
    public boolean sameAs(Object other) {
        return other instanceof Enrollment && this.equals(other);
    }

    @Override
    public EnrollmentId identity() {
        return this.id;
    }

    public ApprovalStatusEnum getState() {
        return state;
    }
}
