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

import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import ecourse.courseManagement.domain.CourseState;
import ecourse.courseManagement.dto.CourseStateDTO;
import ecourse.courseManagement.repositories.CourseStateRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.userManagement.domain.BaseRoles;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * an application service to avoid code duplication.
 * <p>
 * since this service works with DTOs it must transform from DTOs to domain
 * objects and vice versa whenever interfacing the presentation and domain
 * layers.
 *
 * @author Paulo Gandra de Sousa
 */
@ApplicationService
class ListCourseStateDTOService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseStateRepository courseStateRepository = PersistenceContext.repositories()
            .courseStates();

    public Iterable<CourseStateDTO> allCourseStates() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        final Iterable<CourseState> types = this.courseStateRepository.findAll();
        return transformToDTO(types);
    }

    /**
     * Transform for the presentation layer.
     * <p>
     * The current implementation uses Java 8 streams. They are equivalent to more traditional java
     * code:
     *
     * <pre>
     * final List&lt;courseStateDTO&gt; ret = new ArrayList&lt;&gt;();
     * Iterator&lt;courseState&gt; iter = types.iterator();
     * while (iter.hasNext()) {
     *      ret.add(t.toDTO()));
     * }
     * return ret;
     * </pre>
     * <p>
     * Which was replaced by the for-each loop:
     *
     * <pre>
     * final List&lt;courseStateDTO&gt; ret = new ArrayList&lt;&gt;();
     * for(courseState t: types) {
     *      ret.add(t.toDTO()));
     * }
     * return ret;
     * </pre>
     * <p>
     * Which by the way could be simplified:
     *
     * <pre>
     * final List&lt;courseStateDTO&gt; ret = new ArrayList&lt;&gt;();
     * types.forEach(e -> ret.add(e.toDTO()));
     * return ret;
     * </pre>
     *
     * @param types
     * @return
     */
    private Iterable<CourseStateDTO> transformToDTO(final Iterable<CourseState> types) {
        return StreamSupport.stream(types.spliterator(), true)
                .map(CourseState::toDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    public Iterable<CourseStateDTO> activeCourseStates() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.MANAGER);

        return transformToDTO(this.courseStateRepository.activeCourseStates());
    }

}
