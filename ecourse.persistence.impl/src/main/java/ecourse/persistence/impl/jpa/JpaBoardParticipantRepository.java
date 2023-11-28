package ecourse.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.boardManagement.domain.Board;
import ecourse.boardManagement.domain.BoardParticipant;
import ecourse.boardManagement.domain.BoardParticipantId;
import ecourse.boardManagement.repositories.BoardParticipantRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class JpaBoardParticipantRepository extends JpaAutoTxRepository<BoardParticipant, BoardParticipantId, BoardParticipantId>
        implements BoardParticipantRepository {

    public JpaBoardParticipantRepository(final TransactionalContext autoTx) {
        super(autoTx, "boardParticipantId");
    }


    public JpaBoardParticipantRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "boardParticipantId");
    }


    @Override
    public List<BoardParticipant> findParticipantsByBoard(Board board) {
        TypedQuery<BoardParticipant> query = entityManager().createQuery(
                "SELECT bp FROM BoardParticipant bp WHERE bp.id.board = :board", BoardParticipant.class);
        query.setParameter("board", board);
        return query.getResultList();
    }
}
