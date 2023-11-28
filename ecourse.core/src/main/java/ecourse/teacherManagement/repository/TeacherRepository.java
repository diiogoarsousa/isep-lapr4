package ecourse.teacherManagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.teacherManagement.domain.Acronym;
import ecourse.userManagement.domain.Teacher;

import java.util.Optional;


public interface TeacherRepository
        extends DomainRepository<Acronym, Teacher> {

    /**
     * returns the Teacher user with the given Acronym
     *
     * @param acronym the acronym to search for
     * @return the teacher with the given acronym
     */
    Optional<Teacher> findByAcronym(final Acronym acronym);

    Iterable<Teacher> findAllActive();

    Optional<Teacher> findTeacherBySystemUser(SystemUser user);
}
