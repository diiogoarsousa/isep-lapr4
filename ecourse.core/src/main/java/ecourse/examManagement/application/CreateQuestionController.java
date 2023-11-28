package ecourse.examManagement.application;

import ecourse.examManagement.domain.Question;
import ecourse.examManagement.repositories.QuestionRepository;
import ecourse.infrastructure.persistence.PersistenceContext;

public class CreateQuestionController {

    private final QuestionRepository questionRepository = PersistenceContext.repositories().questions();

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

}
