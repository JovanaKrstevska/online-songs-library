package com.example.onlinesongslibrary.model.Playlist;

import com.example.onlinesongslibrary.model.Song.Song;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playlistName;
    private Date dateOfCreation;
    private boolean status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songList;


    public Playlist(String playlistName, Date dateOfCreation, boolean status, List<Song> songList) {
        this.playlistName = playlistName;
        this.dateOfCreation = dateOfCreation;
        this.status = status;
        this.songList = songList;
    }
}
