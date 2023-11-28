package ecourse.userManagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.studentManagement.application.StudentService;
import ecourse.teacherManagement.application.TeacherService;
import ecourse.userManagement.domain.BaseRoles;

import java.util.Calendar;
import java.util.Random;
import java.util.Set;

@UseCaseController
public class AddUserBootstrap {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final TeacherService teacherService = new TeacherService(PersistenceContext.repositories().teachers());
    private final ManagerService managerService = new ManagerService(PersistenceContext.repositories().managers());
    private final StudentService studentService = new StudentService(PersistenceContext.repositories().students());

    /**
     * Get existing RoleTypes available to the user.
     *
     * @return an array of RoleTypes
     */
    public Role[] getRoleTypes() {
        return BaseRoles.nonUserValues();
    }

    /**
     * Add a new user to the system.
     *
     * @param username  User's username
     * @param password  User's password
     * @param firstName User's first name
     * @param lastName  User's last name
     * @param email     User's email
     * @param roles     User's roles
     * @param createdOn User's creation date
     * @return the new user
     */
    public SystemUser addUser(final String username, final String password, final String firstName,
                              final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        SystemUser systemUser = userSvc.registerNewUser(username, password, firstName, lastName, email, roles, createdOn);

        switch (roles.stream().findFirst().get().toString()) {
            case "MANAGER" ->
                //SAVE TO MANAGER REPOSITORY
                    managerService.registerManager(systemUser);
            case "TEACHER" -> {
                //SAVE TO TEACHER REPOSITORY
                teacherService.registerTeacher(systemUser, username.substring(0, 3).toUpperCase(), generateTaxNumber(), Calendar.getInstance());
            }
            case "STUDENT" -> {
                //SAVE TO STUDENT REPOSITORY
                studentService.registerStudent(systemUser, username, generateTaxNumber(), Calendar.getInstance());
            }
            default -> {
            }
        }

        return systemUser;
    }

    public SystemUser addUser(final String username, final String password, final String firstName,
                              final String lastName, final String email, final Set<Role> roles) {
        return addUser(username, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    }

    private int generateTaxNumber() {
        Random random = new Random();
        return random.nextInt(90000000) + 10000000;
    }
}
