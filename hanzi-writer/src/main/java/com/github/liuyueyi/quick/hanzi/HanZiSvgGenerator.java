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

        public SvgGenBuilder setSvgStyle(String svgStyle) {
            options.setSvgStyle(svgStyle);
            return this;
        }

        public SvgGenBuilder setBgShow(Boolean bgShow) {
            options.setBgShow(bgShow);
            return this;
        }

        /**
         * 背景田字格
         *
         * @param bgStr
         * @return
         */
        public SvgGenBuilder setBg(String bgStr) {
            options.setBg(bgStr);
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


        public SvgGenBuilder setAnimateShow(Boolean animateShow) {
            options.setAnimateShow(animateShow);
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

        public SvgGenBuilder setAnimateColor(String animalColor) {
            options.setAnimateColor(animalColor);
            return this;
        }

        public SvgGenBuilder setWriteTipShow(Boolean tipShow) {
            options.setWriteTipShow(tipShow);
            return this;
        }

        public SvgGenBuilder setWriteTipStyle(String tipStyle) {
            options.setWriteTipStyle(tipStyle);
            return this;
        }


        public SvgGenBuilder setStrokeNumShow(Boolean biHuaShow) {
            options.setStrokeNumShow(biHuaShow);
            return this;
        }

        public SvgGenBuilder setStrokeNumStyle(String biHuaStyle) {
            options.setStrokeNumStyle(biHuaStyle);
            return this;
        }

        public SvgGenBuilder setAnimateSeconds(Float animalSeconds) {
            options.setAnimateSeconds(animalSeconds);
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
}

