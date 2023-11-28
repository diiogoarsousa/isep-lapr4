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
package ecourse.courseManagement.application.viadto;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import ecourse.courseManagement.dto.CourseDTO;
import ecourse.courseManagement.dto.CourseStateDTO;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.courseManagement.repositories.CourseStateRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.userManagement.domain.BaseRoles;

/**
 * The controller for the use case "register new dish" using DTOs.
 * <p>
 * Since this controller works with DTOs it must transform from DTOs to domain
 * objects and vice-versa whenever interfacing the presentation and domain
 * layers. Also, some some related domain object is needed it must first be
 * retrieved from the repository prior to its use
 * </p>
 *
 * @author Paulo Gandra de Sousa
 * @see ecourse.courseManagement.application.CreateCourseController
 */
@UseCaseController
public class RegisterCourseViaDTOController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListCourseStateDTOService svc = new ListCourseStateDTOService();

    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();
    private final CourseStateRepository courseStateRepository = PersistenceContext.repositories().courseStates();

    /**
     * @param dto
     * @return
     */
    public CourseDTO registerCourse(final CourseDTO dto) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        final var newCourse = new CourseDTOParser(courseStateRepository).valueOf(dto);

        return courseRepository.save(newCourse).toDTO();
    }

    public Iterable<CourseStateDTO> courseStates() {
        return svc.activeCourseStates();
    }
}
