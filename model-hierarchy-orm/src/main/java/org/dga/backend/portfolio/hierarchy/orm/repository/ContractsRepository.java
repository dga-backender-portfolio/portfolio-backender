package org.dga.backend.portfolio.hierarchy.orm.repository;

import org.dga.backend.portfolio.hierarchy.orm.entity.core.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractsRepository extends JpaRepository<Contracts, Long> {
}
