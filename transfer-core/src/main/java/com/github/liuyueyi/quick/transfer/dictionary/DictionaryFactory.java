package com.github.liuyueyi.quick.transfer.dictionary;

import com.github.liuyueyi.quick.transfer.Trie;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yihui
 * @date 20/11/23
 */
public class DictionaryFactory {
    public static final String EMPTY = "";
    public static final String SHARP = "#";
    public static final String EQUAL = "=";

    /**
     * 一个简单的字符串分割，避免直接使用String#split的正则
     *
     * @param content
     * @param split
     * @return
     */
    private static String[] split(String content, String split) {
        int index = content.indexOf(split);
        if (index < 0) {
            return new String[]{content};
        } else {
            return new String[]{content.substring(0, index), content.substring(index + 1)};
        }
    }

    public static BasicDictionary loadDictionary(String mappingFile, boolean reverse) {
        Map<Character, Character> charMap = new HashMap<>(8192);
        Trie<String> dict = new Trie<>();
        int maxLen = 2;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    new BufferedInputStream(Objects.requireNonNull(DictionaryFactory.class.getClassLoader().getResourceAsStream(mappingFile))), StandardCharsets.UTF_8));

            String line = null;
            String[] pair;
            while (null != (line = in.readLine())) {
                if (line.length() == 0 || line.startsWith(SHARP)) {
                    // 空行和注释直接扔掉
                    continue;
                }
                pair = split(line, EQUAL);

                if (pair.length < 2) {
                    continue;
                }

                if (reverse) {
                    if (pair[0].length() == 1 && pair[1].length() == 1) {
                        charMap.put(pair[1].charAt(0), pair[0].charAt(0));
                    } else {
                        maxLen = Math.max(pair[0].length(), maxLen);
                        dict.add(pair[1], pair[0]);
                    }
                } else {
                    if (pair[0].length() == 1 && pair[1].length() == 1) {
                        charMap.put(pair[0].charAt(0), pair[1].charAt(0));
                    } else {
                        maxLen = Math.max(pair[0].length(), maxLen);
                        dict.add(pair[0], pair[1]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new BasicDictionary(mappingFile, charMap, dict, maxLen);
    }

    public static SecondParserDictionary loadSecondDictionary(BasicDictionary parent, String mappingFile, boolean reverse) {
        BasicDictionary current = loadDictionary(mappingFile, reverse);
        return new SecondParserDictionary(mappingFile, parent, current.getChars(), current.getDict(), current.getMaxLen());
    }
}
