package ecourse.teacherManagement.domain;

import javax.persistence.Embeddable;

@Embeddable
public class TaxNumber {

    private int taxNumber;

    public TaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    public TaxNumber() {

    }
}
