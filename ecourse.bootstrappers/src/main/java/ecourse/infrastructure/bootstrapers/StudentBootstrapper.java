package ecourse.infrastructure.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import ecourse.infrastructure.bootstrapers.UsersBootstrapperBase;
import ecourse.userManagement.domain.BaseRoles;

import java.util.HashSet;
import java.util.Set;

public class StudentBootstrapper extends UsersBootstrapperBase implements Action {

    private static final String PASSWORD1 = "Password1";

    @Override
    public boolean execute() {
        registerStudent("1121121", PASSWORD1, "Antonio", "Almeida", "1121121@isep.ipp.pt");
        registerStudent("2382883", PASSWORD1, "Bruno", "Brito", "2382883@isep.ipp.pt");
        registerStudent("1234567", PASSWORD1, "Carlos", "Costa", "1234567@isep.ipp.pt");
        registerStudent("7654321", PASSWORD1, "David", "Dias", "7654321@isep.ipp.pt");
        registerStudent("2345678", PASSWORD1, "Eduardo", "Esteves", "2345678@isep.ipp.pt");
        registerStudent("8765432", PASSWORD1, "Francisco", "Fernandes", "8765432@isep.ipp.pt");

        return true;
    }

    private void registerStudent(final String username, final String password,
                                 final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.STUDENT);

        registerUser(username, password, firstName, lastName, email, roles);
    }
}
