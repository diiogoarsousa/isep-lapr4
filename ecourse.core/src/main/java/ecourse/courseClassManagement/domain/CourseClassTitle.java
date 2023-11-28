package ecourse.courseClassManagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseClassTitle implements ValueObject {

    @Column
    private String title;

    public CourseClassTitle(String title) {
        if(title != null && !title.isEmpty())
            this.title = title;
    }

    protected CourseClassTitle() {
        // for ORM only
    }

    public String toString() {
        return this.title;
    }
}
