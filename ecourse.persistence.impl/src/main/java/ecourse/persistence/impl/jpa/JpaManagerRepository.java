package ecourse.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.userManagement.domain.Manager;
import ecourse.userManagement.repository.ManagerRepository;

import java.util.Map;

public class JpaManagerRepository extends JpaAutoTxRepository<Manager, Long, Long>
        implements ManagerRepository {

    public JpaManagerRepository(String persistenceUnitName, String identityFieldName) {
        super(persistenceUnitName, identityFieldName);
    }

    public JpaManagerRepository(String persistenceUnitName, Map properties, String identityFieldName) {
        super(persistenceUnitName, properties, identityFieldName);
    }

    public JpaManagerRepository(TransactionalContext tx, String identityFieldName) {
        super(tx, identityFieldName);
    }

    public JpaManagerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }
}
