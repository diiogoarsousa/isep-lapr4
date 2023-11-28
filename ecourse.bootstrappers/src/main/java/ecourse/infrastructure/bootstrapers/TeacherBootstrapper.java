package ecourse.infrastructure.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import ecourse.userManagement.domain.BaseRoles;
import org.apache.commons.lang.RandomStringUtils;

import java.util.HashSet;
import java.util.Set;

public class TeacherBootstrapper extends UsersBootstrapperBase implements Action {
    private static final String PASSWORD1 = "Password1";

    @Override
    public boolean execute() {
        registerTeacher("Ana", "Alves", "ana.alves@isep.ipp.pt");
        registerTeacher("Bruno", "Brito", "bruno.brito@isep.ipp.pt");
        registerTeacher("Carlos", "Costa", "carlos.costa@isep.ipp.pt");
        registerTeacher("David", "Dias", "david.dias@isep.ipp.pt");
        return true;
    }

    private void registerTeacher(final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.TEACHER);

        String usernamePrefix = firstName.substring(0, 3);
        String newUsername = (usernamePrefix + RandomStringUtils.randomAlphanumeric(4)).toLowerCase();

        registerUser(newUsername, PASSWORD1, firstName, lastName, email, roles);
    }
}
