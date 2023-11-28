package ecourse.courseManagement.application;

import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import ecourse.courseManagement.domain.CourseState;
import ecourse.courseManagement.repositories.CourseStateRepository;
import ecourse.userManagement.domain.BaseRoles;

/**
 * An application service to avoid code duplication.
 */
@ApplicationService
class ListCourseStateService {
    private final AuthorizationService authz;
    private final CourseStateRepository courseStateRepository;

    public ListCourseStateService(final AuthorizationService authz, final CourseStateRepository courseStateRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.courseStateRepository = courseStateRepository;
    }

    /**
     * Gets an Iterable with all the course states.
     *
     * @return an Iterable with all the course states.
     */
    public Iterable<CourseState> allCourseTypes() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        return courseStateRepository.findAll();
    }

    /**
     * Gets an Iterable with all the active course states.
     *
     * @return an Iterable with all the active course states.
     */
    public Iterable<CourseState> activeCourseStates() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        return courseStateRepository.activeCourseStates();
    }
}
