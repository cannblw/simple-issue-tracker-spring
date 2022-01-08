package com.edgarchirivella.simpleissuetracker.repositories;

import com.edgarchirivella.simpleissuetracker.domain.Story;
import com.edgarchirivella.simpleissuetracker.domain.StoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findByStatusOrderByPoints(StoryStatus status);
}
