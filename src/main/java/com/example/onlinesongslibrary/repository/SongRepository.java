package com.example.onlinesongslibrary.repository;

import com.example.onlinesongslibrary.model.Song.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>{
    List<Song> findByDurationBetweenOrderByDurationDesc(Double minDuration, Double macDuration);
}
