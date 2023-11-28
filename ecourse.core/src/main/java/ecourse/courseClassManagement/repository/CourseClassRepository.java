package ecourse.courseClassManagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import ecourse.courseClassManagement.domain.CourseClass;
import ecourse.courseManagement.domain.Course;


public interface CourseClassRepository extends DomainRepository<String, CourseClass> {

    public Iterable<CourseClass> getAllCourseClassesFromCourse(Course course);
}
