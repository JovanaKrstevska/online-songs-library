package com.example.onlinesongslibrary.model.Artist;

import com.example.onlinesongslibrary.model.Song.Song;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String artisticName;
    private Date dateOfBirth;
    private String nationality;

    @OneToMany
    @JoinTable(name = "artist_songs", joinColumns = { @JoinColumn(name = "artist_id") }, inverseJoinColumns = {
            @JoinColumn(name = "song_id") })
    private List<Song> songs;

    public Artist(String name, String artisticName, Date dateOfBirth, String nationality) {
        this.name = name;
        this.artisticName = artisticName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.songs = new ArrayList<>();
    }
}
