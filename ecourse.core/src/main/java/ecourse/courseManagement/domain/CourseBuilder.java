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
package ecourse.courseManagement.domain;

import eapli.framework.domain.model.DomainFactory;

import java.util.Calendar;
import java.util.UUID;

/**
 * A Course builder. Since there is one optional component of Course, i.e.,
 * duration info, we are using a builder to simplify the class
 * and avoid overloading constructors or too much conditional logic on the
 * constructor.
 *
 * @author Paulo Gandra de Sousa 2021/05/04
 */
public class CourseBuilder implements DomainFactory<Course> {

    private Course theCourse;

    private UUID identifier;

    private String courseName;
    private CourseStateEnum courseState;

    private Calendar startDate;
    private Calendar endDate;

    private int minEnrolments;

    private int maxEnrolments;

    private String headTeacher;

    private String description;

    /**
     * @return a new builder for a Course
     */
    public static CourseBuilder builder() {
        return new CourseBuilder();
    }

    /**
     * @param identifier
     * @return itself
     */
    public CourseBuilder identified(final UUID identifier) {
        this.identifier = identifier;
        return this;
    }

    /**
     * @param courseState
     * @return itself
     */
    public CourseBuilder withState(final CourseStateEnum courseState) {
        this.courseState = courseState;
        return this;
    }

    /**
     * @param courseName
     * @return itself
     */
    public CourseBuilder named(final String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * @param startDate
     * @return itself
     */
    public CourseBuilder withStartDate(final Calendar startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * @param endDate
     * @return itself
     */
    public CourseBuilder withEndDate(final Calendar endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * @param minEnrolments
     * @return itself
     */
    public CourseBuilder withMinEnrolments(final int minEnrolments) {
        this.minEnrolments = minEnrolments;
        return this;
    }

    /**
     * @param maxEnrolments
     * @return itself
     */
    public CourseBuilder withMaxEnrolments(final int maxEnrolments) {
        this.maxEnrolments = maxEnrolments;
        return this;
    }

    /**
     * @param headTeacher
     * @return itself
     */
    public CourseBuilder withHeadTeacher(final String headTeacher) {
        this.headTeacher = headTeacher;
        return this;
    }

    /**
     * @param description
     * @return itself
     */
    public CourseBuilder described(final String description) {
        this.description = description;
        return this;
    }

    /**
     * @return a new Course instance
     */
    private Course buildOrThrow() {
        if (theCourse != null) {
            return theCourse;
        }
        if (courseName != null && courseState != null) {
            theCourse = new Course(identifier, courseName, courseState, startDate, endDate, minEnrolments, maxEnrolments, headTeacher, description);
            return theCourse;
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * @return a new Course instance
     */
    @Override
    public Course build() {
        final Course ret = buildOrThrow();
        theCourse = null;
        return ret;
    }
}
