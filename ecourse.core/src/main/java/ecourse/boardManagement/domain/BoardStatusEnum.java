package ecourse.boardManagement.domain;

/**
 * Represents the state of a board.
 * This enum was created because if we wanted
 * to add more states to the board, like deleted,
 * we would need to change the Board class.
 * <p>
 * As is right now, this could be only a boolean,
 * but we decided to make it an enum because it
 * could be useful in the future.
 */
public enum BoardStatusEnum {
    ACTIVE("Active"),
    ARCHIVED("Archived");

    private final String properName;

    BoardStatusEnum(String name) {
        this.properName = name;
    }

    public String getDescription() {
        return properName;
    }
}
