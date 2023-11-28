package ecourse.examManagement.domain.sections;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
public abstract class Section implements ValueObject, Comparable<Section> {
    String description;
}
