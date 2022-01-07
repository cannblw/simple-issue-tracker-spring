package com.edgarchirivella.simpleissuetracker.repositories;

import com.edgarchirivella.simpleissuetracker.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
}
