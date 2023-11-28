package ecourse.examTaken.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Answer {
    private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    public Answer() {

    }
}
