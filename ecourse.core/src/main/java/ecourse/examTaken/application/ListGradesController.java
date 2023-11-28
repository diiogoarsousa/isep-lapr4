package ecourse.examTaken.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.examTaken.domain.ExamTaken;
import ecourse.examTaken.repositories.ExamTakenRepository;
import ecourse.infrastructure.persistence.PersistenceContext;

import java.util.Optional;

public class ListGradesController {

    private final ExamTakenRepository exameRepo = PersistenceContext.repositories().examTaken();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * @return the current user
     */
    private Optional<SystemUser> currentUser() {
        return authz.session().flatMap(s -> userSvc.userOfIdentity(s.authenticatedUser().username()));
    }

    /**
     * @return the list of grades of the current user
     */
    public Iterable<ExamTaken> listMyGrades() {
        if(currentUser().isEmpty())
            throw new IllegalStateException("User not found.");
        return exameRepo.findGradesByUser(currentUser().get());
    }
}
