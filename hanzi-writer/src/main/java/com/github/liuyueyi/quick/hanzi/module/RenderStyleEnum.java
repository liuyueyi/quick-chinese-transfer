package com.github.liuyueyi.quick.hanzi.module;

/**
 * 绘制样式
 *
 * @author YiHui
 * @date 2024/5/15
 */
public enum RenderStyleEnum {
    NORMAL(0, "普通"),
    STROKE_ANIMATE(1, "笔画动画"),
    TOTAL(2, "终态 + 笔画动画");

    private int style;
    private String desc;

    public int getStyle() {
        return style;
    }

    public String getDesc() {
        return desc;
    }

    RenderStyleEnum(int style, String desc) {
        this.style = style;
        this.desc = desc;
    }
}
