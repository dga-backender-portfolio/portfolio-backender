package org.dga.backend.portfolio.hierarchy.orm.repository;

import org.dga.backend.portfolio.hierarchy.orm.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
}
