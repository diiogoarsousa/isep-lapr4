package ecourse.boardManagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BOARD_PARTICIPANT")
public class BoardParticipant implements AggregateRoot<BoardParticipantId> {

    @EmbeddedId
    private BoardParticipantId boardParticipantId;

    @Column(name = "CAN_WRITE")
    private boolean canWrite;

    public BoardParticipant(BoardParticipantId boardParticipantId, boolean canWrite) {
        this.boardParticipantId = boardParticipantId;
        this.canWrite = canWrite;
    }

    // default constructor required by JPA
    protected BoardParticipant() {
    }

    public BoardParticipantId id() {
        return boardParticipantId;
    }

    public boolean canWrite() {
        return canWrite;
    }

    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof BoardParticipant)) {
            return false;
        }

        final BoardParticipant that = (BoardParticipant) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public BoardParticipantId identity() {
        return this.boardParticipantId;
    }

    /**
     * Toggles the write access of the participant.
     */
    public BoardParticipant toggleWriteAccess() {
        canWrite = !canWrite;
        return this;
    }
}