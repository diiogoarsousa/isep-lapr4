package ecourse.persistence.impl.jpa;

import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.courseManagement.domain.Course;
import ecourse.requestEnrollment.domain.Enrollment;
import ecourse.requestEnrollment.domain.EnrollmentId;
import ecourse.requestEnrollment.repositories.EnrollmentsRepository;
import ecourse.userManagement.domain.Student;

import javax.persistence.TypedQuery;

public class JpaEnrollmentsRepository extends JpaAutoTxRepository<Enrollment, EnrollmentId, EnrollmentId>
        implements EnrollmentsRepository {

    /**
     * JpaEnrollmentsRepository constructor
     *
     * @param puname persistence unit name
     */
    public JpaEnrollmentsRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public int getNumberOfAvailableEnrollments(Course course) {
        int max = course.getMaxEnrollments();
        String query = "SELECT COUNT(e) FROM Enrollment e WHERE e.id.course = :course";
        TypedQuery<Long> typedQuery = entityManager().createQuery(query, Long.class);
        typedQuery.setParameter("course", course);
        return max - typedQuery.getSingleResult().intValue();
    }

    @Override
    public Iterable<Enrollment> findEnrollmentsByStudent(Student student) {
        String query = "SELECT e FROM Enrollment e WHERE e.id.student = :student";
        TypedQuery<Enrollment> typedQuery = entityManager().createQuery(query, Enrollment.class);
        typedQuery.setParameter("student", student);
        return typedQuery.getResultList();
    }
}
