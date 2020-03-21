package com.ricky.marvelapi.util;

import com.ricky.marvelapi.exception.LanguageNotSupportedException;

import co.aurasphere.jyandex.Jyandex;
import co.aurasphere.jyandex.dto.Language;

public class Translator {

	private final Jyandex client;
	
	public Translator(Jyandex client) {
		this.client = client;
	}
	
	public String translate(String textToTranslate, String language) {
		if (textToTranslate.equals("")) {
			return "";
		} else if (!client.supportedLanguages().getSupportedLanguages().containsKey(language)) {
			throw new LanguageNotSupportedException("The language code provided [" + language + "] is not supported.");
		}
		return client.translateText(textToTranslate, Language.ENGLISH, language).getTranslatedText()[0];
	}
}
