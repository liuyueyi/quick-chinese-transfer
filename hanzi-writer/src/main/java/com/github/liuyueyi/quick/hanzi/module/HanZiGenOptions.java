package com.github.liuyueyi.quick.hanzi.module;

import java.util.Objects;

/**
 * 构造方式
 *
 * @author YiHui
 * @date 2024/5/14
 */
public class HanZiGenOptions {
    /**
     * 默认大小
     */
    private Integer size = 300;


    /**
     * 文字背景是否显示
     */
    private Boolean bgShow = true;

    /**
     * 默认是田字格背景
     */
    private String bg = "  <g stroke=\"lightgray\" stroke-dasharray=\"1,1\" stroke-width=\"1\" transform=\"scale(4, 4)\">\n" +
            "    <line x1=\"0\" y1=\"0\" x2=\"256\" y2=\"256\"></line>\n" +
            "    <line x1=\"256\" y1=\"0\" x2=\"0\" y2=\"256\"></line>\n" +
            "    <line x1=\"128\" y1=\"0\" x2=\"128\" y2=\"256\"></line>\n" +
            "    <line x1=\"0\" y1=\"128\" x2=\"256\" y2=\"128\"></line>\n" +
            "  </g>\n";

    private String svgStyle = "border: 1px solid rgb(204, 204, 204); background-color: rgb(238, 238, 238);";

    /**
     * 文字的灰色背景提示
     */
    private Boolean outlineShow = true;

    private String outlineColor = "rgba(221,221,221,1)";


    /**
     * 描绘动画
     */
    private Boolean animateShow = true;

    /**
     * 绘制后的颜色
     */
    private String drawColor = "rgb(85, 85, 85)";

    /**
     * 绘制过程中间色
     */
    private String midColor = "rgb(68, 68, 255)";

    /**
     * 绘制的高亮色
     */
    private String animateColor = "rgb(187, 68, 68)";


    /**
     * 是否显示绘制轮廓
     */
    private Boolean writeTipShow = true;

    /**
     * 绘制轮廓的颜色
     */
    private String writeTipStyle = "\n      .median-stroke {\n" +
            "          fill: none;\n" +
            "          stroke: rgb(15,15,15, 0.5);\n" +
            "          stroke-width: 2px;\n" +
            "          opacity: 1;\n" +
            "      }";


    /**
     * 是否显示笔画顺序的文字
     */
    private Boolean strokeNumShow = true;

    /**
     * 笔画提示的格式
     */
    private String strokeNumStyle = "\n        text {\n" +
            "            font-family: Helvetica;\n" +
            "            font-size: 50px;\n" +
            "            fill: #ffffff;\n" +
            "            paint-order: stroke;\n" +
            "            stroke: #000000;\n" +
            "            stroke-width: 4px;\n" +
            "            stroke-linecap: butt;\n" +
            "            stroke-linejoin: miter;\n" +
            "            font-weight: 800;\n" +
            "        }";

    /**
     * 绘制动画的时间
     */
    private Float animateSeconds = 0.9f;

    /**
     * 绘制结果
     */
    private RenderStyleEnum renderStyle = RenderStyleEnum.NORMAL;

    public Integer getSize() {
        return size;
    }

    public HanZiGenOptions setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Boolean getBgShow() {
        return bgShow;
    }

    public HanZiGenOptions setBgShow(Boolean bgShow) {
        this.bgShow = bgShow;
        return this;
    }

    public String getBg() {
        return bg;
    }

    public HanZiGenOptions setBg(String bg) {
        this.bg = bg;
        return this;
    }

    public String getSvgStyle() {
        return svgStyle;
    }

    public HanZiGenOptions setSvgStyle(String svgStyle) {
        this.svgStyle = svgStyle;
        return this;
    }

    public Boolean getOutlineShow() {
        return outlineShow;
    }

    public HanZiGenOptions setOutlineShow(Boolean outlineShow) {
        this.outlineShow = outlineShow;
        return this;
    }

    public String getOutlineColor() {
        return outlineColor;
    }

