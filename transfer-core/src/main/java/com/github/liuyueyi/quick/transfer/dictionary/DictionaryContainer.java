package com.github.liuyueyi.quick.transfer.dictionary;

import com.github.liuyueyi.quick.transfer.constants.TransType;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yihui
 * @date 20/11/23
 */
public class DictionaryContainer {
    private static volatile DictionaryContainer instance;

    private final Map<String, BasicDictionary> dictionaryMap = new HashMap<>(8, 1);

    private Map<String, Set<String>> selfExcludeWords;

    private DictionaryContainer() {
        selfExcludeWords = new HashMap<>(16);
    }

    public static DictionaryContainer getInstance() {
        if (instance == null) {
            synchronized (DictionaryContainer.class) {
                if (instance == null) {
                    instance = new DictionaryContainer();
                }
            }
        }
        return instance;
    }

    /**
     * @param key
     * @return
     * @see DictionaryContainer#getDictionary(TransType)
     */
    @Deprecated
    public BasicDictionary getDictionary(String key) {
        return getDictionary(TransType.typeOf(key));
    }

    /**
     * 获取词典
     *
     * @param transType
     * @return
     */
    public BasicDictionary getDictionary(TransType transType) {
        BasicDictionary dictionary = dictionaryMap.get(transType.getType());
        if (dictionary != null) {
            return dictionary;
        }

        synchronized (this) {
            dictionary = dictionaryMap.get(transType.getType());
            if (dictionary != null) {
                return dictionary;
            }
            switch (transType) {
                case SIMPLE_TO_TRADITIONAL:
                    dictionary = DictionaryFactory.loadDictionary("tc/s2t.txt", false);
                    break;
                case SIMPLE_TO_HONGKONG:
                    dictionary = DictionaryFactory.loadSecondDictionary(getDictionary(TransType.SIMPLE_TO_TRADITIONAL), "tc/t2hk.txt", false);
                    break;
                case SIMPLE_TO_TAIWAN:
                    dictionary = DictionaryFactory.loadSecondDictionary(getDictionary(TransType.SIMPLE_TO_TRADITIONAL), "tc/t2tw.txt", false);
                    break;
                case TRADITIONAL_TO_SIMPLE:
                    dictionary = DictionaryFactory.loadDictionary("tc/t2s.txt", false);
                    break;
                case HONGKONG_TO_SIMPLE:
                    dictionary = DictionaryFactory.loadSecondDictionary(getDictionary(TransType.TRADITIONAL_TO_SIMPLE), "tc/t2hk.txt", true);
                    break;
                case TAIWAN_TO_SIMPLE:
                    dictionary = DictionaryFactory.loadSecondDictionary(getDictionary(TransType.TRADITIONAL_TO_SIMPLE), "tc/t2tw.txt", true);
                    break;
                default:
                    throw new IllegalArgumentException("暂不支持转化方式" + transType);
            }
            dictionaryMap.put(transType.getType(), dictionary);
        }

        // 移除自定义的排除限定词
        doLoadGlobalSelfExcludeWords(selfExcludeWords.get("g"));
        doLoadSelfExcludeWords(transType, selfExcludeWords.get(transType.getType()));
        return dictionary;
    }

    /**
     * 卸载字典，减少内存占用开销
     *
     * @return
     */
    public void unloadDictionary(TransType transType) {
        dictionaryMap.remove(transType.getType());
    }


    /**
     * 加载排除词典
     * 此方法用于将特定转换类型下不希望出现的词汇添加到一个排除集合中
     * 在之后的转换过程中，这些词汇将被忽略或排除
     *
     * @param transType 转换类型，用于确定词典的用途如果为null，则使用通用类型"g"
     * @param dict 要添加到排除词典中的词汇集合
     */
    public void loadExcludeDict(TransType transType, Collection<String> dict) {
        // 确定排除词典的键值如果transType为null，则使用通用类型"g"
        String key = transType == null ? "g" : transType.getType();

        // 获取或初始化指定键值的排除词集合
        Set<String> old = selfExcludeWords.computeIfAbsent(key, k -> new HashSet<>());
        // 将新的排除词汇添加到集合中
        old.addAll(dict);

        // 执行加载操作
        if (transType == null) {
            doLoadGlobalSelfExcludeWords(dict);
        } else {
            doLoadSelfExcludeWords(transType, dict);
        }
    }

    /**
     * 加载全局自定义排除词汇
     * 此方法用于将一组词汇标记为全局排除词汇，并确保这些词汇不在任何基本词典中被使用
     *
     * @param selfExcludeWords 一个包含自定义排除词汇的集合
     */
    private void doLoadGlobalSelfExcludeWords(Collection<String> selfExcludeWords) {
        if (selfExcludeWords == null || selfExcludeWords.isEmpty()) {
            return;
        }
        // 遍历自定义排除词汇集合
        for (String word : selfExcludeWords) {
            // 遍历所有基本词典，并从每个词典中移除当前排除词汇
            for (BasicDictionary dictionary : dictionaryMap.values()) {
                dictionary.remove(word);
            }
        }
    }

    /**
     * 加载自定义排除词库
     * <p>
     * 此方法用于将特定转换类型下的自定义排除词库加载到系统中它首先将转换类型与排除词库关联起来，
     * 然后从相应的基础词典中移除这些词以确保这些词在转换过程中被排除
     *
     * @param transType        转换类型，表示需要应用自定义排除词库的场景
     * @param selfExcludeWords 自定义排除词集合，包含不应在转换过程中使用的词汇
     */
    private void doLoadSelfExcludeWords(TransType transType, Collection<String> selfExcludeWords) {
        if (selfExcludeWords == null || selfExcludeWords.isEmpty()) {
            return;
        }

        // 获取与转换类型相关联的基础词典，如果没有找到相关联的基础词典，则不执行任何操作
        BasicDictionary dictionary = dictionaryMap.get(transType.getType());
        if (dictionary == null) {
            return;
        }

        // 遍历自定义排除词库，将每个词从基础词典中移除
        for (String word : selfExcludeWords) {
            dictionary.remove(word);
        }
    }
}
