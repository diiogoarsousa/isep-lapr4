package ecourse.boardManagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOARD_STATE")
public class BoardState implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_STATE_ID")
    private Long stateId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private int numRows;
    private int numColumns;

    @OneToMany(mappedBy = "boardState", cascade = CascadeType.ALL)
    @Lazy
    private List<PostIt> postIts;

    /**
     * Creates a new board state with the given number of rows and columns.
     *
     * @param board      the board that this board state belongs to.
     * @param numRows    the number of rows on the board
     * @param numColumns the number of columns on the board
     */
    public BoardState(Board board, int numRows, int numColumns, List<PostIt> postIts) {
        this.board = board;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.postIts = new ArrayList<>();
        for (PostIt postIt : postIts) {
            this.postIts.add(new PostIt(postIt, this));
        }
    }

    /**
     * Creates a new board state that is a copy of the given board state.
     *
     * @param other the board state to copy
     */
    public BoardState(BoardState other) {
        this.board = other.board;
        this.numRows = other.numRows;
        this.numColumns = other.numColumns;
        // copy post-its
        this.postIts = new ArrayList<>();
        for (PostIt postIt : other.postIts) {
            this.postIts.add(new PostIt(postIt, this));
        }
    }

    protected BoardState() {
        // for ORM only
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    /**
     * Returns a list of all post-it notes on the board state.
     *
     * @return a list of all post-it notes on the board state
     */
    public List<PostIt> getPostIts() {
        return postIts;
    }

    public void addPostIt(PostIt postIt) {
        postIts.add(postIt);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof BoardState)) {
            return false;
        }

        if (numRows != ((BoardState) other).numRows) {
            return false;
        }

        if (numColumns != ((BoardState) other).numColumns) {
            return false;
        }

        return postIts.equals(((BoardState) other).postIts);
    }

    @Override
    public Long identity() {
        return null;
    }

}