    public HanZiGenOptions setOutlineColor(String outlineColor) {
        this.outlineColor = outlineColor;
        return this;
    }

    public Boolean getAnimateShow() {
        return animateShow;
    }

    public HanZiGenOptions setAnimateShow(Boolean animateShow) {
        this.animateShow = animateShow;
        return this;
    }

    public String getDrawColor() {
        return drawColor;
    }

    public HanZiGenOptions setDrawColor(String drawColor) {
        this.drawColor = drawColor;
        return this;
    }

    public String getMidColor() {
        return midColor;
    }

    public HanZiGenOptions setMidColor(String midColor) {
        this.midColor = midColor;
        return this;
    }

    public String getAnimateColor() {
        return animateColor;
    }

    public HanZiGenOptions setAnimateColor(String animateColor) {
        this.animateColor = animateColor;
        return this;
    }

    public Boolean getWriteTipShow() {
        return writeTipShow;
    }

    public HanZiGenOptions setWriteTipShow(Boolean writeTipShow) {
        this.writeTipShow = writeTipShow;
        return this;
    }

    public String getWriteTipStyle() {
        return writeTipStyle;
    }

    public HanZiGenOptions setWriteTipStyle(String writeTipStyle) {
        this.writeTipStyle = writeTipStyle;
        return this;
    }

    public Boolean getStrokeNumShow() {
        return strokeNumShow;
    }

    public HanZiGenOptions setStrokeNumShow(Boolean strokeNumShow) {
        this.strokeNumShow = strokeNumShow;
        return this;
    }

    public String getStrokeNumStyle() {
        return strokeNumStyle;
    }

    public HanZiGenOptions setStrokeNumStyle(String strokeNumStyle) {
        this.strokeNumStyle = strokeNumStyle;
        return this;
    }

    public Float getAnimateSeconds() {
        return animateSeconds;
    }

    public HanZiGenOptions setAnimateSeconds(Float animateSeconds) {
        this.animateSeconds = animateSeconds;
        return this;
    }

    public RenderStyleEnum getRenderStyle() {
        return renderStyle;
    }

    public HanZiGenOptions setRenderStyle(RenderStyleEnum renderStyle) {
        this.renderStyle = renderStyle;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HanZiGenOptions options = (HanZiGenOptions) o;
        return Objects.equals(size, options.size) && Objects.equals(bgShow, options.bgShow) && Objects.equals(bg, options.bg) && Objects.equals(outlineShow, options.outlineShow) && Objects.equals(outlineColor, options.outlineColor) && Objects.equals(animateShow, options.animateShow) && Objects.equals(drawColor, options.drawColor) && Objects.equals(midColor, options.midColor) && Objects.equals(animateColor, options.animateColor) && Objects.equals(writeTipShow, options.writeTipShow) && Objects.equals(writeTipStyle, options.writeTipStyle) && Objects.equals(strokeNumShow, options.strokeNumShow) && Objects.equals(strokeNumStyle, options.strokeNumStyle) && Objects.equals(animateSeconds, options.animateSeconds) && renderStyle == options.renderStyle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, bgShow, bg, outlineShow, outlineColor, animateShow, drawColor, midColor, animateColor, writeTipShow, writeTipStyle, strokeNumShow, strokeNumStyle, animateSeconds, renderStyle);
    }

    @Override
    public String toString() {
        return "HanZiGenOptions{" +
                "size=" + size +
                ", bgShow=" + bgShow +
                ", bgStr='" + bg + '\'' +
                ", outlineShow=" + outlineShow +
                ", outlineColor='" + outlineColor + '\'' +
                ", animalShow=" + animateShow +
                ", drawColor='" + drawColor + '\'' +
                ", midColor='" + midColor + '\'' +
                ", animalColor='" + animateColor + '\'' +
                ", tipShow=" + writeTipShow +
                ", tipStyle='" + writeTipStyle + '\'' +
                ", biHuaShow=" + strokeNumShow +
                ", biHuaStyle='" + strokeNumStyle + '\'' +
                ", animalSeconds=" + animateSeconds +
                ", render=" + renderStyle +
                '}';
    }
}
