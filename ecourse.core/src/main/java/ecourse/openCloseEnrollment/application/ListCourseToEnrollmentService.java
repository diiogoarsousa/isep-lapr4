/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ecourse.openCloseEnrollment.application;

import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.requestEnrollment.domain.ApprovalStatusEnum;
import ecourse.requestEnrollment.domain.Enrollment;
import ecourse.requestEnrollment.domain.EnrollmentId;
import ecourse.requestEnrollment.repositories.EnrollmentsRepository;
import ecourse.userManagement.domain.BaseRoles;
import ecourse.userManagement.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * An application service to avoid code duplication since getting the list of all dishes is needed
 * in several use cases.
 */
@ApplicationService
public class ListCourseToEnrollmentService {
    private static final Logger logger = LoggerFactory.getLogger(ListCourseToEnrollmentService.class);

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();
    private final EnrollmentsRepository enrollmentsRepository = PersistenceContext.repositories().enrollments();


    public Course changeEnrollmentState(Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        if (CourseStateEnum.OPEN.equals(course.courseState())) {
            course.changeState(CourseStateEnum.ENROLL);

        } else {
            course.changeState(CourseStateEnum.IN_PROGRESS);
        }
        return courseRepository.save(course);
    }


    public Iterable<Course> findCourseByStateInEnrollments(CourseStateEnum courseState) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER,
                BaseRoles.MANAGER);
        return courseRepository.findCourseByState(courseState);

    }

    public int getNumberOfAvailableEnrollments(Course course) {
        return enrollmentsRepository.getNumberOfAvailableEnrollments(course);
    }

    /**
     * Enrolls in a course
     *
     * @param course to enroll in
     * @return true if the enrollment was enrolled successfully, false otherwise.
     */
    public boolean enroll(Course course, Student student) {
        if (course.getState() == CourseStateEnum.CLOSED) {
            return false;
        }
        if (getNumberOfAvailableEnrollments(course) == 0) {
            course.changeState(CourseStateEnum.CLOSED);
            logger.info("Course " + course.name() + " is now closed");
            courseRepository.save(course);
            return false;
        }

        Enrollment enrollment = new Enrollment(new EnrollmentId(course, student), Calendar.getInstance(), ApprovalStatusEnum.ACCEPTED);

        return enrollmentsRepository.save(enrollment) != null;
    }

}
