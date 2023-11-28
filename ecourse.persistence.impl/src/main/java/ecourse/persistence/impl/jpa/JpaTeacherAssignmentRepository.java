package ecourse.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.courseManagement.domain.Course;
import ecourse.teacherAssignmentManagement.domain.TeacherAssignment;
import ecourse.teacherAssignmentManagement.repository.TeacherAssignmentRepository;
import ecourse.userManagement.domain.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JpaTeacherAssignmentRepository extends JpaAutoTxRepository<TeacherAssignment, UUID, UUID>
        implements TeacherAssignmentRepository {
    public JpaTeacherAssignmentRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaTeacherAssignmentRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public List<TeacherAssignment> findAllTeachersAssignedToCourse(Course course) {
        final Map<String, Object> params = new HashMap<>();
        params.put("course", course);
        return match("e.course = :course", params);
    }

    @Override
    public List<TeacherAssignment> findAllCoursesAssignedToTeacher(Teacher teacher) {
        final Map<String, Object> params = new HashMap<>();
        params.put("t", teacher.acronym());

        return match("e.teacher = :t ", params);
    }
}
