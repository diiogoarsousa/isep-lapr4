package ecourse.examManagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long question_id;

    @ManyToMany(mappedBy = "questions")
    private List<Section> sections = new ArrayList<>();

    @Column()
    private String question;

    @Column()
    private String correctAnswer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType questionType;

    // @Column()
    //private int questionValue;


    public Question(QuestionType questionType, String question, String correctAnswer) {
        Preconditions.noneNull(questionType, question, correctAnswer);
        this.correctAnswer = correctAnswer;
        this.question = question;
        this.questionType = questionType;
    }

    protected Question() {
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return null;
    }

    public String question() {
        return this.question;
    }

    public String correctAnswer() {
        return this.correctAnswer;
    }
}