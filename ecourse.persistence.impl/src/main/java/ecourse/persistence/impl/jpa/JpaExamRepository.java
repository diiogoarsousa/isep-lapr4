package ecourse.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.courseManagement.domain.Course;
import ecourse.examManagement.domain.Exam;
import ecourse.examManagement.repositories.ExamRepository;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JpaExamRepository extends JpaAutoTxRepository<Exam, Long, Long>
        implements ExamRepository {

    public JpaExamRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaExamRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    public Iterable<Exam> findAllExams() {
        final TypedQuery<Exam> query = entityManager().createQuery("SELECT e FROM Exam e ", Exam.class);
        return query.getResultList();
    }

    @Override
    public List<Exam> getExamsByCourse(Course course) {
        final Map<String, Object> params = new HashMap<>();
        params.put("course", course);
        return match("e.course = :course", params);
    }

}
