package ecourse.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import ecourse.courseManagement.domain.Course;
import ecourse.requestEnrollment.domain.Enrollment;
import ecourse.requestEnrollment.domain.EnrollmentId;
import ecourse.requestEnrollment.repositories.EnrollmentsRepository;
import ecourse.userManagement.domain.Student;

public class InMemoryEnrollmentRepository extends InMemoryDomainRepository<Enrollment, EnrollmentId> implements EnrollmentsRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public int getNumberOfAvailableEnrollments(Course course) {
        //TODO: implement this method
        return 0;
    }

    @Override
    public Iterable<Enrollment> findEnrollmentsByStudent(Student student) {
        return null;
    }
}


