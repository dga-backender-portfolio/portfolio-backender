package org.dga.backend.portfolio.hierarchy.orm.repository;

import org.dga.backend.portfolio.hierarchy.orm.entity.core.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonTptRepository extends JpaRepository<Person, Long> {
}
