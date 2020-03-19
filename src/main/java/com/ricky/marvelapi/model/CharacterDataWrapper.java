package com.ricky.marvelapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterDataWrapper {

	private CharacterDataContainer data;

}