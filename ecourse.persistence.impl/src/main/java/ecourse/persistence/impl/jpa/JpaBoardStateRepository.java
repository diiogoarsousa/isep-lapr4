package ecourse.persistence.impl.jpa;

import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.boardManagement.domain.BoardState;
import ecourse.boardManagement.repositories.BoardStateRepository;

public class JpaBoardStateRepository extends JpaAutoTxRepository<BoardState, Long, Long>
        implements BoardStateRepository {

    public JpaBoardStateRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }
}
