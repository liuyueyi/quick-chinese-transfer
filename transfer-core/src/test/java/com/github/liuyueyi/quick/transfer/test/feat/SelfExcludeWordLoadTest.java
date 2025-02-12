package com.github.liuyueyi.quick.transfer.test.feat;

import com.github.liuyueyi.quick.transfer.ChineseUtils;
import com.github.liuyueyi.quick.transfer.constants.TransType;

import java.util.Arrays;

/**
 * 加载自定义的不参与转换的单词
 *
 * @author YiHui
 * @date 2025/2/12
 */
public class SelfExcludeWordLoadTest {

    public static void main(String[] args) {
        String sWord = "发展国家";
        String tWords = "發展國家";

        String text = "中华人民共和国目前还处于发展国家行列，但他依然在快速发展中";
        String out = ChineseUtils.s2t(text);
        System.out.println("不加载自定义过滤词：简=>繁 ==>" + out);

        ChineseUtils.loadExcludeDict(TransType.SIMPLE_TO_TRADITIONAL, Arrays.asList(sWord, "国"));
        System.out.println("加载自定义过滤词后：简=>繁 ==>" + ChineseUtils.s2t(text));

        String tAns = ChineseUtils.t2s(out);
        System.out.println("不加载自定义过滤词：繁=>简 ==>" + tAns);
        ChineseUtils.loadExcludeDict(TransType.TRADITIONAL_TO_SIMPLE, Arrays.asList(tWords, "國"));
        System.out.println("不加载自定义过滤词：繁=>简 ==>" + ChineseUtils.t2s(out));


        // 全局的转换词典
        ChineseUtils.loadExcludeDict(Arrays.asList("华", "華"));
        System.out.println("加载全局过滤词：简=>繁 ==>" + ChineseUtils.s2t(text));
        System.out.println("加载全局过滤词：繁=>简 ==>" + ChineseUtils.t2s(out));
    }

}
