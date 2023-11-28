package ecourse.studentManagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.teacherManagement.domain.BirthDate;
import ecourse.teacherManagement.domain.TaxNumber;
import ecourse.userManagement.domain.Student;

import java.util.Calendar;

/**
 * A factory for User entities.
 * <p>
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 */
public class StudentBuilder implements DomainFactory<Student> {
    private SystemUser systemUser;
    private MecanographicNumber number;
    private TaxNumber taxNumber;
    private BirthDate birthDate;

    public StudentBuilder withSystemUser(final SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public StudentBuilder withNumber(final String number) {
        this.number = new MecanographicNumber(number);
        return this;
    }

    public StudentBuilder withTaxNumber(final int taxNumber) {
        this.taxNumber = new TaxNumber(taxNumber);
        return this;
    }

    public StudentBuilder withBirthDate(final Calendar day) {
        this.birthDate = new BirthDate(day);
        return this;
    }

    public StudentBuilder with(final SystemUser systemUser, final String number, final int taxNumber, final Calendar birthDate) {
        withSystemUser(systemUser);
        withNumber(number);
        withTaxNumber(taxNumber);
        withBirthDate(birthDate);
        return this;
    }

    @Override
    public Student build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Student(this.systemUser, this.number, this.birthDate, this.taxNumber);
    }
}
