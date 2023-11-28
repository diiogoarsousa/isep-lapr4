package ecourse.examManagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section implements AggregateRoot<Long> {
    @ManyToMany
    @JoinTable(
            name = "section_question",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long section_id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType questionType;

    @Column()
    private String description;


    public Section(List<Question> question, String description, QuestionType type) {
        Preconditions.noneNull(question, description, type);
        this.description = description;
        this.questionType = type;
        this.questions.addAll(question);
    }

    protected Section() {
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return null;
    }

    public String getTitle() {
        return description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

}