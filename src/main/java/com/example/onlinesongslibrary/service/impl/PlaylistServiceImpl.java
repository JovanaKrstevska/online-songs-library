package com.example.onlinesongslibrary.service.impl;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.model.Playlist.Playlist;
import com.example.onlinesongslibrary.model.Playlist.exceptions.PlayListNotValidException;
import com.example.onlinesongslibrary.model.Song.Song;
import com.example.onlinesongslibrary.repository.ArtistRepository;
import com.example.onlinesongslibrary.repository.PlaylistRepository;
import com.example.onlinesongslibrary.repository.SongRepository;
import com.example.onlinesongslibrary.service.ArtistService;
import com.example.onlinesongslibrary.service.PlaylistService;
import com.example.onlinesongslibrary.service.SongService;
import com.example.onlinesongslibrary.web.dto.PlayListResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongService songService;
    private final ArtistRepository artistRepository;
    @Override
    public Playlist createPlaylist(Playlist playlist, List<Long> songsId) {
        List<Song> songs = songService.findAllById(songsId);
        playlist.setSongList(songs);
        return playlistRepository.save(playlist);
    }
    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public List<Playlist> getPlaylistsByArtistName(String artistName) {
       Artist artist = artistRepository.findByName(artistName);
       List<Playlist> playlists = playlistRepository.findBySongsArtist(artist);
       List<Playlist> sortedSongs = playlists.stream().sorted(Comparator.comparing((Playlist p) -> p.getSongList().stream().map(Song::getArtist).map(Artist::getName).findFirst().orElse("")).thenComparing(p -> p.getSongList().stream().map(Song::getArtist).map(Artist::getDateOfBirth).findFirst().orElse(null))).collect(Collectors.toList());
       return playlistRepository.saveAll(sortedSongs);
    }

    @Override
    public List<Playlist> getPublicPlaylistsWithMaxSongs(Integer maxSongs) {
        boolean publicStatus = true;
        return playlistRepository.findPublicPlaylistsWithMaxSongs(publicStatus, maxSongs);
    }
    @Override
    public Double calculateTotalDuration(Long playlisId) {
        Playlist playlist = playlistRepository.findById(playlisId).orElseThrow(() -> new PlayListNotValidException(playlisId));
        return playlist.getSongList().stream().mapToDouble(Song::getDuration).sum();

    }
    @Override
    public Playlist addSongToSpecPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new PlayListNotValidException(playlistId));
        Song song = songService.findSongBySpecPlaylist(songId);
        playlist.getSongList().add(song);
        return playlist;
    }

    @Override
    public void deletePlaylist(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }
}
