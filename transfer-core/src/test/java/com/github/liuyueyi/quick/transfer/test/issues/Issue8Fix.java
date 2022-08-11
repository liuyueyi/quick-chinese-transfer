package com.github.liuyueyi.quick.transfer.test.issues;

import com.github.liuyueyi.quick.transfer.ChineseUtils;

/**
 * @author YiHui
 * @date 2022/8/11
 */
public class Issue8Fix {

    public static void fix8() {
        String text = "那個人聽說是個神秘人";
        String ans = ChineseUtils.t2s(text);
        System.out.println(ans);
        System.out.println("tw2s:" + ChineseUtils.tw2s(text));
        System.out.println("hk2s:" + ChineseUtils.hk2s(text));
    }

    public static void main(String[] args) {
        fix8();
    }
}
