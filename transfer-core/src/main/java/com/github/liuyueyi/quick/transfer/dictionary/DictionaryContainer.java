package com.github.liuyueyi.quick.transfer.dictionary;

import com.github.liuyueyi.quick.transfer.constants.TransType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yihui
 * @date 20/11/23
 */
public class DictionaryContainer {
    private static volatile DictionaryContainer instance;

    private final Map<String, BasicDictionary> dictionaryMap = new HashMap<>(8, 1);

    private DictionaryContainer() {
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
}
