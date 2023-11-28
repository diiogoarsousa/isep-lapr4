package ecourse.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.examManagement.domain.Question;
import ecourse.examManagement.domain.QuestionType;
import ecourse.examManagement.repositories.QuestionRepository;

import java.util.HashMap;
import java.util.Map;

public class JpaQuestionRepository extends JpaAutoTxRepository<Question, Long, Long>
        implements QuestionRepository {

    public JpaQuestionRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaQuestionRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public Iterable<Question> findQuestionsByType(QuestionType type) {
        final Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        return match("e.questionType = :type", params);
    }
}
