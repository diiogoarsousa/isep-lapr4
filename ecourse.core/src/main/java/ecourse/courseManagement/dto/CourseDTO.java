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
package ecourse.courseManagement.dto;

import eapli.framework.representations.dto.DTO;
import ecourse.courseManagement.domain.CourseStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

/**
 * A pure DTO for dishes.
 * <p>
 * This class uses Project Lombok's <code>Data<code> annotation to avoid
 * repetitive coding.
 *
 * @author Paulo Gandra de Sousa
 */
@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private String name;
    private CourseStateEnum state;
    private Calendar startDate;
    private Calendar endDate;

    private Integer minEnrollments;
    private Integer maxEnrollments;

    private String headTeacher;
    private String description;


    public String getName() {
        return String.valueOf(name);
    }

    public String getState() {
        return CourseStateEnum.CLOSED.toString();
    }

    public void setState(CourseStateEnum state) {
        this.state = state;
    }

    public String getStartDate() {
        return startDate.getTime().toString();
    }

    public String getEndDate() {
        return endDate.getTime().toString();
    }

    public  Integer getMinEnrollments() {
        return minEnrollments;
    }

    public Integer getMaxEnrollments() {
        return maxEnrollments;
    }

    public String getHeadTeacher() {
        return headTeacher;
    }

    public String getDescription() {
        return description;
    }
}
