package ecourse.teacherAssignmentManagement.exceptions;

public class NoTeachersRegisteredException  extends RuntimeException{

    public NoTeachersRegisteredException() {
        super("No registered teachers found.");
    }
}