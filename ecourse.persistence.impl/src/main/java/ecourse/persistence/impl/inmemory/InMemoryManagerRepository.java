package ecourse.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import ecourse.userManagement.domain.Manager;
import ecourse.userManagement.repository.ManagerRepository;

public class InMemoryManagerRepository extends InMemoryDomainRepository<Manager, Long>
        implements ManagerRepository {
}
