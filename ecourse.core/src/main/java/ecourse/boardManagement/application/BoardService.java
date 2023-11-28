package ecourse.boardManagement.application;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import ecourse.boardManagement.domain.Board;
import ecourse.boardManagement.domain.BoardParticipant;
import ecourse.boardManagement.domain.BoardParticipantId;
import ecourse.boardManagement.domain.PostIt;
import ecourse.boardManagement.repositories.BoardParticipantRepository;
import ecourse.boardManagement.repositories.BoardRepository;
import ecourse.boardManagement.repositories.BoardStateRepository;
import ecourse.boardManagement.repositories.PostItRepository;
import ecourse.infrastructure.persistence.PersistenceContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BoardService {
    private final BoardRepository boardRepository = PersistenceContext.repositories().boards();
    private final BoardStateRepository boardStateRepository = PersistenceContext.repositories().boardStates();
    private final PostItRepository postItRepository = PersistenceContext.repositories().postIts();
    private final BoardParticipantRepository boardParticipantRepository = PersistenceContext.repositories().boardParticipants();
    private final UserRepository userRepository = PersistenceContext.repositories().users();

    /**
     * Creates a new board.
     *
     * @param title        The title of the board.
     * @param owner        The owner of the board.
     * @param numRows      The number of rows of the board.
     * @param numColumns   The number of columns of the board.
     * @param participants The participants of the board.
     * @return The created board.
     */
    public Board createBoard(String title, SystemUser owner, int numRows, int numColumns, List<BoardParticipant> participants) {
        Board board;
        try {
            board = boardRepository.save(new Board(title, owner, numRows, numColumns, participants));
        } catch (Exception e) {
            System.out.println("Error creating board.");
            return null;
        }

        return board;
    }

    /**
     * Finds a board by its id.
     *
     * @param id The id of the board.
     * @return The board with the given id.
     */
    public Board findBoardById(Long id) {
        return boardRepository.ofIdentity(id).orElse(null);
    }

    /**
     * Archives a board.
     *
     * @param board The board to be archived.
     * @return The archived board.
     */
    public Board archiveBoard(Board board) {
        board.archive();
        try {
            board = boardRepository.save(board);
        } catch (Exception e) {
            System.out.println("Error archiving board.");
            return null;
        }

        return board;
    }

    /**
     * Finds owned boards by a user.
     *
     * @param user The user to find the boards that he owns.
     * @return A list of boards that the user owns.
     */
    public List<Board> findBoardsByOwner(SystemUser user) {
        return boardRepository.findBoardsByOwner(user);
    }

    /**
     * Finds active owned boards by a user.
     *
     * @param user The user to find the active boards that he owns.
     * @return A list of active boards that the user owns.
     */
    public List<Board> findBoardsThatUserCanEdit(SystemUser user) {
        return boardRepository.findActiveBoardsByOwner(user);
    }

    public List<SystemUser> findAllSystemUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
    }

    /**
     * Unarchives a board.
     *
     * @param board The board to be unarchived.
     * @return The unarchived board.
     */
    public Board unarchiveBoard(Board board) {
        board.unarchive();
        try {
            board = boardRepository.save(board);
        } catch (Exception e) {
            System.out.println("Error unarchiving board.");
            return null;
        }

        return board;
    }

    /**
     * Creates a post-it.
     *
     * @param board   The board to create the post-it.
     * @param title   The title of the post-it.
     * @param content The content of the post-it.
     * @param row     The row of the post-it.
     * @param column  The column of the post-it.
     * @return The board with the created post-it.
     */
    public Board createPostIt(Board board, String title, String content, int row, int column) {

        board.newBoardState();
        var brd = boardRepository.save(board);
        var bs = brd.getCurrentState();


        //check if coordinates fit in board
        if (row < 0 || row >= bs.getNumRows() || column < 0 || column >= bs.getNumColumns()) {
            System.out.println("\n### Error creating post-it. Coordinates out of bounds. ###");
            return null;
        }

        //check if row and column coordinates is empty
        if (bs.getPostIts()
                .stream()
                .anyMatch(p -> p.getRow() == row && p.getColumn() == column)) {
            System.out.println("\n### Error creating post-it. There is a post-it in the given coordinates. ###");
            return null;
        }
        try {
            var postIt = new PostIt(bs, title, content, row, column);
            postItRepository.save(postIt);
        } catch (Exception e) {
            System.out.println("Error creating post-it. Could not save board.");
            return null;
        }

        return brd;
    }

    /**
     * Changes a post-it content.
     *
     * @param board   The board that contains the post-it.
     * @param postIt  The post-it to be changed.
     * @param content The new content of the post-it.
     * @return The board with the changed post-it.
     */
    public Board changePostIt(Board board, Long postIt, String content) {
        Board brd;
        try {
            var boardPostIts = board.getPostIts();
            // check if a post-it with the given id exists
            var postItChanged = boardPostIts.stream()
                    .filter(p -> p.identity().equals(postIt))
                    .findFirst()
                    .orElse(null);

            if (postItChanged == null) {
                System.out.println("Post-it not found.");
                return null;
            }

            postItChanged.setContent(content);
            board.newBoardState();
            brd = boardRepository.save(board);
        } catch (Exception e) {
            System.out.println("Error changing post-it. Could not save board.");
            return null;
        }

        return brd;
    }

    /**
     * Finds all boards.
     *
     * @return A list of all boards.
     */
    public List<Board> findAllBoards() {
        return StreamSupport.stream(boardRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Shares a board with a user.
     *
     * @param board    The board to share.
     * @param username The user to share the board with.
     * @return The shared board.
     */
    public Board shareBoard(Board board, String username, boolean canWrite) {
        SystemUser user = findAllSystemUsers().stream().filter(u -> u.username().toString().equals(username)).findFirst().orElse(null);
        if (user == null) {
            System.out.println("User not found.");
            return null;
        }

        BoardParticipant participant = new BoardParticipant(new BoardParticipantId(board, user), canWrite);
        board.addParticipant(participant);
        try {
            board = boardRepository.save(board);
        } catch (Exception e) {
            System.out.println("Error sharing board.");
            return null;
        }

        return board;
    }

    public void removeParticipant(Board board, String participantUsername) {
        //getsystemuser
        var participant = board.getParticipants().stream().filter(p -> p.id().getUser().username().toString().equals(participantUsername)).findFirst().orElse(null);

        if (participant == null) {
            System.out.println("\n### Participant not found. ###");
            return;
        }

        try {
            boardParticipantRepository.remove(participant);
        } catch (Exception e) {
            System.out.println("\n### Error removing participant. ###");
        }
    }

    /**
     * Toggles the write access of a participant.
     *
     * @param board               The board to toggle the write access.
     * @param participantUsername The participant to toggle the write access.
     * @return The board with the toggled write access.
     */
    public Board toggleParticipantWriteAccess(Board board, String participantUsername) {
        //getsystemuser
        var participant = board.getParticipants().stream().filter(p -> p.id().getUser().username().toString().equals(participantUsername)).findFirst().orElse(null);

        if (participant == null) {
            System.out.println("\n### Participant not found. ###");
            return null;
        }
        participant.toggleWriteAccess();
        try {
            boardParticipantRepository.save(participant);
        } catch (Exception e) {
            System.out.println("\n### Error toggling participant write access. ###");
        }

        return board;
    }

    /**
     * Deletes a board.
     *
     * @param board The board to be deleted.
     */
    public void deleteBoard(Board board) {
        try {
            boardRepository.remove(board);
        } catch (Exception e) {
            System.out.println("\n### Error deleting board. ###");
        }
    }

    /**
     * Finds all participants of a board.
     *
     * @param board The board to find the participants.
     * @return A list of participants of the board.
     */
    public List<BoardParticipant> findParticipantsByBoard(Board board) {
        return boardParticipantRepository.findParticipantsByBoard(board);
    }
}
