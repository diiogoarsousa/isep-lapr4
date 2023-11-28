package ecourse.boardManagement.application;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.boardManagement.domain.Board;
import ecourse.boardManagement.domain.BoardParticipant;
import ecourse.boardManagement.domain.PostIt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardController {
    private final BoardService boardService = new BoardService();

    /**
     * Creates a new board.
     *
     * @param title      The title of the board.
     * @param owner      The owner of the board.
     * @param numRows    The number of rows of the board.
     * @param numColumns The number of columns of the board.
     * @return The created board.
     */
    public Board createBoard(String title, SystemUser owner, int numRows, int numColumns) {
        return boardService.createBoard(title, owner, numRows, numColumns, Collections.emptyList());
    }

    /**
     * Deletes a board.
     *
     * @param board The board to be deleted.
     */
    public void deleteBoard(Board board) {
        boardService.deleteBoard(board);
    }

    /**
     * Shares a board with a user.
     *
     * @param board    The board to be shared.
     * @param username The user to share the board with.
     * @return The shared board.
     */
    public Board shareBoard(Board board, String username) {
        return boardService.shareBoard(board, username, false);
    }

    /**
     * Removes a participant from a board.
     *
     * @param board               The board to remove the participant from.
     * @param participantUsername The username of the participant to be removed.
     */
    public void removeParticipant(Board board, String participantUsername) {
        boardService.removeParticipant(board, participantUsername);
    }

    /**
     * Toggles the read access of a participant.
     *
     * @param board               The board to toggle the read access.
     * @param participantUsername The username of the participant to toggle the read access.
     * @return The board with the toggled read access.
     */
    public Board toggleParticipantWriteAccess(Board board, String participantUsername) {
        return boardService.toggleParticipantWriteAccess(board, participantUsername);
    }

    /**
     * Archives a board.
     *
     * @param board The board to be archived.
     * @return The archived board.
     */
    public Board archiveBoard(Board board) {
        return boardService.archiveBoard(board);
    }

    /**
     * Unarchives a board.
     *
     * @param board The board to be unarchived.
     * @return The unarchived board.
     */
    public Board unarchiveBoard(Board board) {
        return boardService.unarchiveBoard(board);
    }

    /**
     * Finds a board by its id.
     *
     * @param id The id of the board.
     * @return The board with the given id.
     */
    public Board findBoardById(Long id) {
        return boardService.findBoardById(id);
    }

    /**
     * Finds all boards of an owner.
     *
     * @param user The owner of the boards.
     * @return A list of boards owned by the given user.
     */
    public List<Board> findBoardsByOwner(SystemUser user) {
        return boardService.findBoardsByOwner(user);
    }

    /**
     * Finds all system users.
     *
     * @return A list of all system users.
     */
    public List<SystemUser> findAllSystemUsers() {
        return boardService.findAllSystemUsers();
    }

    /**
     * Creates a new post-it.
     *
     * @param board   The board to add the post-it to.
     * @param title   The title of the post-it.
     * @param content The content of the post-it.
     * @param row     The row of the post-it.
     * @param column  The column of the post-it.
     * @return The created post-it.
     */
    public Board createPostIt(Board board, String title, String content, int row, int column) {
        return boardService.createPostIt(board, title, content, row, column);
    }

    /**
     * Changes a post-it content.
     *
     * @param board   The board of the post-it.
     * @param postit  The post-it to change the content.
     * @param content The new content of the post-it.
     * @return The board with the changed post-it.
     */
    public Board changePostIt(Board board, Long postit, String content) {
        return boardService.changePostIt(board, postit, content);
    }

    /**
     * Finds all boards that a user can edit.
     * A user can edit a board if he is the owner of the board
     * or if the board is shared with him
     * with write permissions.
     * The board must not be archived.
     * And the board must have free space to add a new post-it.
     *
     * @param user The user to find the boards that he can edit.
     * @return A list of boards that the user can edit.
     */
    public List<Board> findBoardsThatUserCanAddPostIt(SystemUser user) {
        List<Board> activeboards = boardService.findBoardsThatUserCanEdit(user);
        List<Board> boards = boardService.findAllBoards();
        boards.removeIf(board -> !activeboards.contains(board));

        //find boards that user is participant and can write
        List<Board> participantBoards = new ArrayList<>();
        for (Board board : boards) {
            for (BoardParticipant participant : board.getParticipantsWithWriteAccess()) {
                if (participant.id().getUser().equals(user)) {
                    participantBoards.add(board);
                    break;
                }
            }
        }
        boards.addAll(participantBoards);

        //Check if there is free space in the board to add a new post-it
        boards.removeIf(board -> board.getNumRows() * board.getNumColumns() == board.getPostIts().size());

        return boards;
    }

    /**
     * Finds all boards that a user can see.
     * A user can edit a board if he is the owner of the board
     * or if the board is shared with him.
     *
     * @param user The user to find the boards that he can see.
     * @return A list of boards that the user can see.
     */
    public List<Board> findBoardsThatUserCanSee(SystemUser user) {
        List<Board> boardsUserIsParticipant = boardService.findBoardsThatUserCanEdit(user);
        List<Board> boards = boardService.findAllBoards();
        boards.removeIf(board -> !boardsUserIsParticipant.contains(board));

        return boards;
    }


    /**
     * Finds all boards.
     *
     * @return A list of all boards.
     */
    public List<Board> findAllBoards() {
        return boardService.findAllBoards();
    }

    /**
     * Lists all participants of a board.
     *
     * @param board The board to list the participants.
     * @return A list of all participants of the board.
     */
    public List<BoardParticipant> findParticipantsByBoard(Board board) {
        return boardService.findParticipantsByBoard(board);
    }
}
