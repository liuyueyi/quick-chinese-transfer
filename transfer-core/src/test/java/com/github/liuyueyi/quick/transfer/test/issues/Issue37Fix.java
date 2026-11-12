package com.github.liuyueyi.quick.transfer.test.issues;

import com.github.liuyueyi.quick.transfer.ChineseUtils;

/**
 * @author YiHui
 * @date 2022/12/30
 */
public class Issue37Fix {

    public static void fix37() {
        String text = "繁转简：新力->索尼  对象->物件";
        System.out.println("origin:" + text);
        System.out.println("s2t:" + ChineseUtils.s2t(text));
        System.out.println("s2tw:" + ChineseUtils.s2tw(text));
        System.out.println("s2hk:" + ChineseUtils.s2hk(text));
        System.out.println("t2s:" + ChineseUtils.t2s(text));
    }

    public static void main(String[] args) {
        fix37();
    }

}
