package ecourse.courseClassManagement.exceptions;

public class ScheduleOverlapException extends RuntimeException{

    public ScheduleOverlapException() {
        super("The class overlaps with another class.");
    }
}
