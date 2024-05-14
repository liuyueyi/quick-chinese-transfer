package com.github.liuyueyi.quick.hanzi;

import com.github.liuyueyi.quick.hanzi.module.HanZiGenOptions;
import com.github.liuyueyi.quick.hanzi.module.HanZiRenderContents;
import com.github.liuyueyi.quick.hanzi.module.HanZiRenderVo;
import com.github.liuyueyi.quick.hanzi.util.FileReadUtil;
import com.github.liuyueyi.quick.hanzi.util.HanZiGenUtil;

import java.util.List;

/**
 * 汉字svg生成
 *
 * @author YiHui
 * @date 2024/5/14
 */
public class HanZiSvgGenerator {
    public static HanZiRenderVo buildRender(String character, HanZiGenOptions options) {
        List<String> lines = FileReadUtil.readLines("hz/" + character + ".txt");
        if (lines.size() < 2) {
            return null;
        }
        String content = lines.get(1);
        HanZiRenderContents contents = HanZiGenUtil.parseRenderContent(content);
        System.out.println(contents);
        return HanZiGenUtil.buildAniSvg(contents, options);
    }

    public static String toSvg(String character, HanZiGenOptions options) {
        HanZiRenderVo vo = buildRender(character, options);
        String svg = HanZiRender.toSvg(vo, options);
        return svg;
    }

    public static List<String> toStrokes(String character, HanZiGenOptions options) {
        HanZiRenderVo vo = buildRender(character, options);
        List<String> svg = HanZiRender.toStrokes(vo, options);
        return svg;
    }


    public static void main(String[] args) {
//        String content = "{\"character\": \"么\", \"strokes\": [\"M 464 772 Q 476 682 334 526 Q 256 448 174 381 Q 164 375 176 372 Q 263 387 398 520 Q 401 524 405 528 Q 456 583 534 696 Q 544 711 552 717 Q 559 721 559 734 Q 558 744 544 759 Q 505 795 471 788 Q 461 787 464 772 Z\", \"M 727 196 Q 574 180 407 145 Q 391 139 386 144 Q 382 153 397 172 Q 644 446 695 491 Q 713 501 707 518 Q 701 536 644 574 Q 625 587 611 584 Q 593 580 597 551 Q 603 515 574 472 Q 498 343 376 218 Q 342 178 273 142 Q 252 129 256 114 Q 260 92 290 62 Q 302 46 321 58 Q 390 107 741 166 Q 742 166 744 166 C 774 171 757 199 727 196 Z\", \"M 744 166 Q 807 61 814 57 Q 823 53 834 62 Q 850 72 848 123 Q 849 192 672 329 Q 665 336 658 324 Q 654 309 663 296 Q 694 251 727 196 L 744 166 Z\"], \"medians\": [[[473, 778], [488, 766], [507, 733], [463, 647], [428, 597], [330, 487], [231, 407], [180, 378]], [[615, 566], [642, 521], [636, 496], [524, 348], [361, 166], [341, 124], [393, 116], [543, 151], [692, 176], [718, 179], [728, 168]], [[669, 316], [747, 226], [802, 146], [821, 95], [819, 65]]]}";

        HanZiGenOptions options = new HanZiGenOptions();
        options.setTipShow(false).setBiHuaShow(false);
        String svg = toSvg("我", options);
        List<String> svgList = toStrokes("我", options);
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
