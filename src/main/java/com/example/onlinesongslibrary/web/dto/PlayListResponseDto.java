package com.example.onlinesongslibrary.web.dto;

import com.example.onlinesongslibrary.model.Song.Song;
import jakarta.persistence.ManyToMany;
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
public class PlayListResponseDto {

    private String playlistName;
    private Date dateOfCreation;
    private boolean status;

    @ManyToMany
    private List<Song> songList;
}
