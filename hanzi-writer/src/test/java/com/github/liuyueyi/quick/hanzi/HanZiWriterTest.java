package com.github.liuyueyi.quick.hanzi;

import com.github.liuyueyi.quick.hanzi.module.HanZiRenderResultVo;
import com.github.liuyueyi.quick.hanzi.module.RenderStyleEnum;

import java.util.List;

/**
 * @author YiHui
 * @date 2024/5/15
 */
public class HanZiWriterTest {

    public static void main(String[] args) {
        HanZiRenderResultVo vo = HanZiSvgGenerator
                .newGenerator("帅")
                .setRenderStyle(RenderStyleEnum.TOTAL)
                .setBiHuaShow(true)
                .setAnimalShow(true)
                .setOutlineShow(true)
                .setTipShow(true)
                .toSvgs();

        String svg = vo.getSvg();
        List<String> svgList = vo.getStrokes();
        System.out.println("\n\n");
        System.out.println(svg);
        System.out.println("\n\n");

        System.out.println("\n\n");
        for (String sub : svgList) {
            System.out.println(sub);
        }
        System.out.println("\n\n");
    }
}
