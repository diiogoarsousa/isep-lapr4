package ecourse.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.courseClassManagement.domain.CourseClass;
import ecourse.courseClassManagement.repository.CourseClassRepository;
import ecourse.courseManagement.domain.Course;

import javax.persistence.TypedQuery;
import java.util.Optional;


public class JpaCourseClassRepository extends JpaAutoTxRepository<CourseClass, Long, String> implements CourseClassRepository {

    public JpaCourseClassRepository(final TransactionalContext autoTx) {
        super(autoTx, "title");
    }

    public JpaCourseClassRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "title");
    }


    public Optional<CourseClass> ofIdentity(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(String id) {
        return CourseClassRepository.super.containsOfIdentity(id);
    }

    @Override
    public boolean contains(CourseClass entity) {
        return CourseClassRepository.super.contains(entity);
    }

    @Override
    public void deleteOfIdentity(String entityId) {

    }

    @Override
    public long size() {
        return CourseClassRepository.super.size();
    }

    @Override
    public void remove(CourseClass entity) {
        CourseClassRepository.super.remove(entity);
    }

    @Override
    public void removeOfIdentity(String entityId) {
        CourseClassRepository.super.removeOfIdentity(entityId);
    }

    /**
     * Returns all the course classes from a course
     * @param course
     * @return
     */
    @Override
    public Iterable<CourseClass> getAllCourseClassesFromCourse(Course course) {

        String jpqlQuery = "SELECT c1 FROM COURSE_CLASS c1, COURSE_CLASS c2 WHERE c1.title <> c2.title AND c1.course =: c";


        TypedQuery<CourseClass> query = entityManager().createQuery(jpqlQuery, CourseClass.class);
        query.setParameter("c", course);

        return query.getResultList();
    }

}
