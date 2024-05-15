package com.github.liuyueyi.quick.hanzi;

import com.github.liuyueyi.quick.hanzi.module.HanZiGenOptions;
import com.github.liuyueyi.quick.hanzi.module.HanZiRenderPathBo;
import com.github.liuyueyi.quick.hanzi.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 渲染
 *
 * @author YiHui
 * @date 2024/5/14
 */
public class HanZiRender {

    /**
     * 笔画顺序
     *
     * @param vo
     * @param options
     * @param index
     * @return
     */
    private static String toStroke(HanZiRenderPathBo vo, HanZiGenOptions options, int index) {
        StringBuilder svg = new StringBuilder();
        svg.append("<svg version=\"1.1\" viewBox=\"0 0 1024 1024\" xmlns=\"http://www.w3.org/2000/svg\" " +
                "width=\"" + options.getSize() + "\" height=\"" + options.getSize() + "\" ");
        svg.append("style=\"").append(options.getSvgStyle()).append("\">");
        if (options.getBgShow()) {
            svg.append(options.getBg());
        }

        svg.append("\n  <g transform=\"scale(1, -1) translate(0, -900)\">\n" +
                "\n    <style type=\"text/css\">\n");
        if (options.getStrokeNumShow()) {
            svg.append(options.getStrokeNumStyle());
        }
        if (options.getWriteTipShow()) {
            svg.append(options.getWriteTipStyle());
        }
        svg.append("\n    </style>\n");

        if (options.getOutlineShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(vo.getOutlinePaths(), "\n"))
                    .append("\n</g>\n");
        }

        svg.append("\n    <g style=\"opacity: 1;\">\n");
        for (int i = 0; i <= index; i++) {
            String animalPath = vo.getOutlinePaths().get(i);
            if (i == index) {
                // 当前显示的这一笔，使用高亮颜色
                animalPath = animalPath.replace(options.getOutlineColor(), options.getAnimateColor());
                svg.append(animalPath).append("\n");
            } else if (i == index - 1) {
                // 前面一笔，使用中间过渡色
                animalPath = animalPath.replace(options.getOutlineColor(), options.getMidColor());
                svg.append(animalPath).append("\n");
            } else {
                // 已绘制完毕的颜色
                animalPath = animalPath.replace(options.getOutlineColor(), options.getDrawColor());
                svg.append(animalPath).append("\n");
            }
        }
        svg.append("\n</g>\n");

        if (options.getStrokeNumShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(vo.getStrokePaths(), "\n"))
                    .append("\n</g>\n");
        }

        if (options.getWriteTipShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(vo.getTipPaths(), "\n"))
                    .append("\n</g>\n");
        }
        svg.append("\n  </g>\n" +
                "</svg>\n");
        return svg.toString();
    }


    /**
     * 笔画绘制顺序
     *
     * @param bo      生成的中间内容
     * @param options 绘制参数
     * @return
     */
    public static List<String> toStrokes(HanZiRenderPathBo bo, HanZiGenOptions options) {
        List<String> ans = new ArrayList<>(bo.getOutlinePaths().size());
        for (int i = 0; i < bo.getOutlinePaths().size(); i++) {
            ans.add(toStroke(bo, options, i));
        }
        return ans;
    }


    /**
     * 终态的svg
     *
     * @param bo      生成的中间内容
     * @param options 绘制参数
     * @return
     */
    public static String toSvg(HanZiRenderPathBo bo, HanZiGenOptions options) {
        StringBuilder svg = new StringBuilder();
        svg.append("<svg version=\"1.1\" viewBox=\"0 0 1024 1024\" xmlns=\"http://www.w3.org/2000/svg\" " +
                "width=\"" + options.getSize() + "\" height=\"" + options.getSize() + "\" " +
                "style=\"border: 1px solid rgb(204, 204, 204); background-color: rgb(238, 238, 238);\">");
        if (options.getBgShow()) {
            svg.append(options.getBg());
        }

        svg.append("\n  <g transform=\"scale(1, -1) translate(0, -900)\">\n" +
                "\n    <style type=\"text/css\">");
        if (options.getStrokeNumShow()) {
            svg.append(options.getStrokeNumStyle());
        }
        if (options.getWriteTipShow()) {
            svg.append(options.getWriteTipStyle());
        }
        if (options.getAnimateShow()) {
            svg.append(StrUtil.join(bo.getAnimalStyles(), "\n\n"));
        }
        svg.append("\n    </style>\n");

        if (options.getOutlineShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(bo.getOutlinePaths(), "\n"))
                    .append("\n</g>\n");
        }

        if (options.getAnimateShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(bo.getAnimalPaths(), "\n"))
                    .append("\n</g>\n");
        }

        if (options.getStrokeNumShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(bo.getStrokePaths(), "\n"))
                    .append("\n</g>\n");
        }

        if (options.getWriteTipShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(bo.getTipPaths(), "\n"))
                    .append("\n</g>\n");
        }
        svg.append("\n  </g>\n" +
                "</svg>\n");
        return svg.toString();
    }


}
