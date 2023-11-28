package ecourse.persistence.impl.jpa;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.teacherManagement.domain.Acronym;
import ecourse.teacherManagement.repository.TeacherRepository;
import ecourse.userManagement.domain.Teacher;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaTeacherRepository extends JpaAutoTxRepository<Teacher, Acronym, Acronym>
        implements TeacherRepository {

    public JpaTeacherRepository(String persistenceUnitName, String identityFieldName) {
        super(persistenceUnitName, identityFieldName);
    }

    public JpaTeacherRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "acronym");
    }

    @Override
    public Optional<Teacher> findByAcronym(Acronym acronym) {
        final Map<String, Object> params = new HashMap<>();
        params.put("acronym", acronym);
        return matchOne("e.acronym=:acronym", params);
    }

    @Override
    public Iterable<Teacher> findAllActive() {
        return match("e.user.active = true");
    }

    @Override
    public Optional<Teacher> findTeacherBySystemUser(SystemUser user) {
        final Map<String, Object> params = new HashMap<>();
        params.put("user", user.username());
        return matchOne("e.username =:user", params);
    }

}
