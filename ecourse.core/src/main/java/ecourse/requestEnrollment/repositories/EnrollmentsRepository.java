package ecourse.requestEnrollment.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import ecourse.courseManagement.domain.Course;
import ecourse.requestEnrollment.domain.Enrollment;
import ecourse.requestEnrollment.domain.EnrollmentId;
import ecourse.userManagement.domain.Student;

public interface EnrollmentsRepository extends DomainRepository<EnrollmentId, Enrollment> {

    /**
     * Gets the number of available enrollments for a course
     *
     * @param course to get the number of available enrollments
     * @return number of available enrollments
     */
    int getNumberOfAvailableEnrollments(Course course);

    Iterable<Enrollment> findEnrollmentsByStudent(Student student);
}
