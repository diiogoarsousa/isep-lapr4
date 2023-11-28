package ecourse.persistence.impl.jpa;

import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
import ecourse.boardManagement.domain.PostIt;
import ecourse.boardManagement.repositories.PostItRepository;

public class JpaPostItRepository extends JpaAutoTxRepository<PostIt, Long, Long>
        implements PostItRepository {

    public JpaPostItRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }
}
