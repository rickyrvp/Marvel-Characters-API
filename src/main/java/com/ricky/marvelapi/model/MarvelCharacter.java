package com.ricky.marvelapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelCharacter {

    private long id;
    private String name;
    private String description;
    private Thumbnail thumbnail;

}
