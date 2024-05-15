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
    private String bgStr = "  <g stroke=\"lightgray\" stroke-dasharray=\"1,1\" stroke-width=\"1\" transform=\"scale(4, 4)\">\n" +
            "    <line x1=\"0\" y1=\"0\" x2=\"256\" y2=\"256\"></line>\n" +
            "    <line x1=\"256\" y1=\"0\" x2=\"0\" y2=\"256\"></line>\n" +
            "    <line x1=\"128\" y1=\"0\" x2=\"128\" y2=\"256\"></line>\n" +
            "    <line x1=\"0\" y1=\"128\" x2=\"256\" y2=\"128\"></line>\n" +
            "  </g>\n";

    private String bgStyle = "border: 1px solid rgb(204, 204, 204); background-color: rgb(238, 238, 238);";

    /**
     * 文字的灰色背景提示
     */
    private Boolean outlineShow = true;

    private String outlineColor = "rgba(221,221,221,1)";


    /**
     * 描绘动画
     */
    private Boolean animalShow = true;

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
    private String animalColor = "rgb(187, 68, 68)";


    /**
     * 是否显示绘制轮廓
     */
    private Boolean tipShow = true;

    /**
     * 绘制轮廓的颜色
     */
    private String tipStyle = "      .median-stroke {\n" +
            "          fill: none;\n" +
            "          stroke: rgb(15,15,15, 0.5);\n" +
            "          stroke-width: 2px;\n" +
            "          opacity: 1;\n" +
            "      }";


    /**
     * 是否显示笔画顺序的文字
     */
    private Boolean biHuaShow = true;

    /**
     * 笔画提示的格式
     */
    private String biHuaStyle = "        text {\n" +
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
    private Float animalSeconds = 0.9f;

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

    public String getBgStr() {
        return bgStr;
    }

    public HanZiGenOptions setBgStr(String bgStr) {
        this.bgStr = bgStr;
        return this;
    }

    public String getBgStyle() {
        return bgStyle;
    }

    public HanZiGenOptions setBgStyle(String bgStyle) {
        this.bgStyle = bgStyle;
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

    public Boolean getAnimalShow() {
        return animalShow;
    }

    public HanZiGenOptions setAnimalShow(Boolean animalShow) {
        this.animalShow = animalShow;
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

    public String getAnimalColor() {
        return animalColor;
    }

    public HanZiGenOptions setAnimalColor(String animalColor) {
        this.animalColor = animalColor;
        return this;
    }

    public Boolean getTipShow() {
        return tipShow;
    }

    public HanZiGenOptions setTipShow(Boolean tipShow) {
        this.tipShow = tipShow;
        return this;
    }

    public String getTipStyle() {
        return tipStyle;
    }

    public HanZiGenOptions setTipStyle(String tipStyle) {
        this.tipStyle = tipStyle;
        return this;
    }

    public Boolean getBiHuaShow() {
        return biHuaShow;
    }

    public HanZiGenOptions setBiHuaShow(Boolean biHuaShow) {
        this.biHuaShow = biHuaShow;
        return this;
    }

    public String getBiHuaStyle() {
        return biHuaStyle;
    }

    public HanZiGenOptions setBiHuaStyle(String biHuaStyle) {
        this.biHuaStyle = biHuaStyle;
        return this;
    }

    public Float getAnimalSeconds() {
        return animalSeconds;
    }

    public HanZiGenOptions setAnimalSeconds(Float animalSeconds) {
        this.animalSeconds = animalSeconds;
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
        return Objects.equals(size, options.size) && Objects.equals(bgShow, options.bgShow) && Objects.equals(bgStr, options.bgStr) && Objects.equals(outlineShow, options.outlineShow) && Objects.equals(outlineColor, options.outlineColor) && Objects.equals(animalShow, options.animalShow) && Objects.equals(drawColor, options.drawColor) && Objects.equals(midColor, options.midColor) && Objects.equals(animalColor, options.animalColor) && Objects.equals(tipShow, options.tipShow) && Objects.equals(tipStyle, options.tipStyle) && Objects.equals(biHuaShow, options.biHuaShow) && Objects.equals(biHuaStyle, options.biHuaStyle) && Objects.equals(animalSeconds, options.animalSeconds) && renderStyle == options.renderStyle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, bgShow, bgStr, outlineShow, outlineColor, animalShow, drawColor, midColor, animalColor, tipShow, tipStyle, biHuaShow, biHuaStyle, animalSeconds, renderStyle);
    }

    @Override
    public String toString() {
        return "HanZiGenOptions{" +
                "size=" + size +
                ", bgShow=" + bgShow +
                ", bgStr='" + bgStr + '\'' +
                ", outlineShow=" + outlineShow +
                ", outlineColor='" + outlineColor + '\'' +
                ", animalShow=" + animalShow +
                ", drawColor='" + drawColor + '\'' +
                ", midColor='" + midColor + '\'' +
                ", animalColor='" + animalColor + '\'' +
                ", tipShow=" + tipShow +
                ", tipStyle='" + tipStyle + '\'' +
                ", biHuaShow=" + biHuaShow +
                ", biHuaStyle='" + biHuaStyle + '\'' +
                ", animalSeconds=" + animalSeconds +
                ", render=" + renderStyle +
                '}';
    }
}
