package com.edgarchirivella.simpleissuetracker.repositories;

import com.edgarchirivella.simpleissuetracker.domain.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
}
