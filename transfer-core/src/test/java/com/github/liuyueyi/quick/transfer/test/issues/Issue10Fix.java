package com.github.liuyueyi.quick.transfer.test.issues;

import com.github.liuyueyi.quick.transfer.ChineseUtils;

/**
 * @author YiHui
 * @date 2022/8/11
 */
public class Issue10Fix {

    public static void fix9() {
        String text = "洛哈特=>洛哈特, 人鬼情未了=>第六感生死戀";
        System.out.println("origin:" + text);
        System.out.println("tw2s:" + ChineseUtils.tw2s(text));
        System.out.println("hk2s:" + ChineseUtils.hk2s(text));
        System.out.println("t2s:" + ChineseUtils.t2s(text));
        System.out.println("s2t:" + ChineseUtils.s2t(text));
        System.out.println("s2tw:" + ChineseUtils.s2tw(text));
        System.out.println("s2hk:" + ChineseUtils.s2hk(text));
    }

    public static void main(String[] args) {
        fix9();
    }
}
