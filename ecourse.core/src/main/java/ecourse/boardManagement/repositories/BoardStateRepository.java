package ecourse.boardManagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import ecourse.boardManagement.domain.BoardState;

public interface BoardStateRepository extends DomainRepository<Long, BoardState> {

    /**
     * Finds all board states.
     *
     * @return an iterable of all board states.
     */
    Iterable<BoardState> findAll();
}
