package com.github.liuyueyi.quick.transfer.test.feat;

import com.github.liuyueyi.quick.transfer.ChineseUtils;
import com.github.liuyueyi.quick.transfer.constants.TransType;

import java.util.HashMap;
import java.util.Map;

/**
 * 加载自定义的不参与转换的单词
 *
 * @author YiHui
 * @date 2025/2/12
 */
public class SelfDictWordLoadTest {

    public static void main(String[] args) {
        String text = "中华人民共和国目前还处于发展国家行列，但他依然在快速发展中";
        String out = ChineseUtils.s2t(text);
        System.out.println("不加载自定义词典：简=>繁 ==>" + out);

        Map<String, String> map = new HashMap<>();
        map.put("发展国家", "發達國家");
        ChineseUtils.loadAdditionalDict(TransType.SIMPLE_TO_TRADITIONAL, map);
        System.out.println("加载自定义词典后：简=>繁 ==>" + ChineseUtils.s2t(text));


        // =============== 繁转简

        String tAns = ChineseUtils.t2s(out);
        System.out.println("不加载自定义词典：繁=>简 ==>" + tAns);

        Map<String, String> map2 = new HashMap<>();
        map2.put("發达國家", "发展国家");
        ChineseUtils.loadAdditionalDict(TransType.TRADITIONAL_TO_SIMPLE, map2);
        System.out.println("不加载自定义词典：繁=>简 ==>" + ChineseUtils.t2s(out));
    }

}
