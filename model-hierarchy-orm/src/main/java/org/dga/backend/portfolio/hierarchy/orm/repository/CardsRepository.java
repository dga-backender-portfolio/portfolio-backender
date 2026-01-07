package org.dga.backend.portfolio.hierarchy.orm.repository;

import org.dga.backend.portfolio.hierarchy.orm.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
}
