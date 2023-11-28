package ecourse.examManagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import ecourse.courseManagement.domain.Course;
import ecourse.examTaken.domain.ExamTaken;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Entity
public class Exam implements AggregateRoot<Long> {
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Section> sections = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Embedded
    private Header header;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ExamTaken> examTaken = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private Calendar date;

    public Exam(String title, Header header, Course course, List<Section> sections, Calendar date) {
        Preconditions.noneNull(sections, title);
        this.title = title;
        this.header = header;
        this.course = course;
        this.sections.addAll(sections);
        this.date = date;
        this.examTaken = Collections.emptyList();
    }

    protected Exam() {
    }

    public void newExamTaken(ExamTaken id) {
        this.examTaken.add(id);
    }

    public Calendar getDate() {
        return date;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return null;
    }

    public String title() {
        return title;
    }

    public Header header() {
        return header;
    }

    public Course course() {
        return course;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<ExamTaken> getExamTaken() {
        return examTaken;
    }
}
