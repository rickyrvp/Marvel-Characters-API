package com.ricky.marvelapi.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ricky.marvelapi.model.MarvelCharacter;
import com.ricky.marvelapi.service.MarvelService;

@RestController
public class MarvelController {

    @Autowired
    private MarvelService marvelService;

    //Serves an endpoint /characters that returns all the Marvel character ids
    @GetMapping("/characters")
    public Set<Long> getCharacters() {
    	return marvelService.getAllCharacters();
    }

    /** Serves an endpoint /characters/{characterId} with optional query param language.
     * Queries the real-time data from the Marvel API /v1/public/characters/{characterId} to return character information
     */
    @GetMapping("/characters/{characterId}")
    public MarvelCharacter getCharacterById(
    		@PathVariable long characterId, @RequestParam (defaultValue = "en") String language) {
		return marvelService.getCharacterById(characterId, language);
    }

}
