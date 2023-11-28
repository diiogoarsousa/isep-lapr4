package ecourse.courseManagement.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import ecourse.courseManagement.dto.CourseDTO;
import ecourse.examManagement.domain.Exam;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "COURSE")
public class Course implements AggregateRoot<UUID>, DTOable<CourseDTO>, Representationable {

    @JsonProperty
    @Id
    private UUID identifier;

    /**
     * This is just to showcase the use of EmbeddedId, but this class should have a
     * private Long pk since using a string as primary key is not efficient.
     */
    @XmlElement
    @JsonProperty
    private String courseName;

    /**
     * cascade = CascadeType.NONE as the courseState is part of another aggregate
     */
    @XmlElement
    @JsonProperty
    @Enumerated(EnumType.STRING)
    private CourseStateEnum courseState;

    @XmlElement
    @JsonProperty
    private Calendar startDate;

    @XmlElement
    @JsonProperty
    private Calendar endDate;

    @XmlElement
    @JsonProperty
    private Integer minEnrollments;

    @XmlElement
    @JsonProperty
    private Integer maxEnrollments;

    @XmlElement
    @JsonProperty
    private String headTeacher;

    @XmlElement
    @JsonProperty
    private String description;

    @XmlElement
    @JsonProperty
    private boolean active;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @Lazy
    private List<Exam> exams;

    /**
     * Constructor.
     *
     * @param courseName
     * @param courseState
     * @param startDate
     * @param endDate
     * @param minEnrollments
     * @param maxEnrollments
     * @param headTeacher
     * @param description
     */
    public Course(final UUID identifier, final String courseName, final CourseStateEnum courseState, final Calendar startDate, final Calendar endDate, final Integer minEnrollments, final Integer maxEnrollments, final String headTeacher, final String description) {
        Preconditions.noneNull(courseName, courseState, startDate, endDate, minEnrollments, maxEnrollments, headTeacher);

        this.identifier = UUID.randomUUID();
        this.courseName = courseName;
        this.courseState = courseState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minEnrollments = minEnrollments;
        this.maxEnrollments = maxEnrollments;
        this.headTeacher = headTeacher;
        this.description = description;
        this.active = false; // by default a course is not active
    }

    /**
     * Empty constructor for ORM.
     */
    protected Course() {
    }

    /**
     * Equals method for the Course class.
     *
     * @param o object.
     * @return true or false whether is or not equal
     */
    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    /**
     * HashCode method for the Course class.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    /**
     * sameAs method for the Course class.
     *
     * @param other
     * @return true or false whether is or not same
     */
    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Course that)) {
            return false;
        }

        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && courseState.equals(that.courseState) && active == that.active;
    }

    /**
     * courseState method for the Course class.
     *
     * @return courseState
     */
    public CourseStateEnum courseState() {
        return this.courseState;
    }

    /**
     * identity method for the Course class.
     *
     * @return identifier
     */
    @Override
    public UUID identity() {
        return this.identifier;
    }

    /**
     * name method for the Course class.
     *
     * @return courseName
     */
    public String name() {
        return this.courseName;
    }

    /**
     * @return true or false whether is or not active
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Toggles the state of the course, activating it or deactivating it accordingly.
     *
     * @return whether the course is active or not
     */
    public boolean toogleState() {
        this.active = !this.active;
        return isActive();
    }


    /**
     * Showcase the {@link DTOable} interface. In this case it is the Course class
     * that dictates the DTO structure.
     *
     * @see #buildRepresentation
     */
    @Override
    public CourseDTO toDTO() {
        return new CourseDTO(courseName, courseState, startDate, endDate, minEnrollments, maxEnrollments, headTeacher, description);
    }

    /**
     * Showcase the {@link Representationable} interface allowing the caller to
     * decide on the actual representation to obtain, can be used to transform to
     * custom DTOs.
     *
     * @see #toDTO
     */
    @Override
    public <R> R buildRepresentation(final RepresentationBuilder<R> builder) {
        builder.startObject("Course").withProperty("name", courseName)
                .withProperty("startDate", startDate).withProperty("endDate", endDate)
                .withProperty("minEnrolments", minEnrollments).withProperty("maxEnrolments", maxEnrollments)
                .withProperty("headTeacher", headTeacher).withProperty("description", description).endObject();
        return builder.build();
    }

    /**
     * Update the course's properties. We cannot change the course's identity.
     *
     * @param state
     * @param active
     */
    public void update(final CourseStateEnum state, final boolean active) {
        Preconditions.noneNull(state);

        this.courseState = state;
        this.active = active;
    }

    /**
     * toString method for the Course class.
     *
     * @return string.
     */
    @Override
    public String toString() {
        return "Course{" +
                "identifier=" + identifier +
                ", courseName='" + courseName + '\'' +
                ", courseState=" + courseState +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", minEnrollments=" + minEnrollments +
                ", maxEnrollments=" + maxEnrollments +
                ", headTeacher='" + headTeacher + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", exams=" + exams +
                '}';
    }

    /**
     * @return the state of the course
     */
    public Object getState() {
        return this.courseState;
    }

    public void changeState(CourseStateEnum newState) {
        this.courseState = newState;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    /**
     * @return the maxEnrolments
     */
    public int getMaxEnrollments() {
        return this.maxEnrollments;
    }

}