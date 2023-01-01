package com.github.liuyueyi.quick.transfer.test.issues;

import com.github.liuyueyi.quick.transfer.ChineseUtils;
import com.github.liuyueyi.quick.transfer.dictionary.DictionaryFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author YiHui
 * @date 2022/12/30
 */
public class Issue11Fix {

    public static void fix11() {
        String text = "签名 求签 庙签 亲娘 娘亲 伯娘 对象 物件";
        String ans = ChineseUtils.t2s(text);
        System.out.println(ans);
        System.out.println("s2t:" + ChineseUtils.s2t(text));
        System.out.println("s2tw:" + ChineseUtils.s2tw(text));
        System.out.println("s2hk:" + ChineseUtils.s2hk(text));
    }

    public static void main(String[] args) {
        fix11();
    }

}
