package ecourse.persistence.impl.inmemory;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import ecourse.teacherManagement.domain.Acronym;
import ecourse.teacherManagement.repository.TeacherRepository;
import ecourse.userManagement.domain.Teacher;

import java.util.Optional;

public class InMemoryTeacherRepository
        extends InMemoryDomainRepository<Teacher, Acronym>
        implements TeacherRepository {


    @Override
    public Optional<Teacher> findByAcronym(Acronym acronym) {
        return matchOne(e -> e.identity().equals(acronym));
    }

    @Override
    public Iterable<Teacher> findAllActive() {
        return match(e -> e.user().isActive());
    }

    @Override
    public Optional<Teacher> findTeacherBySystemUser(SystemUser user) {
        return matchOne(e->e.user().equals(user));
    }
}
