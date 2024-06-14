package com.example.onlinesongslibrary.service;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.model.Playlist.Playlist;
import com.example.onlinesongslibrary.model.Song.Song;
import com.example.onlinesongslibrary.web.dto.PlayListResponseDto;

import java.util.List;

public interface PlaylistService {
    Playlist createPlaylist(Playlist playlist, List<Long> songsId);
    List<Playlist> getAllPlaylists();
    List<Playlist> getPlaylistsByArtistName(String artistName);
    List<Playlist> getPublicPlaylistsWithMaxSongs(Integer maxSongs);
    Double calculateTotalDuration(Long playlisId);
    Playlist addSongToSpecPlaylist(Long playlistId, Long songId);
    void deletePlaylist(Long playlistId);
}
