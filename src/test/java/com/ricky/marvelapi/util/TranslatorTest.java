package com.ricky.marvelapi.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.aurasphere.jyandex.Jyandex;
import co.aurasphere.jyandex.dto.Language;
import co.aurasphere.jyandex.dto.SupportedLanguageResponse;
import co.aurasphere.jyandex.dto.TranslateTextResponse;
import com.ricky.marvelapi.exception.LanguageNotSupportedException;
import com.ricky.marvelapi.util.Translator;

@ExtendWith(MockitoExtension.class)
class TranslatorTest {

    private  static final String SUPPORTED_LANGUAGE = "supportedLanguage";
    private  static final String UNSUPPORTED_LANGUAGE = "unsupportedLanguage";

    @Mock
    private Jyandex client;
    @Mock
    private TranslateTextResponse translateTextResponse;
    @Mock
    private SupportedLanguageResponse supportedLanguageResponse;

    private HashMap<String, String> supportedLanguageMap = new HashMap<>();
    private Translator translator;

    @BeforeEach
    void setup() {
        supportedLanguageMap.put(SUPPORTED_LANGUAGE, SUPPORTED_LANGUAGE);
        translator = new Translator(client);

        when(client.supportedLanguages()).thenReturn(supportedLanguageResponse);
        when(supportedLanguageResponse.getSupportedLanguages()).thenReturn(supportedLanguageMap);
    }

    @Test
    void translate_supportedLanaguage_returnsSuccessfully() {
        String inputText = "hi";
        String translated = "I've been translated";

        when(client.translateText(inputText, Language.ENGLISH, SUPPORTED_LANGUAGE)).thenReturn(translateTextResponse);
        when(translateTextResponse.getTranslatedText()).thenReturn(new String[] {translated});

        assertEquals(translated, translator.translate(inputText, SUPPORTED_LANGUAGE));
    }

    @Test
    void translate_unsupportedLanguage_throwsException() {
        assertThrows(LanguageNotSupportedException.class, () -> translator.translate("test", UNSUPPORTED_LANGUAGE));
    }
}