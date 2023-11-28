package ecourse.boardManagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import ecourse.boardManagement.domain.PostIt;

public interface PostItRepository extends DomainRepository<Long, PostIt> {

    /**
     * Finds all PostIts.
     *
     * @return an iterable of all PostIts.
     */
    Iterable<PostIt> findAll();
}
