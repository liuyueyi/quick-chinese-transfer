package com.github.liuyueyi.quick.hanzi;

import com.github.liuyueyi.quick.hanzi.module.HanZiGenOptions;
import com.github.liuyueyi.quick.hanzi.module.HanZiRenderVo;
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
    private static String toStroke(HanZiRenderVo vo, HanZiGenOptions options, int index) {
        StringBuilder svg = new StringBuilder();
        svg.append("<svg version=\"1.1\" viewBox=\"0 0 1024 1024\" xmlns=\"http://www.w3.org/2000/svg\" " +
                "width=\"" + options.getSize() + "\" height=\"" + options.getSize() + "\" " +
                "style=\"border: 1px solid rgb(204, 204, 204); background-color: rgb(238, 238, 238);\">");
        if (options.getBgShow()) {
            svg.append(options.getBgStr());
        }

        svg.append("\n  <g transform=\"scale(1, -1) translate(0, -900)\">\n" +
                "\n    <style type=\"text/css\">\n");
        if (options.getBiHuaShow()) {
            svg.append(options.getBiHuaStyle());
        }
        if (options.getTipShow()) {
            svg.append(options.getTipStyle());
        }
        svg.append("\n    </style>\n");

        if (options.getBgShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(vo.getOutlinePaths(), "\n"))
                    .append("\n</g>\n");
        }

        svg.append("\n    <g style=\"opacity: 1;\">\n");
        for (int i = 0; i <= index; i++) {
            String animalPath = vo.getOutlinePaths().get(i);
            if (i == index) {
                // 当前显示的这一笔，使用高亮颜色
                animalPath = animalPath.replace(options.getOutlineColor(), options.getAnimalColor());
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

        if (options.getBiHuaShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(vo.getBiHuaPaths(), "\n"))
                    .append("\n</g>\n");
        }

        if (options.getTipShow()) {
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
     * @param vo
     * @param options
     * @return
     */
    public static List<String> toStrokes(HanZiRenderVo vo, HanZiGenOptions options) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < vo.getOutlinePaths().size(); i++) {
            ans.add(toStroke(vo, options, i));
        }
        return ans;
    }


    /**
     * 终态的svg
     *
     * @param vo
     * @param options
     * @return
     */
    public static String toSvg(HanZiRenderVo vo, HanZiGenOptions options) {
        StringBuilder svg = new StringBuilder();
        svg.append("<svg version=\"1.1\" viewBox=\"0 0 1024 1024\" xmlns=\"http://www.w3.org/2000/svg\" " +
                "width=\"" + options.getSize() + "\" height=\"" + options.getSize() + "\" " +
                "style=\"border: 1px solid rgb(204, 204, 204); background-color: rgb(238, 238, 238);\">");
        if (options.getBgShow()) {
            svg.append(options.getBgStr());
        }

        svg.append("\n  <g transform=\"scale(1, -1) translate(0, -900)\">\n" +
                "\n    <style type=\"text/css\">");
        if (options.getBiHuaShow()) {
            svg.append(options.getBiHuaStyle());
        }
        if (options.getTipShow()) {
            svg.append(options.getTipStyle());
        }
        if (options.getAnimalShow()) {
            svg.append(StrUtil.join(vo.getAnimalStyles(), "\n\n"));
        }
        svg.append("\n    </style>\n");

        if (options.getBgShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(vo.getOutlinePaths(), "\n"))
                    .append("\n</g>\n");
        }

        if (options.getAnimalShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(vo.getAnimalPaths(), "\n"))
                    .append("\n</g>\n");
        }

        if (options.getBiHuaShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(vo.getBiHuaPaths(), "\n"))
                    .append("\n</g>\n");
        }

        if (options.getTipShow()) {
            svg.append("\n<g style=\"opacity: 1;\">\n")
                    .append(StrUtil.join(vo.getTipPaths(), "\n"))
                    .append("\n</g>\n");
        }
        svg.append("\n  </g>\n" +
                "</svg>\n");
        return svg.toString();
    }


}
