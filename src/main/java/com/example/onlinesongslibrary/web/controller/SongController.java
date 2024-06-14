package com.example.onlinesongslibrary.web.controller;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.model.Song.Song;
import com.example.onlinesongslibrary.model.enumerations.Genre;
import com.example.onlinesongslibrary.service.ArtistService;
import com.example.onlinesongslibrary.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @PostMapping("/{id}/add")
    public Song addSong(@PathVariable("id") Long id, @RequestBody Song song){
        return this.songService.addSongToArtist(id, new Song(song.getTitle(), song.getDuration(), song.getReleaseDate(), song.getGenre()));
    }
    @GetMapping("/{artistId}/long-song")
    public Song longestSong(@PathVariable("artistId") Long artistId, @RequestParam Genre genre){
        return this.songService.getLongestSong(artistId, genre);
    }
    @GetMapping("/songs3")
    public List<Song> firstThreeSongs(){
        return this.songService.getFirstThreeSongs();
    }
}
