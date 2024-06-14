package com.example.onlinesongslibrary.service.impl;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.model.Artist.exceptions.ArtistNotValidException;
import com.example.onlinesongslibrary.model.Song.Song;
import com.example.onlinesongslibrary.repository.ArtistRepository;
import com.example.onlinesongslibrary.service.ArtistService;
import com.example.onlinesongslibrary.web.dto.SongRequestDto;
import com.example.onlinesongslibrary.web.dto.SpecificArtistResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Override
    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist getArtistById(Long artistId) {
        return artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotValidException(artistId));
    }

    @Override
    public List<Artist> filterArtistByNationalityAndDateOfBirth(String nationality, Date date) {
        return artistRepository.findAllByNationalityAndDateOfBirthBefore(nationality, date);
    }

}