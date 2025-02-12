/**
 * File    : Trie.java
 * Created : 2014年1月20日
 * By      : luhuiguo
 */
package com.github.liuyueyi.quick.transfer;

/**
 * @author luhuiguo
 */
public class Trie<T> {

    private TrieNode<T> root = new TrieNode<T>(' ');

    public Trie() {
        super();
    }

    public void add(char[] w, T value) {

        if (w.length < 1) {
            return;
        }
        TrieNode<T> p = root;
        for (int i = 0; i < w.length; i++) {
            TrieNode<T> n = p.child(w[i]);
            if (n == null) {
                n = p.addChild(w[i]);
            }
            p = n;
        }
        p.setLeaf(true);
        p.setValue(value);

    }

    public void add(String w, T value) {
        if (null == w) {
            return;
        }
        add(w.toCharArray(), value);
    }

    public TrieNode<T> match(char[] sen, int offset, int len) {
        TrieNode<T> node = root;
        for (int i = 0; i < len; i++) {
            node = node.child(sen[offset + i]);
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    /**
     * 在Trie树中查找给定字符串的最佳匹配节点
     * <p>
     * 此方法从给定的偏移量开始，在Trie树中搜索与字符串片段最匹配的节点
     * 它主要用于文本搜索或自动完成等应用场景，其中sen是输入的字符数组，
     * offset是开始搜索的起始位置，len是搜索的长度限制
     *
     * @param sen    字符数组，代表待匹配的字符串
     * @param offset 搜索的起始位置，表示从数组的第几个元素开始
     * @param len    搜索的长度，表示最多匹配多少个字符
     * @return 返回最佳匹配的TrieNode节点，如果没有找到匹配项，则返回null
     */
    public TrieNode<T> bestMatch(char[] sen, int offset, int len) {
        // 初始化返回值为null，表示尚未找到匹配项
        TrieNode<T> ret = null;

        // 从Trie树的根节点开始搜索
        TrieNode<T> node = root;
        // 遍历字符串片段中的每个字符
        for (int i = offset; i < len; i++) {
            // 获取当前字符对应的子节点
            node = node.child(sen[i]);
            // 如果子节点存在
            if (node != null) {
                // 如果子节点是叶子节点，则表示找到一个完整匹配项
                if (node.isLeaf()) {
                    ret = node;
                }
            } else {
                // 如果子节点不存在，中断循环，表示搜索失败
                break;
            }
        }
        // 返回最佳匹配的节点，如果没有找到则返回null
        return ret;
    }

    public TrieNode<T> bestMatch(char[] sen, int offset) {
        return bestMatch(sen, offset, sen.length);
    }

}
