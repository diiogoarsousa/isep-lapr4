package ecourse.examTaken.domain;

import eapli.framework.domain.model.AggregateRoot;
import ecourse.examManagement.domain.Exam;
import ecourse.userManagement.domain.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EXAMTAKEN")
public class ExamTaken implements AggregateRoot<Long> {
    @ElementCollection
    private final List<Answer> answers = new ArrayList<>();

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Embedded
    private Feedback feedback;

    @Column
    private int grade;

    protected ExamTaken() {
        // for ORM only
    }

    public ExamTaken(Exam exam, Student student, int grade, Feedback feedback) {
        this.grade = grade;
        this.feedback = feedback;
        this.exam = exam;
        this.student = student;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return null;
    }

    @Override
    public String toString() {
        return "ExamTaken{" +
                "answers=" + answers +
                ", id=" + id +
                ", exam=" + exam +
                ", student=" + student +
                ", feedback=" + feedback +
                ", grade=" + grade +
                '}';
    }

    public Exam exam() {
        return exam;
    }

    public int grade() {
        return grade;
    }

    /**
     * Get the student that took the exam.
     *
     * @return the student that took the exam.
     */
    public Student getStudent() {
        return student;
    }
}
