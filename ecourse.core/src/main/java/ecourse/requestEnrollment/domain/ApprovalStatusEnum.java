package ecourse.requestEnrollment.domain;

public enum ApprovalStatusEnum {
    REQUESTED("Requested"),
    ACCEPTED("Accepted"),
    REFUSED("Refused");

    private final String properName;

    ApprovalStatusEnum(String name) {
        this.properName = name;
    }

    public String getDescription() {
        return properName;
    }

}
