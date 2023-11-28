package ecourse.boardManagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.boardManagement.domain.Board;

import java.util.List;

public interface BoardRepository extends DomainRepository<Long, Board> {

    /**
     * Finds all boards.
     *
     * @return an iterable of all boards.
     */
    Iterable<Board> findAll();

    /**
     * Finds owned boards by a user.
     *
     * @param user The user to find the boards that he owns.
     * @return A list of boards that the user owns.
     */
    List<Board> findBoardsByOwner(SystemUser user);

    /**
     * Finds all boards that a user can edit.
     * A user can edit a board if he is the owner of the board
     * or if the board is shared with him
     * with write permissions.
     * The board must not be archived.
     *
     * @param user The user to find the boards that he can edit.
     * @return A list of boards that the user can edit.
     */
    List<Board> findActiveBoardsByOwner(SystemUser user);
}
