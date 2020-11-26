package com.github.liuyueyi.quick.transfer.test;

import com.github.liuyueyi.quick.transfer.ChineseUtils;
import com.github.liuyueyi.quick.transfer.dictionary.BasicDictionary;
import com.github.liuyueyi.quick.transfer.dictionary.DictionaryFactory;
import com.github.liuyueyi.quick.transfer.dictionary.SecondParserDictionary;
import org.junit.Test;

public class TestLoader {

    String text = "这斜月三星洞…… 长寿面，孙悟空，猪八戒，唐僧，沙和尚，白龙马，李靖，托塔天王, 戏说西游，许多人都这样说，收拾一下，拾金不昧";

    @Test
    public void testTransfer() {
        BasicDictionary s2t = DictionaryFactory.loadDictionary("tc/s2t.txt", false);
        SecondParserDictionary s2hk = DictionaryFactory.loadSecondDictionary(s2t, "tc/t2hk.txt", false);
        SecondParserDictionary s2tw = DictionaryFactory.loadSecondDictionary(s2t, "tc/t2tw.txt", false);
        System.out.println(s2t.convert(text));
        System.out.println(s2hk.convert(text));
        System.out.println(s2tw.convert(text));
    }


    @Test
    public void testTrans() {
        String out = ChineseUtils.s2t(text);
        System.out.println("s2t -->" + out);
        String hkOut = ChineseUtils.s2hk(text);
        System.out.println("s2hk -->" + hkOut);
        String twOut = ChineseUtils.s2tw(text);
        System.out.println("s2tw --> " + twOut);

        String origin = ChineseUtils.t2s(out);
        System.out.println("t2s -->" + origin);
        System.out.println("hk2s -->" + ChineseUtils.hk2s(hkOut));
        System.out.println("tw2s -->" + ChineseUtils.tw2s(twOut));
    }
}
