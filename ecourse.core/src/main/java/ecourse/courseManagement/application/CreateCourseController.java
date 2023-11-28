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
package ecourse.courseManagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseBuilder;
import ecourse.courseManagement.domain.CourseState;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.courseManagement.repositories.CourseStateRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.userManagement.domain.BaseRoles;

import java.util.Calendar;

/**
 * The controller for the use case "create new course" using the domain objects.
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 * @see ecourse.courseManagement.application.viadto.RegisterCourseViaDTOController
 */
@UseCaseController
public class CreateCourseController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();

    private final CourseStateRepository courseStateRepo = PersistenceContext.repositories().courseStates();

    private final ListCourseStateService svcCourseStates = new ListCourseStateService(authz, courseStateRepo);
    private final ListCourseService svcCourses = new ListCourseService();

    /**
     * @param courseName    the name of the course
     * @param courseState   the state of the course
     * @param startDate     the start date of the course
     * @param endDate       the end date of the course
     * @param minEnrolments the minimum number of enrolments for the course
     * @param maxEnrolments the maximum number of enrolments for the course
     * @param headTeacher   the head teacher of the course
     * @return the created course
     */
    public Course createCourse(final String courseName, final CourseStateEnum courseState, final Calendar startDate, final Calendar endDate, final Integer minEnrolments, final Integer maxEnrolments, final String headTeacher) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        final var newCourse = new CourseBuilder().withState(courseState).named(courseName).withStartDate(startDate).withEndDate(endDate).withMinEnrolments(minEnrolments).withMaxEnrolments(maxEnrolments).withHeadTeacher(headTeacher).build();
        //TODO: USE SERVICE HERE INSTEAD OF REPO
        return courseRepository.save(newCourse);
    }

    /**
     * @return an iterable with all the courses states
     */
    public Iterable<CourseState> CourseStates() {
        return svcCourseStates.activeCourseStates();
    }
}
