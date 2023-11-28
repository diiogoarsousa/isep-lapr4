package ecourse.teacherAssignmentManagement.application;

import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.teacherAssignmentManagement.domain.TeacherAssignment;
import ecourse.teacherAssignmentManagement.exceptions.NoTeachersRegisteredException;
import ecourse.teacherAssignmentManagement.repository.TeacherAssignmentRepository;
import ecourse.teacherManagement.repository.TeacherRepository;
import ecourse.userManagement.domain.Teacher;

import java.util.ArrayList;
import java.util.List;

public class SetCourseTeachersController {

    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();

    private final TeacherRepository teachersRepository = PersistenceContext.repositories().teachers();

    private final TeacherAssignmentRepository teacherAssignmentRepository = PersistenceContext.repositories().teacherAssignments();

    private Course course;
    private Teacher headTeacher;
    private final List<Teacher> teachers = new ArrayList<>();

    public Iterable<Course> getCourses() {
        return this.courseRepository.findAllCoursesNotClosed();
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Iterable<Teacher> getTeachers() {

        Iterable<Teacher> teachers = this.teachersRepository.findAll();
        if(teachers.iterator().hasNext())
            return teachers;
        else
            throw new NoTeachersRegisteredException();

    }

    public List<Teacher> getTeachersSelected() {
        return this.teachers;
    }

    public void setHeadTeacher(Teacher headTeacher) {
        this.headTeacher = headTeacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void saveTeacherAssignments() {

        for (Teacher teacher : this.teachers) {
            if(teacher.equals(this.headTeacher))
                this.teacherAssignmentRepository.save(new TeacherAssignment(teacher, this.course, true));
            else
                teacherAssignmentRepository.save(new TeacherAssignment(teacher, this.course, false));
        }
    }
}
