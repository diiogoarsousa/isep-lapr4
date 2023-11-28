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

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.strings.util.StringPredicates;
import ecourse.courseManagement.dto.CourseStateDTO;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A dish type, e.g., vegetarian or fish or meat.
 * <p>
 * This class is implemented in a more traditional way than DDD, by using
 * primitive types for the attributes instead of value objects. this means that
 * some semantic is lost and potential code duplication may rise if the same
 * concept is used as an attribute in more than one class. however, the learning
 * curve is smoother when compared to full DDD.
 *
 * @author MCN 29/03/2016.
 */
@XmlRootElement
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"acronym"})})
public class CourseState implements AggregateRoot<String> {

    private static final long serialVersionUID = 1L;

    /**
     * ORM primary key
     */
    @Id
    @GeneratedValue
    private Long pk;

    @Version
    private Long version;

    /**
     * business ID
     */
    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    private String acronym;

    @XmlElement
    @JsonProperty
    private String description;

    @XmlElement
    @JsonProperty
    private boolean active;

    protected CourseState() {
        // for ORM
    }

    /**
     * CourseState constructor.
     *
     * @param courseState Mandatory
     * @param description Optional
     */
    public CourseState(final CourseStateEnum courseState, final Description description) {
        setCourseState(courseState);
        setDescription(description);
        this.active = true;
    }

    /**
     * Ensure name is not null or empty.
     *
     * @param name The name to assess.
     * @return True if name meets minimum requirements. False if name does not meet
     * minimum requirements.
     */
    private static boolean nameMeetsMinimumRequirements(final String name) {
        return !StringPredicates.isNullOrEmpty(name);
    }

    /**
     * Ensure description is not null or empty.
     *
     * @param description The description to assess.
     * @return True if description meets minimum requirements. False if description
     * does not meet minimum requirements.
     */
    private static boolean descriptionMeetsMinimumRequirements(final String description) {
        return !StringPredicates.isNullOrEmpty(description);
    }

    /**
     * Sets and validates newDescription.
     *
     * @param newDescription
     */
    private void setDescription(final Description newDescription) {
        if (!descriptionMeetsMinimumRequirements(String.valueOf(newDescription))) {
            throw new IllegalArgumentException("Invalid Description");
        }
        this.description = String.valueOf(newDescription);
    }

    private void setCourseState(final CourseStateEnum newCourseState) {
        if (!nameMeetsMinimumRequirements(String.valueOf(newCourseState))) {
            throw new IllegalArgumentException("Invalid State");
        }
        this.acronym = String.valueOf(newCourseState);
    }

    public String description() {
        return this.description;
    }

    public boolean isActive() {
        return this.active;
    }

    /**
     * Toggles the state of the coursetype, activating it or deactivating it
     * accordingly.
     *
     * @return whether the coursetype is active or not
     */
    public boolean toogleState() {

        this.active = !this.active;
        return isActive();
    }

    /**
     * Change CourseState description
     *
     * @param newDescription New description.
     */
    public void changeDescriptionTo(final String newDescription) {
        if (!descriptionMeetsMinimumRequirements(newDescription)) {
            throw new IllegalArgumentException();
        }
        this.description = newDescription;
    }

    @Override
    public boolean hasIdentity(final String id) {
        return id.equalsIgnoreCase(this.acronym);
    }

    @Override
    public String identity() {
        return this.acronym;
    }

    @Override
    public boolean sameAs(final Object other) {
        final CourseState courseState = (CourseState) other;
        return this.equals(courseState) && description().equals(courseState.description())
                && isActive() == courseState.isActive();
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    public CourseStateDTO toDTO() {
        return new CourseStateDTO(acronym, description, active);
    }

    @Override
    public String toString() {
        return identity();
    }
}
