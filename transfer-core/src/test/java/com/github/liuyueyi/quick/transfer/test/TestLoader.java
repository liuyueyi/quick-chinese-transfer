package com.github.liuyueyi.quick.transfer.test;

import com.github.liuyueyi.quick.transfer.ChineseUtils;
import com.github.liuyueyi.quick.transfer.constants.TransType;
import com.github.liuyueyi.quick.transfer.dictionary.BasicDictionary;
import com.github.liuyueyi.quick.transfer.dictionary.DictionaryFactory;
import com.github.liuyueyi.quick.transfer.dictionary.SecondParserDictionary;

public class TestLoader {

    private static String text = "这斜月三星洞…… 长寿面，孙悟空，猪八戒，唐僧，沙和尚，白龙马，李靖，托塔天王, 戏说西游，许多人都这样说，收拾一下，拾金不昧；纔=才； 奔驰, 定址，奶油，黄油";

    static {
        // issue #3
        text += "，查克拉";
        // issue #4
        text += "，孙乾";
        // issue #5
        text += "，骨骼";
        // issure #6
        text += ", “”";
        text += "\uD873\uDD8D=鮀，\uD873\uDD8F=鮠，\uD873\uDD9F=鯻，\uD873\uDE2A=鷭";
        text += "，唇";
    }

    public static void main(String[] args) throws InterruptedException {
        // 预热加载所有的词典
        ChineseUtils.preLoad(true, TransType.values());
        Thread.sleep(1000);
        long start = System.currentTimeMillis();
        System.out.println("---start---:" + start);
        testTrans();
//        testTransfer();
        System.out.println("---end----:" + (System.currentTimeMillis() - start));
        ChineseUtils.unLoad(TransType.values());
    }

    public static void testTransfer() {
        BasicDictionary s2t = DictionaryFactory.loadDictionary("tc/s2t.txt", false);
        SecondParserDictionary s2hk = DictionaryFactory.loadSecondDictionary(s2t, "tc/t2hk.txt", false);
        SecondParserDictionary s2tw = DictionaryFactory.loadSecondDictionary(s2t, "tc/t2tw.txt", false);
        System.out.println(s2t.convert(text));
        System.out.println(s2hk.convert(text));
        System.out.println(s2tw.convert(text));
    }

    public static void testTrans() {
        // 简体中文，直接代用 繁体转简体
        System.out.println("s2s -->" + ChineseUtils.t2s(text));

        String out = ChineseUtils.s2t(text);
        System.out.println("s2t -->" + out);
        String hkOut = ChineseUtils.s2hk(text);
        System.out.println("s2hk-->" + hkOut);
        String twOut = ChineseUtils.s2tw(text);
        System.out.println("s2tw--> " + twOut);

        String origin = ChineseUtils.t2s(out);
        System.out.println("t2s -->" + origin);
        System.out.println("hk2s-->" + ChineseUtils.hk2s(hkOut));
        System.out.println("tw2s-->" + ChineseUtils.tw2s(twOut));
    }
}
