/*
 * Copyright (c) 2013-2022 the original author or authors.
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
package ecourse.teacherManagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serial;
import java.util.Calendar;

/**
 * A Signup Request. This class represents the Signup Request created right
 * after a person applies for a Client User account.
 *
 * <p>
 * It follows a DDD approach where all of its properties are instances of value
 * objects. This approach may seem a little more complex than just having String
 * or native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern.
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Entity
public class SignupRequest implements AggregateRoot<Username> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private Username username;
    private Password password;
    private Name name;
    private EmailAddress email;

    private Acronym acronym;


    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    /* package */ SignupRequest(final Username username, final Password password, final Name name,
                                final EmailAddress email, final Acronym acronym,
                                final Calendar createdOn) {
        Preconditions.noneNull(username, password, name, email, acronym);

        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.acronym = acronym;
        this.createdOn = createdOn;
    }

    protected SignupRequest() {
        // for ORM only
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
        if (!(other instanceof SignupRequest that)) {
            return false;
        }

        if (this == that) {
            return true;
        }

        return username.equals(that.username) && password.equals(that.password)
                && name.equals(that.name) && email.equals(that.email)
                && acronym.equals(that.acronym);
    }

    public Acronym acronym() {
        return acronym;
    }

    @Override
    public Username identity() {
        return username;
    }

    public Username username() {
        return username;
    }

    public Name name() {
        return name;
    }

    public EmailAddress email() {
        return email;
    }

    public Password password() {
        return password;
    }
}
