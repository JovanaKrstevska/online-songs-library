package com.example.onlinesongslibrary.model.Song.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class SongNotFoundException extends RuntimeException{
    public SongNotFoundException(){
        super(String.format("Not song found"));
    }
}
