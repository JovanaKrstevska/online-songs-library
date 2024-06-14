package com.example.onlinesongslibrary.web.controller;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.model.Playlist.Playlist;
import com.example.onlinesongslibrary.model.Song.Song;
import com.example.onlinesongslibrary.service.ArtistService;
import com.example.onlinesongslibrary.service.PlaylistService;
import com.example.onlinesongslibrary.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylist() {
        List<Playlist> playlist = playlistService.getAllPlaylists();
        return ResponseEntity.ok(playlist);
    }
    @PostMapping("/create")
    public Playlist create(@RequestBody Playlist playlist) {
        List<Long> songIds = playlist.getSongList().stream().map(Song::getId).collect(Collectors.toList());
        return this.playlistService.createPlaylist(playlist, songIds);
    }
    @GetMapping("/by-artist")
    public List<Playlist> listOfPlaylistsByArtistName(@RequestParam String artisticName){
        return playlistService.getPlaylistsByArtistName(artisticName);
    }
    @GetMapping("/max-songs")
    public List<Playlist> getPublicSongs(){
        return this.playlistService.getPublicPlaylistsWithMaxSongs(3);
    }
    @GetMapping("/{playlistId}/total")
    public Double getTotalDuration(@PathVariable("playlistId") Long playlistId){
        return this.playlistService.calculateTotalDuration(playlistId);
    }
    @PostMapping("/{playlistId}/songs/{songId}")
    public Playlist addSongToTheSpecPlaylist(@PathVariable("playlistId") Long playlistId, @PathVariable("songId") Long songId){
        return this.playlistService.addSongToSpecPlaylist(playlistId, songId);
    }
    @DeleteMapping("/{playlistId}/delete")
    public void deleteCurrentPlaylist(@PathVariable("playlistId") Long playlistId){
        playlistService.deletePlaylist(playlistId);
    }
}
