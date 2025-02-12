package com.github.liuyueyi.quick.transfer.dictionary;

import com.github.liuyueyi.quick.transfer.Trie;
import com.github.liuyueyi.quick.transfer.TrieNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * @author yihui
 */
public class BasicDictionary {
    protected String name;

    /**
     * chars
     */
    protected Map<Character, Character> charMap = null;

    /**
     * dict
     */
    protected Trie<String> dict = null;

    /**
     * maxLen
     */
    private int maxLen = 2;

    public Map<Character, Character> getChars() {
        return charMap;
    }

    public Trie<String> getDict() {
        return dict;
    }

    public int getMaxLen() {
        return maxLen;
    }

    public BasicDictionary(String name, Map<Character, Character> chars, Trie<String> dict, int maxLen) {
        this.name = name;
        this.charMap = chars;
        this.dict = dict;
        this.maxLen = maxLen;
    }

    public char convert(char ch) {
        Character tmp = charMap.get(ch);
        if (tmp == null) {
            return ch;
        }
        return tmp;
    }

    public void convert(Reader reader, Writer writer) throws IOException {
        PushbackReader in = new PushbackReader(new BufferedReader(reader), maxLen);

        char[] buf = new char[maxLen];

        int len = -1;
        while ((len = in.read(buf)) != -1) {
            TrieNode<String> node = dict.bestMatch(buf, 0, len);

            if (node != null) {
                int offset = node.getLevel();
                writer.write(node.getValue());
                in.unread(buf, offset, len - offset);
            } else {
                in.unread(buf, 0, len);
                char ch = (char) in.read();
                writer.write(convert(ch));
            }
        }

    }

    public String convert(String str) {
        String ret = str;
        Reader in = new StringReader(str);
        Writer out = new StringWriter();
        try {
            convert(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ret = out.toString();
        return ret;

    }


    public void remove(String key) {
        if (key.length() == 1) {
            charMap.remove(key.charAt(0));
        } else {
            // 添加过滤词，实际的效果就是自己不替换
            dict.add(key, key);
        }
    }

    public void add(String key, String value) {
        if (key.length() == 1 && value.length() == 1) {
            charMap.put(key.charAt(0), value.charAt(0));
        } else {
            dict.add(key, value);
        }
    }
}
