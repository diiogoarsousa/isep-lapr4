package ecourse.boardManagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Embeddable
public class BoardParticipantId implements ValueObject, Comparable<BoardParticipantId> {
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;

    public BoardParticipantId(Board board, SystemUser user) {
        this.board = board;
        this.user = user;
    }

    protected BoardParticipantId() {
        // For ORM only
    }

    public Board getBoard() {
        return board;
    }

    public SystemUser getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardParticipantId that = (BoardParticipantId) o;
        return Objects.equals(board, that.board) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, user);
    }

    @Override
    public int compareTo(BoardParticipantId boardParticipantId) {
        int boardComparison = this.board.compareTo(boardParticipantId.board.identity());
        if (boardComparison != 0) {
            return boardComparison;
        }
        return this.user.compareTo(boardParticipantId.user.username());
    }
}
