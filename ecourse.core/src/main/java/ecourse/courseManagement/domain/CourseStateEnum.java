package ecourse.courseManagement.domain;

public enum CourseStateEnum {
    CLOSE("Event Create"),
    OPEN("Event Open"),
    ENROLL("Event Open Enrollments"),
    IN_PROGRESS("Event Close Enrollments"),
    CLOSED("Event Close");

    private final String properName;

    CourseStateEnum(String name) {
        this.properName = name;
    }

    public String getDescription() {
        return properName;
    }
}