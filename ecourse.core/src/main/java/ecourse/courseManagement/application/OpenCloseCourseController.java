package ecourse.courseManagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.courseManagement.repositories.CourseStateRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.userManagement.domain.BaseRoles;

@UseCaseController
public class OpenCloseCourseController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();

    private final CourseStateRepository courseStateRepo = PersistenceContext.repositories().courseStates();

    private final ListCourseStateService svcCourseStates = new ListCourseStateService(authz, courseStateRepo);
    private final ListCourseService svcCourses = new ListCourseService();


    /**
     * Method to find a course by its state
     *
     * @param courseState
     * @return Iterable<Course>
     */
    public Iterable<Course> findCourseByState(CourseStateEnum courseState) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        return svcCourses.findCourseByState(courseState);
    }

    /**
     * Method to open or close a course
     *
     * @param course
     * @return String
     */
    public String openCloseCourse(Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        svcCourses.toggleOpenCloseCourse(course);

        return course.getState().toString();
    }
}
