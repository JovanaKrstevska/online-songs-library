package com.example.onlinesongslibrary.repository;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.model.Playlist.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    @Query("SELECT DISTINCT p FROM Playlist p JOIN p.songList s WHERE s.artist = :artist")
    List<Playlist> findBySongsArtist(@Param("artist") Artist artist);

    @Query("SELECT p FROM Playlist p LEFT JOIN p.songList s WHERE p.status = :status GROUP BY p HAVING COUNT(s) <= :maxSongs")
    List<Playlist> findPublicPlaylistsWithMaxSongs(@Param("status") boolean status, @Param("maxSongs") int maxSongs);


}
