/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package ecourse.teacherManagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.util.regex.Pattern;


@Embeddable
public class Acronym implements ValueObject, Comparable<Acronym> {

    @Serial
    private static final long serialVersionUID = -2323185887110150475L;

    private String acronym;

    public Acronym(String acronym) {
        if (StringPredicates.isNullOrEmpty(acronym)) {
            throw new IllegalArgumentException(
                    "Acronym should neither be null nor empty");
        }

        acronym = acronym.toUpperCase();

        if(regexChecker(acronym)) {
            this.acronym = acronym;
        } else {
            throw new IllegalArgumentException(
                    "Acronym should be 3 capital letters");
        }

    }

    private boolean regexChecker(String acronym) {

        String pattern = "^[A-Z]{3}$";
        Pattern regex = Pattern.compile(pattern);
        // Use the Matcher class to match the input string against the pattern
        return regex.matcher(acronym).matches();
    }

    protected Acronym() {
        // for ORM
    }

    public static Acronym valueOf(final String acronym) {
        return new Acronym(acronym);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Acronym that)) {
            return false;
        }

        return this.acronym.equals(that.acronym);
    }

    @Override
    public int hashCode() {
        return this.acronym.hashCode();
    }

    @Override
    public String toString() {
        return this.acronym;
    }

    @Override
    public int compareTo(final Acronym arg0) {
        return acronym.compareTo(arg0.acronym);
    }
}
