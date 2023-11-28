package ecourse.teacherManagement.application.eventhandlers;

import eapli.framework.functional.Functions;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.teacherManagement.domain.TeacherBuilder;
import ecourse.teacherManagement.domain.events.NewTeacherRegisteredFromSignupEvent;
import ecourse.teacherManagement.repository.TeacherRepository;
import ecourse.userManagement.domain.Teacher;

import java.util.Optional;

public class AddTeacherUserOnSignupAcceptedController {

    private final UserRepository repo = PersistenceContext.repositories().users();
    private final TeacherRepository teacherRepository = PersistenceContext
            .repositories().teachers();

    public Teacher addTeacher(final NewTeacherRegisteredFromSignupEvent event) {
        final Optional<SystemUser> newUser = findUser(event);

        return newUser.map(u -> {
            final TeacherBuilder studentBuilder = new TeacherBuilder();
            studentBuilder.withAcronym(event.acronym().toString())
                    .withSystemUser(u);
            return teacherRepository.save(studentBuilder.build());
        }).orElseThrow(IllegalStateException::new);
    }

    @SuppressWarnings("squid:S1488")
    private Optional<SystemUser> findUser(final NewTeacherRegisteredFromSignupEvent event) {
        // since we are using events, the actual user may not yet be
        // created, so lets give it a time and wait
        return Functions
                .retry(() -> repo.ofIdentity(event.username()), 1000, 3);
    }
}
