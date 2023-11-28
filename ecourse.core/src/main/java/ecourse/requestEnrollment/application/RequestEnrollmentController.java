package ecourse.requestEnrollment.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import ecourse.courseManagement.application.ListCourseService;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.studentManagement.application.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@UseCaseController
public class RequestEnrollmentController {
    private static final Logger logger = LoggerFactory.getLogger(RequestEnrollmentController.class);

    private final ListCourseService listCourseService = new ListCourseService();
    private final StudentService studentService = new StudentService(PersistenceContext.repositories().students());
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Gets list of courses that are ready to enroll
     *
     * @return list of courses
     */
    public List<Course> listReadyToEnrollCourses() {
        return listCourseService.getCoursesByState(CourseStateEnum.ENROLL);
    }

    /**
     * Requests enrollment in a course
     *
     * @param course to enroll in
     * @return true if the enrollment was requested successfully, false otherwise.
     */
    public boolean requestEnrollment(Course course) {
        if (authz.session().isPresent()) {
            return listCourseService.requestEnrollment(course, studentService.findBySystemUser(authz.session().get().authenticatedUser()));
        } else {
            logger.warn("No user logged in");
            return false;
        }
    }

}
