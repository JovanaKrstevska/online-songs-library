package com.example.onlinesongslibrary.service;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.web.dto.SongRequestDto;
import com.example.onlinesongslibrary.web.dto.SpecificArtistResponseDto;

import java.util.Date;
import java.util.List;

public interface ArtistService {
    Artist addArtist(Artist artist);
    List<Artist> getAllArtists();
    Artist getArtistById(Long artistId);
    List<Artist> filterArtistByNationalityAndDateOfBirth(String nationality, Date date);
}

