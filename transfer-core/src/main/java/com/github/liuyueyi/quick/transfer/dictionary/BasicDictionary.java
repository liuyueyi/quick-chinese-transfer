package com.github.liuyueyi.quick.transfer.dictionary;

import com.github.liuyueyi.quick.transfer.Trie;
import com.github.liuyueyi.quick.transfer.TrieNode;

import java.io.*;
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

}
