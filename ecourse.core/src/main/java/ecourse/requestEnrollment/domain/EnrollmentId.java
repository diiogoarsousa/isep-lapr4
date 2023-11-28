package ecourse.requestEnrollment.domain;

import ecourse.courseManagement.domain.Course;
import ecourse.userManagement.domain.Student;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrollmentId implements Serializable, Comparable<EnrollmentId> {
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public EnrollmentId() {
    }

    public EnrollmentId(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    @Override
    public int compareTo(EnrollmentId o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(course, that.course) && Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, student);
    }

    public Course getCourse() {
        return course;
    }
}
