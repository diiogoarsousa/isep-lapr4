package ecourse.courseClassManagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import ecourse.courseManagement.domain.Course;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity(name="COURSE_CLASS")
public class CourseClass implements AggregateRoot<String>, Serializable {


    /**
     * Internal ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Business ID.
     */
    @Column(unique = true) //title must be unique
    private CourseClassTitle title;

    /**
     * Date of the class.
     */
    @Column(nullable = false)
    private CourseClassDate startDate;

    /**
     * Date of the class.
     */
    @Column(nullable = false)
    private CourseClassDuration duration;

    /**
     * Course the class belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "COURSE")
    private Course course;


    protected CourseClass() {
        // for ORM only
    }

    /**
     *  Creates a new course class.
     * @param title
     * @param startDate
     * @param duration
     * @param course
     */
    public CourseClass(final CourseClassTitle title ,  final CourseClassDate startDate, final CourseClassDuration duration,final Course course) {
        this.title = title;
        this.startDate = startDate;
        this.duration = duration;
        this.course = course;

    }

    public boolean hasSameWeekDay(CourseClass otherClass) {
        return this.startDate.hasSameWeekDay(otherClass.startDate);
    }

    public LocalDate getStartDate() {
        return this.startDate.getDate();
    }

    public LocalDate getEndDate() {
        return this.startDate.getDate().plus(duration.getDuration(), java.time.temporal.ChronoUnit.MINUTES);
    }


    public Course getCourse() {
        return this.course;
    }



    public String identity() {
        return this.title.toString();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof CourseClass that)) {
            return false;
        }

        if (this == that) {
            return true;
        }

        return identity().equals(that.identity())
                && this.title.equals(that.title)
                && this.duration.equals(that.duration)
                && this.startDate.equals(that.startDate)
                && this.course.equals(that.course);
    }

}
