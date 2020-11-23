/**
 * File    : ChineseUtils.java
 * Created : 2014年1月16日
 * By      : luhuiguo
 */
package com.github.liuyueyi.quick.transfer;

import com.github.liuyueyi.quick.transfer.dictionary.DictionaryContainer;

/**
 * 中文相关工具类。
 *
 * @author luhuiguo
 */
public class ChineseUtils {
    /**
     * 简体转繁体
     *
     * @return
     */
    public static String s2t(String content) {
        return DictionaryContainer.getInstance().getDictionary("s2t").convert(content);
    }

    public static String s2hk(String content) {
        return DictionaryContainer.getInstance().getDictionary("s2hk").convert(content);
    }

    public static String s2tw(String content) {
        return DictionaryContainer.getInstance().getDictionary("s2tw").convert(content);
    }

    public static String t2s(String content) {
        return DictionaryContainer.getInstance().getDictionary("t2s").convert(content);
    }

    public static String hk2s(String content) {
        return DictionaryContainer.getInstance().getDictionary("hk2s").convert(content);
    }

    public static String tw2s(String content) {
        return DictionaryContainer.getInstance().getDictionary("tw2s").convert(content);
    }
}
