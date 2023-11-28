package ecourse.courseManagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.domain.repositories.LockableDomainRepository;
import ecourse.courseManagement.domain.CourseState;

/**
 * The repository for Course Types.
 * <p>
 * As an example we are supporting pessimistic locking in this repository by extending from
 * {@link LockableDomainRepository}
 */
public interface CourseStateRepository
        extends DomainRepository<String, CourseState>, LockableDomainRepository<String, CourseState> {

    /**
     * Returns the active courses states.
     *
     * @return An iterable for CourseStates.
     */
    Iterable<CourseState> activeCourseStates();
}
