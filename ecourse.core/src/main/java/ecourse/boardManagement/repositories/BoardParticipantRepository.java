package ecourse.boardManagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import ecourse.boardManagement.domain.Board;
import ecourse.boardManagement.domain.BoardParticipant;
import ecourse.boardManagement.domain.BoardParticipantId;
import ecourse.boardManagement.domain.PostIt;

import java.util.List;

public interface BoardParticipantRepository extends DomainRepository<BoardParticipantId, BoardParticipant> {

    /**
     * Finds all Board Participants.
     *
     * @return an iterable of all Board Participants
     */
    Iterable<BoardParticipant> findAll();

    List<BoardParticipant> findParticipantsByBoard(Board board);
}
