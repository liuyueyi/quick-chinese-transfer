package com.github.liuyueyi.quick.transfer.test.feat;

import com.github.liuyueyi.quick.transfer.ChineseUtils;

/**
 * 验证简体 -> 台湾繁体转换
 * <ul>
 *     <li>s2tw  : 仅字形变体（对应 OpenCC s2tw.json），不做台湾词汇转换</li>
 *     <li>s2twp : 字形变体 + 台湾常用词汇（对应 OpenCC s2twp.json）</li>
 * </ul>
 *
 * @author YiHui
 * @date 2026/11/11
 */
public class S2twTest {

    private static void check(String name, String actual, String expect) {
        boolean ok = expect.equals(actual);
        System.out.println((ok ? "[PASS] " : "[FAIL] ") + name
                + " -> actual=" + actual + (ok ? "" : " | expect=" + expect));
        if (!ok) {
            throw new AssertionError(name + " failed: actual=" + actual + ", expect=" + expect);
        }
    }

    public static void test() {
        // 字形变体：里(简) -> 裏(通用) -> 裡(台湾)
        check("s2tw 字形(里)", ChineseUtils.s2tw("里面"), "裡面");
        check("s2twp 字形(里)", ChineseUtils.s2twp("里面"), "裡面");

        // 字形变体：麪 -> 麵
        check("s2tw 字形(麪)", ChineseUtils.s2tw("長壽麪"), "長壽麵");

        // 词汇转换仅在 s2twp 生效：軟件 -> 軟體
        check("s2tw 不做词汇(軟件)", ChineseUtils.s2tw("軟件"), "軟件");
        check("s2twp 词汇(軟件)", ChineseUtils.s2twp("軟件"), "軟體");

        // 简体软件 -> 軟件 -> 軟體（走 s2t 基础词典 + s2twp 词汇层）
        check("s2twp 词汇(软件)", ChineseUtils.s2twp("软件"), "軟體");
        check("s2tw 软件仅字形", ChineseUtils.s2tw("软件"), "軟件");

        // 与通用繁体区别：线 -> 線（OpenCC 台湾字形亦为 線）
        check("s2tw 线", ChineseUtils.s2tw("线"), "線");

        // 混合句子
        check("s2twp 混合", ChineseUtils.s2twp("这个软件里面有很多线路"),
                "這個軟體裡面有很多線路");

        System.out.println("ALL S2TW TESTS PASSED");
    }

    public static void main(String[] args) {
        test();
    }
}
