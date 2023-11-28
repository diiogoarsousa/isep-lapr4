package ecourse.persistence.impl.jpa;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.examManagement.domain.Exam;
import ecourse.examTaken.domain.ExamTaken;
import ecourse.examTaken.repositories.ExamTakenRepository;
import ecourse.userManagement.domain.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaExamTakenRepository extends JpaAutoTxRepository<ExamTaken, Long, Long>
        implements ExamTakenRepository {

    public JpaExamTakenRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public Iterable<ExamTaken> findGradesByUser(SystemUser systemUser) {
        final Map<String, Object> params = new HashMap<>();
        params.put("user", systemUser);
        return match("e.student.systemUser = :user", params);
    }

    @Override
    public Optional<ExamTaken> findByExamAndUser(Exam exam, Student studentByUser) {
        final Map<String, Object> params = new HashMap<>();
        params.put("exam", exam);
        params.put("student", studentByUser);
        return matchOne("e.exam = :exam AND e.student = :student", params);
    }
}
