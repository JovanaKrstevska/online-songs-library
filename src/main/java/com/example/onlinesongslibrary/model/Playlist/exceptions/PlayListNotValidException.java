package com.example.onlinesongslibrary.model.Playlist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class PlayListNotValidException extends RuntimeException{
    public PlayListNotValidException(Long playlistId){
        super(String.format("Not valid playlist"));
    }
}
