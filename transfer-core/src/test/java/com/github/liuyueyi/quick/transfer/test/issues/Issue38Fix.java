package com.github.liuyueyi.quick.transfer.test.issues;

import com.github.liuyueyi.quick.transfer.ChineseUtils;

/**
 * @author YiHui
 * @date 2022/12/30
 */
public class Issue38Fix {

    public static void fix38() {
        String text = "繁转简：多士- >吐司  许多士卒->许吐司卒   数位->数码   数位将军->数码将军 ";
        System.out.println("origin:" + text);
        System.out.println("s2t:" + ChineseUtils.s2t(text));
        System.out.println("s2tw:" + ChineseUtils.s2tw(text));
        System.out.println("s2hk:" + ChineseUtils.s2hk(text));
        System.out.println("t2s:" + ChineseUtils.t2s(text));
    }

    public static void main(String[] args) {
        fix38();
    }

}
