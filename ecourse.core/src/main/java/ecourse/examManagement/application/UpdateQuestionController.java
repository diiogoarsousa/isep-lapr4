package ecourse.examManagement.application;

import ecourse.examManagement.domain.Question;
import ecourse.examManagement.repositories.QuestionRepository;
import ecourse.infrastructure.persistence.PersistenceContext;

public class UpdateQuestionController {

    private final QuestionRepository questionRepository = PersistenceContext.repositories().questions();


    public Iterable<Question> listQuestions() {
        return questionRepository.findAll();
    }
}
