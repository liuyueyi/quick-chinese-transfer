package com.github.liuyueyi.quick.transfer.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yihui
 * @date 2022/06/06
 */
public enum TransType {
    /**
     * 简体转繁体
     */
    SIMPLE_TO_TRADITIONAL("s2t"),
    /**
     * 简体转香港繁体
     */
    SIMPLE_TO_HONGKONG("s2hk"),
    /**
     * 简体转台湾繁体
     */
    SIMPLE_TO_TAIWAN("s2tw"),
    /**
     * 简体转台湾繁体（字形变体 + 台湾常用词汇，对应 OpenCC s2twp.json）
     */
    SIMPLE_TO_TAIWAN_PHRASE("s2twp"),
    /**
     * 繁体转简体
     */
    TRADITIONAL_TO_SIMPLE("t2s"),
    /**
     * 香港转简体
     */
    HONGKONG_TO_SIMPLE("hk2s"),

    /**
     * 台湾转简体
     */
    TAIWAN_TO_SIMPLE("tw2s"),

    /**
     * 台湾繁体转简体（台湾常用词汇，对应 OpenCC tw2sp.json）
     */
    TAIWAN_PHRASE_TO_SIMPLE("tw2sp");
    ;

    private final String type;

    TransType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    static Map<String, TransType> typeMap;

    static {
        typeMap = new HashMap<>(8, 1);
        for (TransType transType : values()) {
            typeMap.put(transType.getType(), transType);
        }
    }

    public static TransType typeOf(String type) {
        return typeMap.get(type);
    }
}
