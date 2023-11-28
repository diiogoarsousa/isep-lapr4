package ecourse.examManagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import ecourse.examManagement.domain.Question;
import ecourse.examManagement.domain.QuestionType;

public interface QuestionRepository extends DomainRepository<Long, Question> {

    Iterable<Question> findQuestionsByType(QuestionType type);

}
