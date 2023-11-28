package ecourse.userManagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ManagerBuilder implements DomainFactory<Manager> {
    private SystemUser systemUser;

    public ManagerBuilder withSystemUser(final SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public ManagerBuilder with(final SystemUser systemUser) {
        withSystemUser(systemUser);
        return this;
    }

    @Override
    public Manager build() {
        return new Manager(this.systemUser);
    }
}
