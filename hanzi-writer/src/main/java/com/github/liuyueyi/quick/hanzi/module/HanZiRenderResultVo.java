package com.github.liuyueyi.quick.hanzi.module;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 返回结果
 *
 * @author YiHui
 * @date 2024/5/15
 */
public class HanZiRenderResultVo implements Serializable {
    private static final long serialVersionUID = -8354470279065191558L;
    /**
     * 终成品
     */
    private String svg;

    /**
     * 笔画绘制效果
     */
    private List<String> strokes;

    public String getSvg() {
        return svg;
    }

    public HanZiRenderResultVo setSvg(String svg) {
        this.svg = svg;
        return this;
    }

    public List<String> getStrokes() {
        return strokes;
    }

    public HanZiRenderResultVo setStrokes(List<String> strokes) {
        this.strokes = strokes;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HanZiRenderResultVo that = (HanZiRenderResultVo) o;
        return Objects.equals(svg, that.svg) && Objects.equals(strokes, that.strokes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(svg, strokes);
    }

    @Override
    public String toString() {
        return "HanZiRenderResultVo{" +
                "svg='" + svg + '\'' +
                ", strokes=" + strokes +
                '}';
    }
}
