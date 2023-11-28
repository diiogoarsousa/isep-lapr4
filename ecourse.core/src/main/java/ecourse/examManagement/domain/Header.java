package ecourse.examManagement.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Header {
    @Enumerated(EnumType.STRING)
    @Column(name = "header_feedback")
    private FeedbackTypeEnum header_feedback;

    @Enumerated(EnumType.STRING)
    @Column(name = "header_grade")
    private GradeTypeEnum header_grade;

    @Column(name = "header_description")
    private String header_description;

    public Header(FeedbackTypeEnum header_feedback, String header_description, GradeTypeEnum header_grade) {
        this.header_description = header_description;
        this.header_grade = header_grade;
        this.header_feedback = header_feedback;
    }

    protected Header() {
    }

    public String description() {
        return header_description;
    }

    public FeedbackTypeEnum feedback() {
        return header_feedback;
    }

    public GradeTypeEnum grade() {
        return header_grade;
    }

    @Override
    public String toString() {
        return "Header{" +
                "feedback=" + header_feedback +
                ", grade=" + header_grade +
                ", header description='" + header_description + '\'' +
                '}';
    }
}