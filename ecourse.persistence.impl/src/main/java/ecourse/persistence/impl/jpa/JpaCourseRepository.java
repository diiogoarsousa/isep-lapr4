package ecourse.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.courseManagement.repositories.CourseRepository;

import javax.persistence.TypedQuery;
import java.util.*;

public class JpaCourseRepository extends JpaAutoTxRepository<Course, Long, Long>
        implements CourseRepository {

    /**
     * Constructor
     *
     * @param autoTx
     */
    public JpaCourseRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * JpaCourseRepository constructor
     *
     * @param puname
     */
    public JpaCourseRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }


    /**
     * Optional<Course> ofIdentity(UUID id)
     *
     * @param id
     * @return Optional<Course>
     */
    @Override
    public Optional<Course> ofIdentity(UUID id) {
        return Optional.empty();
    }

    /**
     * containsOfIdentity(UUID id)
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean containsOfIdentity(UUID id) {
        return CourseRepository.super.containsOfIdentity(id);
    }

    /**
     * contains(Course entity)
     *
     * @param entity
     * @return boolean
     */
    @Override
    public boolean contains(Course entity) {
        return CourseRepository.super.contains(entity);
    }

    /**
     * delete(Course entity)
     *
     * @param entityId
     * @return boolean
     */
    @Override
    public void deleteOfIdentity(UUID entityId) {

    }

    /**
     * size()
     *
     * @return long
     */
    @Override
    public long size() {
        return CourseRepository.super.size();
    }

    /**
     * remove(Course entity)
     *
     * @param entity
     * @return void
     */
    @Override
    public void remove(Course entity) {
        CourseRepository.super.remove(entity);
    }

    /**
     * removeOfIdentity(UUID entityId)
     *
     * @param entityId
     * @return void
     */
    @Override
    public void removeOfIdentity(UUID entityId) {
        CourseRepository.super.removeOfIdentity(entityId);
    }

    /**
     * Method to find a course by its name
     *
     * @param courseName
     * @return Course
     */
    @Override
    public Course findCourseByName(String courseName) {
        TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.courseName = :name", Course.class);
        query.setParameter("name", courseName);
        List<Course> results = query.getResultList();
        if (results.isEmpty()) {
            throw new IllegalStateException("No course found with name " + courseName);
        }
        return results.get(0);
    }

    /**
     * Method to find a course by its state
     *
     * @param courseState
     * @return Iterable<Course>
     */
    public Iterable<Course> findCourseByState(CourseStateEnum courseState) {
        TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.courseState = :state", Course.class);
        query.setParameter("state", courseState);
        List<Course> results = query.getResultList();
        if (results.isEmpty()) {
            throw new IllegalStateException("No course found with state " + courseState);
        }
        return results;
    }

    @Override
    public Iterable<Course> findAllCoursesNotClosed() {
        return match(
                "e.courseState<>'CLOSED'");
    }

    @Override
    public Iterable<Course> findEnrollCourses() {
        final Map<String, Object> params = new HashMap<>();
        params.put("state", CourseStateEnum.ENROLL);
        return match("e.state = :state", params);
    }
}
