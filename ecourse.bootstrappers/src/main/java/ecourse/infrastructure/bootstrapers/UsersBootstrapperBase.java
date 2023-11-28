package ecourse.infrastructure.bootstrapers;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import ecourse.userManagement.application.AddUserBootstrap;
import ecourse.userManagement.application.ListUsersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class UsersBootstrapperBase {
    private static final Logger logger = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    final AddUserBootstrap addUserBootstrap = new AddUserBootstrap();
    final ListUsersController listUserController = new ListUsersController();

    public UsersBootstrapperBase() {
        super();
    }

    /**
     * helper method to register a user
     *
     * @param username  username
     * @param password  password
     * @param firstName first name
     * @param lastName  last name
     * @param email     email
     * @param roles     roles
     * @return the registered user
     */
    protected SystemUser registerUser(final String username, final String password, final String firstName,
                                      final String lastName, final String email, final Set<Role> roles) {
        SystemUser u = null;
        try {
            u = addUserBootstrap.addUser(username, password, firstName, lastName, email, roles);
            logger.debug("Created user with username: {}", u.username());
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            u = listUserController.find(Username.valueOf(username)).orElseThrow(() -> e);
        }
        return u;
    }
}
