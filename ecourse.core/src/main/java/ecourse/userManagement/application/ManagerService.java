package ecourse.userManagement.application;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.userManagement.domain.ManagerBuilder;
import ecourse.userManagement.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManagerService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepo) {
        managerRepository = managerRepo;
    }


    @Transactional
    public void registerManager(final SystemUser systemUser) {
        final var managerBuilder = new ManagerBuilder();
        managerBuilder.with(systemUser);
        final var newManager = managerBuilder.build();
        managerRepository.save(newManager);
    }
}
