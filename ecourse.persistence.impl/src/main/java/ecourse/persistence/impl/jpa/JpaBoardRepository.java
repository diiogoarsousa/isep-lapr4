package ecourse.persistence.impl.jpa;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.boardManagement.domain.Board;
import ecourse.boardManagement.domain.BoardStatusEnum;
import ecourse.boardManagement.repositories.BoardRepository;

import java.util.List;

public class JpaBoardRepository extends JpaAutoTxRepository<Board, Long, Long>
        implements BoardRepository {

    public JpaBoardRepository(String persistenceUnitName, String identityFieldName) {
        super(persistenceUnitName, identityFieldName);
    }

    public JpaBoardRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public List<Board> findBoardsByOwner(SystemUser user) {
        return entityManager().createQuery("SELECT b FROM Board b WHERE b.owner = :user", Board.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Board> findActiveBoardsByOwner(SystemUser user) {
        return entityManager()
                .createQuery("SELECT b FROM Board b WHERE b.status = :active AND b.owner = :user", Board.class)
                .setParameter("user", user)
                .setParameter("active", BoardStatusEnum.ACTIVE)
                .getResultList();
    }
}
