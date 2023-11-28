package ecourse.userManagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.teacherManagement.domain.Acronym;
import ecourse.teacherManagement.domain.BirthDate;
import ecourse.teacherManagement.domain.TaxNumber;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Teacher.
 */
@Entity
@Table(name = "TEACHER")
public class Teacher implements AggregateRoot<Acronym>, Serializable {

    @EmbeddedId
    @Column(name = "ACRONYM", nullable = false, unique = true)
    private Acronym acronym;

    @Column(name = "TAXNUMBER", nullable = false)
    private TaxNumber taxNumber;

    @Column(nullable = false)
    private BirthDate birthDate;

    @OneToOne()
    private SystemUser systemUser;

    public Teacher(final SystemUser user, final Acronym acronym, final BirthDate date, final TaxNumber number) {
        if (acronym == null || user == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.birthDate = date;
        this.acronym = acronym;
        this.taxNumber = number;
    }

    protected Teacher() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public Acronym acronym() {
        return identity();
    }

    @Override
    public Acronym identity() {
        return this.acronym;
    }

    public String toString() {
        return identity().toString();
    }
}
