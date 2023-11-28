package ecourse.scheduleMeeting.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class ParticipantResponse implements ValueObject {

    @OneToOne()
    private SystemUser systemUser;

    protected ParticipantResponse() {
    }

    public ParticipantResponse(SystemUser user) {
        this.systemUser = user;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
