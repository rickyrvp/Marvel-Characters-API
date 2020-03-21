package com.ricky.marvelapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.ricky.marvelapi.util.Translator;

import co.aurasphere.jyandex.Jyandex;

@Configuration
@PropertySource("classpath:application.properties")
public class MarvelApiApplicationConfig {
	
	@Value("${yandex.api.key}")
	private String API_KEY;

    @Bean
    Translator getTranslator(Jyandex jyandex) {
        return new Translator(jyandex);
    }
    
    @Bean
    Jyandex getJyandex() {
        return new Jyandex(API_KEY);
    }
}
