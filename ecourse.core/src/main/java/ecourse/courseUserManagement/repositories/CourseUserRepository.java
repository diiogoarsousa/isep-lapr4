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
package ecourse.courseUserManagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import ecourse.courseUserManagement.domain.CourseUser;
import ecourse.courseUserManagement.domain.MecanographicNumber;

import java.util.Optional;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface CourseUserRepository extends DomainRepository<MecanographicNumber, CourseUser> {

    /**
     * Returns the course user (utente) whose username is given.
     *
     * @param name the username to search for
     * @return
     */
    Optional<CourseUser> findByUsername(Username name);

    /**
     * Returns the cafeteria user (utente) with the given mecanographic number.
     *
     * @param number
     * @return
     */
    default Optional<CourseUser> findByMecanographicNumber(
            final MecanographicNumber number) {
        return ofIdentity(number);
    }

    /**
     * @return
     */
    Iterable<CourseUser> findAllActive();
}
