package ecourse.boardManagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.DTOable;
import ecourse.boardManagement.dto.BoardDTO;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOARD")
public class Board implements AggregateRoot<Long>, DTOable<BoardDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private SystemUser owner;

    @OneToMany(mappedBy = "boardParticipantId.board", cascade = CascadeType.ALL)
    @Lazy
    private List<BoardParticipant> participants;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private BoardStatusEnum status;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardState> stateStack;

    /**
     * Creates a new board with the given title, owner, number of rows, number of columns, and participants.
     *
     * @param title        the title of the board
     * @param owner        the owner of the board
     * @param numRows      the number of rows on the board
     * @param numColumns   the number of columns on the board
     * @param participants the participants of the board
     */
    public Board(String title, SystemUser owner, int numRows, int numColumns, List<BoardParticipant> participants) {
        this.title = title;
        this.owner = owner;
        this.participants = participants;
        this.status = BoardStatusEnum.ACTIVE;
        this.stateStack = new ArrayList<>();
        this.stateStack.add(new BoardState(this, numRows, numColumns, new ArrayList<>())); // initial state
    }

    protected Board() {
        // for ORM only
    }

    /**
     * Gets the state stack.
     *
     * @return the state stack.
     */
    public List<BoardState> getStateStack() {
        return stateStack;
    }

    /**
     * Adds a new board state to the state stack.
     */
    public BoardState newBoardState() {
        BoardState newState = new BoardState(this, this.getNumRows(), this.getNumColumns(), this.getPostIts());
        this.stateStack.add(newState);
        return newState;
    }

    /**
     * Archives the board.
     */
    public void archive() {
        this.status = BoardStatusEnum.ARCHIVED;
    }

    /**
     * Unarchives the board.
     */
    public void unarchive() {
        this.status = BoardStatusEnum.ACTIVE;
    }

    /**
     * Adds a participant to the board.
     *
     * @param participant the participant to add.
     */
    public void addParticipant(BoardParticipant participant) {
        this.participants.add(participant);
    }

    /**
     * Removes a participant from the board.
     *
     * @param participant the participant to remove.
     */
    public void removeParticipant(BoardParticipant participant) {
        this.participants.remove(participant);
    }

    /**
     * Goes n states back from the state stack.
     *
     * @param index the number of states to go back.
     * @return the state n states back.
     */
    public BoardState getNPastState(int index) {
        if (index < 0 || index >= this.stateStack.size()) {
            return null;
        }
        return this.stateStack.get(stateStack.size() - 1 - index);
    }

    /**
     * Gets the current state of the board.
     *
     * @return the current state of the board.
     */
    public BoardState getCurrentState() {
        return this.stateStack.get(stateStack.size() - 1);
    }

    /**
     * Gets the title of the board.
     *
     * @return the title of the board.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the owner of the board.
     *
     * @return the owner of the board.
     */
    public SystemUser getOwner() {
        return owner;
    }

    /**
     * Gets the list of participants of the board.
     *
     * @return the list of participants of the board.
     */
    public List<BoardParticipant> getParticipants() {
        return participants;
    }


    /**
     * Gets the list of participants of the board with write access.
     *
     * @return the list of participants of the board with write access.
     */
    public List<BoardParticipant> getParticipantsWithWriteAccess() {
        List<BoardParticipant> participantsWithWriteAccess = new ArrayList<>();
        for (BoardParticipant participant : participants) {
            if (participant.canWrite()) {
                participantsWithWriteAccess.add(participant);
            }
        }
        return participantsWithWriteAccess;
    }

    public BoardStatusEnum getStatus() {
        return status;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return id;
    }

    public BoardDTO toDTO() {
        BoardDTO dto = new BoardDTO();
        dto.title = title;
        dto.owner = owner.username().toString();
        dto.state = status;
        return dto;
    }

    public List<PostIt> getPostIts() {
        return this.getCurrentState().getPostIts();
    }

    public int getNumRows() {
        return this.getCurrentState().getNumRows();
    }

    public int getNumColumns() {
        return this.getCurrentState().getNumColumns();
    }

    /**
     * Toggles the write access of a participant.
     *
     * @param participant the participant to toggle the write access.
     */
    public void toggleParticipantWriteAccess(BoardParticipant participant) {
        participant.toggleWriteAccess();

    }
}
