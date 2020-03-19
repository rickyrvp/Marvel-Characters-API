package com.ricky.marvelapi.service;

import com.ricky.marvelapi.model.CharacterDataWrapper;
import com.ricky.marvelapi.model.MarvelCharacter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.client.utils.URIBuilder;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Component
public class MarvelService {

	@Value("${marvel.url.base}")
	private String BASE;
	@Value("${marvel.url.characters.path}")
	private String CHARACTERS_PATH;
	@Value("${marvel.api.public.key}")
	private String PUBLIC_KEY;
	@Value("${marvel.api.private.key}")
	private String PRIVATE_KEY;
	
    private final RestTemplate restTemplate;
	
    @Autowired
    public MarvelService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
	
	@Cacheable(value = "charactersIdCache", key = "#root.methodName")
    public Set<Long> getAllCharacters() {
        Set<Long> marvelCharacterIDs = new HashSet<>();
        String timeStamp = Long.toString(System.currentTimeMillis());
        try {
            long offset = 0;
            // initialized larger than offset to ensure first ping
            long total = 1;
            while (offset < total) {
                URI uri = new URIBuilder(BASE + CHARACTERS_PATH)
                        .addParameter("limit", "100")
                        .addParameter("offset", Long.toString(offset))
                        .addParameter("apikey", PUBLIC_KEY)
                        .addParameter("ts", timeStamp)
                        .addParameter("hash", DigestUtils.md5Hex(timeStamp + PRIVATE_KEY + PUBLIC_KEY))
                        .build();
                
				CharacterDataWrapper characterDataWrapper = restTemplate.getForObject(uri.toString(), CharacterDataWrapper.class);

				total = characterDataWrapper.getData().getTotal();
				offset += characterDataWrapper.getData().getCount();
				characterDataWrapper.getData().getResults()
				        .forEach(character -> marvelCharacterIDs.add(character.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marvelCharacterIDs;
    }
	            

	public MarvelCharacter getCharacterById(long characterId) {
		String timeStamp = Long.toString(System.currentTimeMillis());
		try {
			URI uri = new URIBuilder(BASE + CHARACTERS_PATH + "/" + characterId)
			.addParameter("ts", timeStamp)
			.addParameter("apikey", PUBLIC_KEY)
			.addParameter("hash", DigestUtils.md5Hex(timeStamp + PRIVATE_KEY + PUBLIC_KEY))
			.build();
			
			CharacterDataWrapper characterDataWrapper = restTemplate.getForObject(uri.toString(), CharacterDataWrapper.class);
			MarvelCharacter marvelCharacter = characterDataWrapper.getData().getResults().get(0);
	        return marvelCharacter;
		} catch (URISyntaxException e) {
			throw new RuntimeException();
			}
	}


}
