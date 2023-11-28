package ecourse.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import ecourse.courseClassManagement.domain.CourseClass;
import ecourse.courseClassManagement.repository.CourseClassRepository;
import ecourse.courseManagement.domain.Course;

public class InMemoryCourseClassRepository extends InMemoryDomainRepository<CourseClass, String> implements CourseClassRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<CourseClass> getAllCourseClassesFromCourse(Course course) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


