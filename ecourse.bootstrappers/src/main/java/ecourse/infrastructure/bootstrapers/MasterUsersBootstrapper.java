package ecourse.infrastructure.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import ecourse.userManagement.domain.BaseRoles;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Paulo Gandra Sousa
 */
public class MasterUsersBootstrapper extends UsersBootstrapperBase implements Action {

    @Override
    public boolean execute() {
        registerAdmin("admin", "Password1", "Jane", "Doe Admin",
                "jane.doe@email.local");
        return true;
    }

    /**
     *
     */
    private void registerAdmin(final String username, final String password, final String firstName,
                               final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.MANAGER);

        registerUser(username, password, firstName, lastName, email, roles);
    }
}
