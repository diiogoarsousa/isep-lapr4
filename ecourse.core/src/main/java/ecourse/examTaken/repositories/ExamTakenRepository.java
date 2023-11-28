package ecourse.examTaken.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.examManagement.domain.Exam;
import ecourse.examTaken.domain.ExamTaken;
import ecourse.userManagement.domain.Student;

import java.util.Optional;

public interface ExamTakenRepository extends DomainRepository<Long, ExamTaken> {
    Iterable<ExamTaken> findGradesByUser(SystemUser systemUser);

    Optional<ExamTaken> findByExamAndUser(Exam exam, Student studentByUser);
}
