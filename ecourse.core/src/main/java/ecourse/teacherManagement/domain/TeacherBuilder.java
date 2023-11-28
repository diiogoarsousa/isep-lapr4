package ecourse.teacherManagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.userManagement.domain.Teacher;

import java.util.Calendar;

/**
 * A factory for User entities.
 * <p>
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 */
public class TeacherBuilder implements DomainFactory<Teacher> {
    private SystemUser systemUser;
    private Acronym acronym;
    private TaxNumber taxNumber;
    private BirthDate birthDate;

    public TeacherBuilder withSystemUser(final SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public TeacherBuilder withAcronym(final String acronym) {
        this.acronym = new Acronym(acronym);
        return this;
    }

    public TeacherBuilder withTaxNumber(final int taxNumber) {
        this.taxNumber = new TaxNumber(taxNumber);
        return this;
    }

    public TeacherBuilder withBirthDate(final Calendar day) {
        this.birthDate = new BirthDate(day);
        return this;
    }

    public TeacherBuilder with(final SystemUser systemUser, final String acronym, final int taxNumber, final Calendar birthDate) {
        withSystemUser(systemUser);
        withAcronym(acronym);
        withTaxNumber(taxNumber);
        withBirthDate(birthDate);
        return this;
    }

    @Override
    public Teacher build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Teacher(this.systemUser, this.acronym, this.birthDate, this.taxNumber);
    }
}
