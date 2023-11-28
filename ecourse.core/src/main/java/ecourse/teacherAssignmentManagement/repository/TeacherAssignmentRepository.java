package ecourse.teacherAssignmentManagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import ecourse.courseManagement.domain.Course;
import ecourse.teacherAssignmentManagement.domain.TeacherAssignment;
import ecourse.userManagement.domain.Teacher;

import java.util.List;
import java.util.UUID;

public interface TeacherAssignmentRepository extends DomainRepository<UUID, TeacherAssignment> {

    List<TeacherAssignment> findAllTeachersAssignedToCourse(Course course);

    Iterable<TeacherAssignment> findAllCoursesAssignedToTeacher(Teacher teacher);
}
