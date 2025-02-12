/**
 * File    : ChineseUtils.java
 * Created : 2014年1月16日
 * By      : luhuiguo
 */
package com.github.liuyueyi.quick.transfer;

import com.github.liuyueyi.quick.transfer.constants.TransType;
import com.github.liuyueyi.quick.transfer.dictionary.BasicDictionary;
import com.github.liuyueyi.quick.transfer.dictionary.DictionaryContainer;

import java.util.Collection;
import java.util.Map;

/**
 * 中文相关工具类。
 *
 * @author YiHui
 * @site https://github.com/liuyueyi/quick-chinese-transfer
 */
public class ChineseUtils {

    /**
     * 预热，加载字体词典，支持后台线程加载，避免阻塞主线程（适用于先加载，后使用的场景）
     *
     * @param async 表示异步预热
     * @param types 需要预热的词典类型
     */
    public static void preLoad(boolean async, TransType... types) {
        if (async) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    preLoad(types);
                }
            });
            thread.setDaemon(true);
            thread.start();
        } else {
            preLoad(types);
        }
    }

    /**
     * 预热，加载字体词典
     *
     * @param transType
     */
    public static void preLoad(TransType... transType) {
        for (TransType type : transType) {
            transfer("", type);
        }
    }

    /**
     * 卸载不用的词典
     *
     * @param transTypes
     */
    public static void unLoad(TransType... transTypes) {
        for (TransType type : transTypes) {
            DictionaryContainer.getInstance().unloadDictionary(type);
        }
    }

    /**
     * 为了解决转换库中异常的case，这里支持加载自定义的排除词
     *
     * @param type
     * @param dict
     */
    public static void loadExcludeDict(TransType type, Collection<String> dict) {
        DictionaryContainer.getInstance().loadExcludeDict(type, dict);
    }

    /**
     * 适用于全局的排除词典操作
     *
     * @param dict
     */
    public static void loadExcludeDict(Collection<String> dict) {
        DictionaryContainer.getInstance().loadExcludeDict(null, dict);
    }

    /**
     * 加载自定义的词典，优先级高于默认的词典
     *
     * @param type 转换类型
     * @param dict 自定义词典 key 为转换前，value 为转换后
     */
    public static void loadAdditionalDict(TransType type, Map<String, String> dict) {
        BasicDictionary dictionary = DictionaryContainer.getInstance().getDictionary(type);
        dict.forEach(dictionary::add);
    }

    /**
     * 通用简繁简繁转换接口
     *
     * @param content
     * @param type
     * @return
     */
    public static String transfer(String content, TransType type) {
        return DictionaryContainer.getInstance().getDictionary(type).convert(content);
    }

    /**
     * 简体转繁体
     *
     * @return
     */
    public static String s2t(String content) {
        return DictionaryContainer.getInstance().getDictionary(TransType.SIMPLE_TO_TRADITIONAL).convert(content);
    }

    /**
     * 简体转香港繁体
     *
     * @param content
     * @return
     */
    public static String s2hk(String content) {
        return DictionaryContainer.getInstance().getDictionary(TransType.SIMPLE_TO_HONGKONG).convert(content);
    }

    /**
     * 简体转台湾繁体
     *
     * @param content
     * @return
     */
    public static String s2tw(String content) {
        return DictionaryContainer.getInstance().getDictionary(TransType.SIMPLE_TO_TAIWAN).convert(content);
    }

    /**
     * 繁体转简体
     *
     * @param content
     * @return
     */
    public static String t2s(String content) {
        return DictionaryContainer.getInstance().getDictionary(TransType.TRADITIONAL_TO_SIMPLE).convert(content);
    }

    /**
     * 香港繁体转简体
     *
     * @param content
     * @return
     */
    public static String hk2s(String content) {
        return DictionaryContainer.getInstance().getDictionary(TransType.HONGKONG_TO_SIMPLE).convert(content);
    }

    /**
     * 台湾繁体转简体
     *
     * @param content
     * @return
     */
    public static String tw2s(String content) {
        return DictionaryContainer.getInstance().getDictionary(TransType.TAIWAN_TO_SIMPLE).convert(content);
    }
}
