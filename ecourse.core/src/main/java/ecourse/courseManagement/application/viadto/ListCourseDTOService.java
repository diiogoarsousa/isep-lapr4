package ecourse.courseManagement.application.viadto;

import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.dto.CourseDTO;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.userManagement.domain.BaseRoles;

import java.util.ArrayList;
import java.util.List;

/**
 * An application service to avoid code duplication.
 * <p>
 * Since this service works with DTOs it must transform from DTOs to domain
 * objects and vice versa whenever interfacing the presentation and domain
 * layers.
 *
 * @author Paulo Gandra de Sousa
 */
@ApplicationService
class ListCourseDTOService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();

    public Iterable<CourseDTO> allCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER,
                BaseRoles.MANAGER);

        final Iterable<Course> courses = this.courseRepository.findAll();

        // transform for the presentation layer
        final List<CourseDTO> ret = new ArrayList<>();
        courses.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }
}
