package com.edgarchirivella.simpleissuetracker.repositories;

import com.edgarchirivella.simpleissuetracker.domain.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
