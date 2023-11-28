package ecourse.teacherManagement.application;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.teacherManagement.domain.TeacherBuilder;
import ecourse.teacherManagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Component
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepo) {
        teacherRepository = teacherRepo;
    }


    @Transactional
    public void registerTeacher(final SystemUser systemUser, final String acronym, final int taxNumber, final Calendar birthDate) {
        final var teacherBuilder = new TeacherBuilder();
        teacherBuilder.with(systemUser, acronym, taxNumber, birthDate);
        final var newTeacher = teacherBuilder.build();
        teacherRepository.save(newTeacher);
    }
}
