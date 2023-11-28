package ecourse.teacherAssignmentManagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import ecourse.courseManagement.domain.Course;
import ecourse.userManagement.domain.Teacher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table
public class TeacherAssignment implements AggregateRoot<UUID>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable=false)
    private UUID identifier;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne(optional = false)
    @JoinColumn(name="ACRONYM", nullable=false)
    private Teacher teacher;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne(optional = false)
    @JoinColumn(name="COURSE", nullable=false)
    private Course course;

    @Column(name="HEADTEACHER", nullable=false)
    private boolean headTeacher;


    protected TeacherAssignment() {
        // for ORM only.
    }

    public TeacherAssignment(final Teacher teacher, final Course course, final boolean headTeacher) {
        this.teacher = teacher;
        this.course = course;
        this.headTeacher = headTeacher;
    }

    public boolean isHeadTeacher() {
        return this.headTeacher;
    }

    @Override
    public UUID identity() {
        return this.identifier;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public Course getCourse() {
        return this.course;
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }
}
