package com.example.onlinesongslibrary.service.impl;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.model.Artist.exceptions.ArtistNotValidException;
import com.example.onlinesongslibrary.model.Song.Song;
import com.example.onlinesongslibrary.model.Song.exceptions.SongNotFoundException;
import com.example.onlinesongslibrary.model.enumerations.Genre;
import com.example.onlinesongslibrary.repository.ArtistRepository;
import com.example.onlinesongslibrary.repository.SongRepository;
import com.example.onlinesongslibrary.service.ArtistService;
import com.example.onlinesongslibrary.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ArtistService artistService;
    @Override
    public Song addSongToArtist(Long artistId, Song song) {
       Artist artist = this.artistService.getArtistById(artistId);
       List<Song> songs = artist.getSongs();
       songs.add(song);
       artist.setSongs(songs);
       return songRepository.save(song);
    }
    @Override
    public Song getLongestSong(Long artistId, Genre genre) {
        Artist artist = artistService.getArtistById(artistId);
        return artist.getSongs().stream().filter(song -> song.getGenre().equals(genre)).max(Comparator.comparingDouble(Song::getDuration)).orElseThrow(SongNotFoundException::new);
    }
    @Override
    public List<Song> findAllById(List<Long> songs) {
        return this.songRepository.findAllById(songs);
    }

    @Override
    public Song findSongBySpecPlaylist(Long songId) {
        return this.songRepository.findById(songId).orElseThrow(SongNotFoundException::new);
    }
    @Override
    public List<Song> getFirstThreeSongs() {
        List<Song> songs = songRepository.findByDurationBetweenOrderByDurationDesc(5.0, 10.0);
        int pageSize = 3;
        int startIndex = 0;
        int endIndex = Math.min(startIndex + pageSize, songs.size());
        return songs.subList(startIndex, endIndex);
    }

}
