package com.example.onlinesongslibrary.model.Song;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.model.enumerations.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Float duration;
    private Date releaseDate;
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id")
    private Artist artist;


    public Song(String title, Float duration, Date releaseDate, Genre genre) {
        this.title = title;
        this.duration = duration;
        this.releaseDate =releaseDate;
        this.genre = genre;
    }
}
