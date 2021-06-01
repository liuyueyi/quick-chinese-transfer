package com.github.liuyueyi.quick.translate.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yihui
 * @date 2021/6/1
 */
public enum LanguageEnum {
    ARABIC("ar"),
    BULGARIAN("bg"),
    CATALAN("ca"),
    CHINESE("zh-CN"),
    SIMPLIFIED("zh-CHS"),
    CHINESE_TRADITIONAL("zh-CHT"),
    CZECH("cs"),
    DANISH("da"),
    DUTCH("nl"),
    ENGLISH("en"),
    ESTONIAN("et"),
    FINNISH("fi"),
    FRENCH("fr"),
    GERMAN("de"),
    GREEK("el"),
    HAITIAN_CREOLE("ht"),
    HEBREW("he"),
    HINDI("hi"),
    HMONG_DAW("mww"),
    HUNGARIAN("hu"),
    INDONESIAN("id"),
    ITALIAN("it"),
    JAPANESE("ja"),
    KLINGON("tlh"),
    KLINGON_PIQAD("tlh-Qaak"),
    KOREAN("ko"),
    LATVIAN("lv"),
    LITHUANIAN("lt"),
    MALAY("ms"),
    MALTESE("mt"),
    NORWEGIAN("no"),
    PERSIAN("fa"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    SLOVAK("sk"),
    SLOVENIAN("sl"),
    SPANISH("es"),
    SWEDISH("sv"),
    THAI("th"),
    TURKISH("tr"),
    UKRAINIAN("uk"),
    URDU("ur"),
    VIETNAMESE("vi"),
    WELSH("cy");

    private static Map<String, LanguageEnum> languageEnumMap;

    static {
        languageEnumMap = new HashMap<>(128);
        for (LanguageEnum l : values()) {
            languageEnumMap.put(l.name(), l);
        }
    }

    private String code;

    LanguageEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static LanguageEnum codeOf(String area) {
        return languageEnumMap.get(area.toUpperCase());
    }
}
