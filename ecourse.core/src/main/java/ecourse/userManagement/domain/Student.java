package ecourse.userManagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.examTaken.domain.ExamTaken;
import ecourse.studentManagement.domain.MecanographicNumber;
import ecourse.teacherManagement.domain.BirthDate;
import ecourse.teacherManagement.domain.TaxNumber;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Student.
 */
@Entity
@Table(name = "STUDENT")
public class Student implements AggregateRoot<MecanographicNumber> {

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private final List<ExamTaken> examTaken = new ArrayList<>();

    @EmbeddedId
    @Column(name = "NUMBER", nullable = false, unique = true)
    private MecanographicNumber mecanographicNumber;

    @Column(name = "TAXNUMBER", nullable = false, unique = true)
    private TaxNumber taxNumber;

    @Column(name = "DAY", nullable = false)
    private BirthDate birthDate;

    @OneToOne()
    private SystemUser systemUser;

    public Student(final SystemUser user, final MecanographicNumber mecanographicNumber, final BirthDate date, final TaxNumber number) {
        if (mecanographicNumber == null || user == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.birthDate = date;
        this.mecanographicNumber = mecanographicNumber;
        this.taxNumber = number;
    }

    protected Student() {
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

    public MecanographicNumber mecanographicNumber() {
        return identity();
    }

    @Override
    public MecanographicNumber identity() {
        return this.mecanographicNumber;
    }
}
