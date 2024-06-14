package com.example.onlinesongslibrary.service;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.model.Song.Song;
import com.example.onlinesongslibrary.model.enumerations.Genre;

import java.util.List;

public interface SongService {
    Song addSongToArtist(Long artistId, Song song);
    Song getLongestSong(Long artistId, Genre genre);
    List<Song> findAllById(List<Long> songs);
    Song findSongBySpecPlaylist(Long songId);
    List<Song> getFirstThreeSongs();
}
