package com.ricky.marvelapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterDataContainer {

	private List<MarvelCharacter> results;
    public long count;
    public long total;
}