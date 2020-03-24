package com.ricky.marvelapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;

@SpringBootTest
@AutoConfigureMockMvc
class MarvelControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getCharacters__returnsIDs() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

    @Test
    void getCharacter_nonexistentID_throwsException() throws Exception {
        int characterId = 0;
        mvc.perform(MockMvcRequestBuilders.get("/characters/" + characterId))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Character id [" + characterId + "]"));
    }

    @Test
    void getCharacter_existingID_returnsError() throws Exception {
        int characterId = 1009146;
        mvc.perform(MockMvcRequestBuilders.get("/characters/" + characterId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1009718))
                .andExpect(jsonPath("$.name").value("Wolverine"))
                .andExpect(jsonPath("$.description").value("Born with super-human senses and the power to heal from almost any wound, Wolverine was captured by a secret Canadian organization and given an unbreakable skeleton and claws. Treated like an animal, it took years for him to control himself. Now, he's a premiere member of both the X-Men and the Avengers."))
                .andExpect(jsonPath("$.thumbnail.path").value("http://i.annihil.us/u/prod/marvel/i/mg/2/60/537bcaef0f6cf"))
                .andReturn();
    }
}