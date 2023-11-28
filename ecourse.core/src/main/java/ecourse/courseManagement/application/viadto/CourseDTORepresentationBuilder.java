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

import eapli.framework.representations.RepresentationBuilder;
import ecourse.courseManagement.domain.CourseStateEnum;
import ecourse.courseManagement.dto.CourseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The builder for a CourseDTO using the RepresentationBuilder interface. This
 * builder is forgiving in the sense that it ignores properties that it doesn't
 * understand in case the Dish has evolved to support new properties.
 *
 * @author Paulo Gandra de Sousa
 */
class CourseDTORepresentationBuilder implements RepresentationBuilder<CourseDTO> {

    private static final Logger logger = LogManager.getLogger(CourseDTORepresentationBuilder.class);

    private static final String PROPERTY_NOT_KNOW_IN_COURSE_DTO = "Property '{}' not know in courseDTO";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    // an example of a builder that creates the "product" but hides that way from
    // the client code
    private CourseDTO dto = new CourseDTO();
    private String childObject = "";

    @Override
    public CourseDTO build() {

        final CourseDTO ret = dto;
        // ensure if someone reuses this builder won't change the already built object
        // but actually
        // work on a new object
        dto = new CourseDTO();
        return ret;
    }

    @Override
    public RepresentationBuilder<CourseDTO> startObject(final String name) {
        childObject = name;
        return this;
    }

    @Override
    public RepresentationBuilder<CourseDTO> endObject() {
        childObject = "";
        return this;
    }

    @Override
    public RepresentationBuilder<CourseDTO> withProperty(final String name, final String value) {
        if ("name".equals(name)) {
            dto.setName(value);
        } else if ("state".equals(name)) {
            try {
                CourseStateEnum stateEnum = CourseStateEnum.valueOf(value);
                dto.setState(stateEnum);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid CourseState value: " + value, e);
            }
        } else if ("start date".equals(name)) {
            try {
                Calendar startDate = Calendar.getInstance();
                startDate.setTime(dateFormat.parse(value));
                dto.setStartDate(startDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else if ("end date".equals(name)) {
            try {
                Calendar endDate = Calendar.getInstance();
                endDate.setTime(dateFormat.parse(value));
                dto.setEndDate(endDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else if ("max enrollments".equals(name)) {
            dto.setMaxEnrollments(Integer.parseInt(value));
        } else if ("description".equals(name)) {
            dto.setDescription(value);
        } else {
            logger.warn(PROPERTY_NOT_KNOW_IN_COURSE_DTO, name);
        }
        return this;
    }

    @Override
    public RepresentationBuilder<CourseDTO> withElement(final String value) {
        logger.warn("CourseDTO has no collections; tried to create element {}", value);
        return this;
    }
}
