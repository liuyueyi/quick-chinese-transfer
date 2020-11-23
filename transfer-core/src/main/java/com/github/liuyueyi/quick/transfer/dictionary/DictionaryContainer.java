package com.github.liuyueyi.quick.transfer.dictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yihui
 * @date 20/11/23
 */
public class DictionaryContainer {
    private static DictionaryContainer instance;

    private Map<String, BasicDictionary> dictionaryMap = new HashMap<>(8, 1);

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

    public BasicDictionary getDictionary(String key) {
        BasicDictionary dictionary = dictionaryMap.get(key);
        if (dictionary != null) {
            return dictionary;
        }

        synchronized (this) {
            dictionary = dictionaryMap.get(key);
            if (dictionary != null) {
                return dictionary;
            }
            switch (key) {
                case "s2t":
                    dictionary = DictionaryFactory.loadDictionary("tc/s2t.txt", false);
                    break;
                case "s2hk":
                    dictionary = DictionaryFactory.loadSecondDictionary(getDictionary("s2t"), "tc/t2hk.txt", false);
                    break;
                case "s2tw":
                    dictionary = DictionaryFactory.loadSecondDictionary(getDictionary("s2t"), "tc/t2tw.txt", false);
                    break;
                case "t2s":
                    dictionary = DictionaryFactory.loadDictionary("tc/t2s.txt", false);
                    break;
                case "hk2s":
                    dictionary = DictionaryFactory.loadSecondDictionary(getDictionary("t2s"), "tc/t2hk.txt", true);
                    break;
                case "tw2s":
                    dictionary = DictionaryFactory.loadSecondDictionary(getDictionary("t2s"), "tc/t2tw.txt", true);
                    break;
                default:
                    throw new IllegalArgumentException("暂不支持转化方式" + key);
            }
        }
        dictionaryMap.put(key, dictionary);
        return dictionary;
    }

}
