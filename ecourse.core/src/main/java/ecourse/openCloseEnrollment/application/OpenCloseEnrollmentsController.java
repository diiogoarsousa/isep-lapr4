package ecourse.openCloseEnrollment.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.userManagement.domain.BaseRoles;


public class OpenCloseEnrollmentsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ecourse.openCloseEnrollment.application.ListCourseToEnrollmentService enrollmentInCourseService = new ecourse.openCloseEnrollment.application.ListCourseToEnrollmentService();

    public Course changeCourseState(final Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        return this.enrollmentInCourseService.changeEnrollmentState(course);

    }

    public Iterable<Course> findCourseByStateInEnrollments(CourseStateEnum courseState) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);
        return this.enrollmentInCourseService.findCourseByStateInEnrollments(courseState);
    }
}
