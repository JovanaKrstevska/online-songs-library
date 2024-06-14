package com.example.onlinesongslibrary.model.Artist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class ArtistNotValidException extends RuntimeException{
    public ArtistNotValidException(Long id){
        super(String.format("Not valid artist"));
    }
}
