package ecourse.examTaken.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.examManagement.domain.Exam;
import ecourse.examTaken.domain.ExamTaken;
import ecourse.examTaken.domain.Feedback;
import ecourse.examTaken.repositories.ExamTakenRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.studentManagement.application.StudentService;
import ecourse.userManagement.domain.Student;

import java.util.Optional;

public class TakeExamController {
    private final ExamTakenRepository exameRepo = PersistenceContext.repositories().examTaken();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final StudentService studentService = new StudentService(PersistenceContext.repositories().students());

    private Optional<SystemUser> currentUser() {
        return authz.session().flatMap(s -> userSvc.userOfIdentity(s.authenticatedUser().username()));
    }

    private Student getStudentByUser(SystemUser user) {
        return studentService.findBySystemUser(user);
    }

    public Optional<ExamTaken> takeExam(Exam exam) {
        return exameRepo.findByExamAndUser(exam, getStudentByUser(currentUser().get()));
    }

    public ExamTaken saveExamTaken(Exam exam, int grade) {
        return exameRepo.save(new ExamTaken(exam, getStudentByUser(currentUser().get()), grade, new Feedback()));
    }
}
