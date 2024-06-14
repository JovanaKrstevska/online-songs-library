package com.example.onlinesongslibrary.web.controller;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.model.Song.Song;
import com.example.onlinesongslibrary.service.ArtistService;
import com.example.onlinesongslibrary.web.dto.ArtistCreationRequestDto;
import com.example.onlinesongslibrary.web.dto.ArtistResponseDto;
import com.example.onlinesongslibrary.web.dto.SongRequestDto;
import com.example.onlinesongslibrary.web.dto.SpecificArtistResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtist(){
        List<Artist> artists = artistService.getAllArtists();
        return ResponseEntity.ok(artists);
    }

    @PostMapping("/create")
    public Artist create(@RequestBody ArtistCreationRequestDto artistCreationRequestDto){
        return this.artistService.addArtist(new Artist(artistCreationRequestDto.getName(), artistCreationRequestDto.getArtisticName(), artistCreationRequestDto.getDateOfBirth(), artistCreationRequestDto.getNationality()));
    }

    @GetMapping("/search")
    public List<ArtistResponseDto> getArtistByNationalityAndDate(@RequestParam String nationality,  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfBirth){
        List<Artist> artists = artistService.filterArtistByNationalityAndDateOfBirth(nationality, dateOfBirth);
        return artists.stream().map(artist -> new ArtistResponseDto(artist.getName(), artist.getArtisticName())).collect(Collectors.toList());
    }

    @GetMapping("/{artistId}")
    public SpecificArtistResponseDto getArtistBySortedSongs(@PathVariable Long artistId){
        Artist artist = artistService.getArtistById(artistId);
        List<SongRequestDto> sortedSongs = artist.getSongs().stream().sorted(Comparator.comparing(Song::getTitle).reversed()).map(song -> new SongRequestDto(song.getTitle(), song.getReleaseDate())).collect(Collectors.toList());
        return new SpecificArtistResponseDto(artist.getName(), artist.getArtisticName(), artist.getDateOfBirth(), artist.getNationality(), sortedSongs);
    }
}
