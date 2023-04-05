package com.github.liuyueyi.quick.transfer.test.issues;

import com.github.liuyueyi.quick.transfer.ChineseUtils;

/**
 * @author YiHui
 * @date 2022/12/30
 */
public class Issue15Fix {

    public static void fix15() {
        String text = "标致=寶獅        溜=熘";
        System.out.println("origin:" + text);
        System.out.println("s2t:" + ChineseUtils.s2t(text));
        System.out.println("s2tw:" + ChineseUtils.s2tw(text));
        System.out.println("s2hk:" + ChineseUtils.s2hk(text));
        System.out.println("t2s:" + ChineseUtils.t2s(text));
    }

    public static void main(String[] args) {
        fix15();
    }

}
