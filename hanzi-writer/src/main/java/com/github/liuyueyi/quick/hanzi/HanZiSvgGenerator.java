package com.github.liuyueyi.quick.hanzi;

import com.github.liuyueyi.quick.hanzi.module.HanZiGenOptions;
import com.github.liuyueyi.quick.hanzi.module.HanZiRenderContents;
import com.github.liuyueyi.quick.hanzi.module.HanZiRenderPathBo;
import com.github.liuyueyi.quick.hanzi.module.HanZiRenderResultVo;
import com.github.liuyueyi.quick.hanzi.module.RenderStyleEnum;
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
    private HanZiSvgGenerator() {
    }

    public static class SvgGenBuilder {
        private String character;
        private HanZiRenderContents content;
        private HanZiGenOptions options;

        private SvgGenBuilder(String character) {
            this.character = character;
            this.options = new HanZiGenOptions();
        }

        private SvgGenBuilder(HanZiRenderContents content) {
            this.options = new HanZiGenOptions();
            this.content = content;
        }

        public SvgGenBuilder setSize(Integer size) {
            options.setSize(size);
            return this;
        }

        public SvgGenBuilder setBgShow(Boolean bgShow) {
            options.setBgShow(bgShow);
            return this;
        }


        public SvgGenBuilder setBgStr(String bgStr) {
            options.setBgStr(bgStr);
            return this;
        }


        public SvgGenBuilder setOutlineShow(Boolean outlineShow) {
            options.setOutlineShow(outlineShow);
            return this;
        }

        public SvgGenBuilder setOutlineColor(String outlineColor) {
            options.setOutlineColor(outlineColor);
            return this;
        }


        public SvgGenBuilder setAnimalShow(Boolean animalShow) {
            options.setAnimalShow(animalShow);
            return this;
        }

        public SvgGenBuilder setDrawColor(String drawColor) {
            options.setDrawColor(drawColor);
            return this;
        }

        public SvgGenBuilder setMidColor(String midColor) {
            options.setMidColor(midColor);
            return this;
        }

        public SvgGenBuilder setAnimalColor(String animalColor) {
            options.setAnimalColor(animalColor);
            return this;
        }

        public SvgGenBuilder setTipShow(Boolean tipShow) {
            options.setTipShow(tipShow);
            return this;
        }

        public SvgGenBuilder setTipStyle(String tipStyle) {
            options.setTipStyle(tipStyle);
            return this;
        }


        public SvgGenBuilder setBiHuaShow(Boolean biHuaShow) {
            options.setBiHuaShow(biHuaShow);
            return this;
        }

        public SvgGenBuilder setBiHuaStyle(String biHuaStyle) {
            options.setBiHuaStyle(biHuaStyle);
            return this;
        }

        public SvgGenBuilder setAnimalSeconds(Float animalSeconds) {
            options.setAnimalSeconds(animalSeconds);
            return this;
        }

        public SvgGenBuilder setRenderStyle(RenderStyleEnum render) {
            options.setRenderStyle(render);
            return this;
        }

        public HanZiRenderResultVo toSvgs() {
            if (options.getRenderStyle() == null) {
                options.setRenderStyle(RenderStyleEnum.NORMAL);
            }

            if (content == null) {
                List<String> lines = FileReadUtil.readLines("hz/" + character + ".txt");
                if (lines.size() < 2) {
                    return null;
                }
                String content = lines.get(1);
                this.content = HanZiGenUtil.parseRenderContent(content);
            }

            HanZiRenderPathBo path = HanZiGenUtil.buildAniSvg(this.content, this.options);
            HanZiRenderResultVo vo = new HanZiRenderResultVo();
            if (options.getRenderStyle() == RenderStyleEnum.NORMAL) {
                vo.setSvg(HanZiRender.toSvg(path, options));
            } else if (options.getRenderStyle() == RenderStyleEnum.STROKE_ANIMATE) {
                vo.setStrokes(HanZiRender.toStrokes(path, options));
            } else {
                vo.setSvg(HanZiRender.toSvg(path, options));
                vo.setStrokes(HanZiRender.toStrokes(path, options));
            }
            return vo;
        }

    }

    public static SvgGenBuilder newGenerator(String character) {
        return new SvgGenBuilder(character);
    }

    public static SvgGenBuilder newGenerator(HanZiRenderContents content) {
        return new SvgGenBuilder(content);
    }


    public static void main(String[] args) {
//        String content = "{\"character\": \"么\", \"strokes\": [\"M 464 772 Q 476 682 334 526 Q 256 448 174 381 Q 164 375 176 372 Q 263 387 398 520 Q 401 524 405 528 Q 456 583 534 696 Q 544 711 552 717 Q 559 721 559 734 Q 558 744 544 759 Q 505 795 471 788 Q 461 787 464 772 Z\", \"M 727 196 Q 574 180 407 145 Q 391 139 386 144 Q 382 153 397 172 Q 644 446 695 491 Q 713 501 707 518 Q 701 536 644 574 Q 625 587 611 584 Q 593 580 597 551 Q 603 515 574 472 Q 498 343 376 218 Q 342 178 273 142 Q 252 129 256 114 Q 260 92 290 62 Q 302 46 321 58 Q 390 107 741 166 Q 742 166 744 166 C 774 171 757 199 727 196 Z\", \"M 744 166 Q 807 61 814 57 Q 823 53 834 62 Q 850 72 848 123 Q 849 192 672 329 Q 665 336 658 324 Q 654 309 663 296 Q 694 251 727 196 L 744 166 Z\"], \"medians\": [[[473, 778], [488, 766], [507, 733], [463, 647], [428, 597], [330, 487], [231, 407], [180, 378]], [[615, 566], [642, 521], [636, 496], [524, 348], [361, 166], [341, 124], [393, 116], [543, 151], [692, 176], [718, 179], [728, 168]], [[669, 316], [747, 226], [802, 146], [821, 95], [819, 65]]]}";
        HanZiRenderResultVo vo = HanZiSvgGenerator
                .newGenerator("我")
                .setRenderStyle(RenderStyleEnum.TOTAL)
                .setBiHuaShow(false)
                .setOutlineShow(false)
                .setTipShow(false)
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

