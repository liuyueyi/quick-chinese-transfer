package com.github.liuyueyi.quick.transfer.dictionary;

import com.github.liuyueyi.quick.transfer.Trie;

import java.util.Map;

/**
 * 两阶段转换
 * <p>
 * 如简体 -> 繁体 -> 香港繁体
 *
 * @author yihui
 * @date 20/11/23
 */
public class SecondParserDictionary extends BasicDictionary {

    private BasicDictionary parentDictionary;

    public SecondParserDictionary(String name, BasicDictionary parentDictionary, Map<Character, Character> chars, Trie<String> dict, int maxLen) {
        super(name, chars, dict, maxLen);
        this.parentDictionary = parentDictionary;
    }

    @Override
    public String convert(String str) {
        // 从子节点往上递归转换
        str = super.convert(str);
        return parentDictionary.convert(str);
    }
}
