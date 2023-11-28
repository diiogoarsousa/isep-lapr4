package ecourse.boardManagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

/**
 * Represents a post-it note that can be added to a board.
 */
@Entity
@Table(name = "POSTIT")
public class PostIt implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTIT_ID")
    private Long postIt_id;

    @ManyToOne
    @JoinColumn(name = "BOARD_STATE_ID")
    private BoardState boardState;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ROW_NUMBER")
    private int row;

    @Column(name = "COLUMN_NUMBER")
    private int column;

    /**
     * Creates a new post-it note with the given description.
     *
     * @param content the content of the post-it note
     * @param row     the row of the post-it note
     * @param column  the column of the post-it note
     */
    public PostIt(BoardState boardState, String title, String content, int row, int column) {
        this.boardState = boardState;
        this.title = title;
        this.content = content;
        this.row = row;
        this.column = column;
    }

    //clone a postit
    public PostIt(PostIt other, BoardState newBoardState) {
        this.boardState = newBoardState;
        this.title = other.title;
        this.content = other.content;
        this.row = other.row;
        this.column = other.column;
    }

    protected PostIt() {
    }

    /**
     * Returns the description of the post-it note.
     *
     * @return the description of the post-it note
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the description of the post-it note.
     *
     * @param description the new description of the post-it note
     */
    public void setContent(String description) {
        this.content = description;
    }

    /**
     * Returns the row of the post-it note.
     *
     * @return the row of the post-it note.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of the post-it note.
     *
     * @return the column of the post-it note.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Get the post-it title.
     *
     * @return the post-it title.
     */
    public String getTitle() {
        return title;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof PostIt)) {
            return false;
        }

        if (((PostIt) other).postIt_id == null) {
            return false;
        }

        return this.postIt_id.equals(((PostIt) other).postIt_id);
    }

    @Override
    public Long identity() {
        return postIt_id;
    }

    public PostIt findPostItByTitle(String title) {
        if (this.title.equals(title)) {
            return this;
        }
        return null;
    }
}