package com.example.onlinesongslibrary.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecificArtistResponseDto {

    private String name;
    private String artisticName;
    private Date dateOfBirth;
    private String nationality;
    private List<SongRequestDto> songs;
}
