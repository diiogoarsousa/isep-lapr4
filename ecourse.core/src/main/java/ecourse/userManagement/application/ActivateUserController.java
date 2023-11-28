package ecourse.userManagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.userManagement.domain.BaseRoles;

@UseCaseController
public class ActivateUserController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();

    public Iterable<SystemUser> desactiveUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER, BaseRoles.MANAGER);

        return userSvc.deactivatedUsers();
    }

    public SystemUser deactivateUser(final SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER, BaseRoles.MANAGER);

        return userSvc.deactivateUser(user);
    }
}
