package com.github.liuyueyi.quick.hanzi.util;

import com.github.liuyueyi.quick.hanzi.module.HanZiGenOptions;
import com.github.liuyueyi.quick.hanzi.module.HanZiRenderContents;
import com.github.liuyueyi.quick.hanzi.module.HanZiRenderPathBo;
import com.github.liuyueyi.quick.hanzi.module.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 汉字工具类
 *
 * @author YiHui
 * @date 2024/5/14
 */
public class HanZiGenUtil {
    private static Point sub(Point p1, Point p2) {
        return new Point(p1.x - p2.x, p1.y - p2.y);
    }

    private static Double magnitude(Point p) {
        return Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2));
    }

    private static Double distance(Point p1, Point p2) {
        return magnitude(sub(p1, p2));
    }

    private static Double len(List<Point> points) {
        Point last = null;
        double ans = 0;
        for (Point p : points) {
            if (last == null) {
                last = p;
            } else {
                double dist = distance(p, last);
                ans += dist;
                last = p;
            }
        }
        return ans;
    }


    /**
     * 动画绘制路径
     *
     * @param points 点数
     * @param close  是否是最后一个
     * @return 绘制的path路径
     */
    private static String buildPathStr(List<Point> points, boolean close) {
        StringBuilder path = new StringBuilder();
        boolean first = true;
        for (Point p : points) {
            if (first) {
                path.append("M ").append(p.x).append(" ").append(p.y);
                first = false;
            } else {
                path.append(" L ").append(p.x).append(" ").append(p.y);
            }
        }
        if (close) {
            path.append("Z");
        }
        return path.toString();
    }

    /**
     * 生成绘制动画的 dash_array
     *
     * @param points 点数
     * @return
     */
    public static int dashArray(List<Point> points) {
        Double len = len(points);
        return (int) Math.round(len);
    }

    private static List<Point> toPoints(List<List<Integer>> points) {
        List<Point> list = new ArrayList<>(points.size());
        for (List<Integer> sub : points) {
            list.add(new Point(sub.get(0), sub.get(1)));
        }
        return list;
    }

    /**
     * 解析笔画绘制实体
     * - 可以使用json序列化工具进行解析
     * - 这里为了不额外引入外部依赖，做了一个简单的字符串解析
     *
     * @param content json格式
     * @return
     */
    public static HanZiRenderContents parseRenderContent(String content) {
        int charIndex = content.indexOf("character\"") + "character\"".length();
        charIndex = content.indexOf("\"", charIndex) + 1;
        int charEnd = content.indexOf(",", charIndex);
        String character = content.substring(charIndex, charEnd - 1);

        int strokeIndex = content.indexOf("strokes\"");
        strokeIndex = content.indexOf("[", strokeIndex) + 1;
        int strokeEnd = content.indexOf("]", strokeIndex);
        String strokes = content.substring(strokeIndex, strokeEnd);
        strokes = strokes.replaceAll("\"", "");
        List<String> strokeList = Arrays.asList(strokes.split(","));

        int medianIndex = content.indexOf("medians");
        medianIndex = content.indexOf("[", medianIndex) + 1;
        int medianEnd = 0;
        if (medianIndex > strokeEnd) {
            // medians 再 strokes 后面
            medianEnd = content.lastIndexOf("]");
        } else {
            // medians 再 strokes 前面
            medianIndex = content.substring(0, strokeIndex).lastIndexOf("]");
        }
        int level = 0;
        List<List<Point>> list = new ArrayList<>(strokeList.size());
        List<Point> currentPoints = new ArrayList<>();
        Point point = new Point();
        StringBuilder num = new StringBuilder();
        for (int start = medianIndex; start < medianEnd; start++) {
            char target = content.charAt(start);
            if (target == '[') {
                level += 1;
            } else if (target == ']') {
                level -= 1;
                if (level == 1) {
                    point.y = Integer.parseInt(num.toString());
                    num = new StringBuilder();
                    currentPoints.add(point);
                    point = new Point();
                } else if (level == 0) {
                    list.add(currentPoints);
                    currentPoints = new ArrayList<>();
                }
            } else if (target == ',') {
                if (level == 2) {
                    point.x = Integer.parseInt(num.toString());
                    num = new StringBuilder();
                }
            } else if (target >= '0' && target <= '9') {
                num.append(target);
            }
        }

        HanZiRenderContents contents = new HanZiRenderContents();
        contents.setCharacter(character);
        contents.setStrokes(strokeList);
        contents.setMedians(list);
        return contents;
    }

    public static HanZiRenderPathBo buildAniSvg(HanZiRenderContents contents, HanZiGenOptions options) {
        List<String> strokes = contents.getStrokes();
        int biHuaNum = strokes.size();
        List<String> outlinePath = new ArrayList<>(biHuaNum);
        List<String> tmpAnimalClipPath = new ArrayList<>(biHuaNum);
        int index = 0;
        for (Object obj : strokes) {
            outlinePath.add(String.format("\t\t<path d=\"%s\" fill=\"%s\"></path>", obj, options.getOutlineColor()));
            tmpAnimalClipPath.add(String.format("\t\t<clipPath id=\"clip-%d\">\n\t\t\t<path d=\"%s\"></path>\n\t\t</clipPath>", index, obj));
            index += 1;
        }

        List<String> animalPath = new ArrayList<>(biHuaNum);
        List<String> animalStyles = new ArrayList<>(biHuaNum);
        List<String> biHuaPath = new ArrayList<>(biHuaNum);
        List<String> tipPath = new ArrayList<>(biHuaNum);
        index = 0;
        for (List<Point> pointList : contents.getMedians()) {
            String path = buildPathStr(pointList, false);
            int dash = dashArray(pointList) + 128;
            animalPath.add(String.format("%s\n\t\t\t<path clip-path=\"url(#clip-%d)\" d=\"%s\" fill=\"none\" id=\"animation-%d\" stroke-dasharray=\"%d %d\" stroke-linecap=\"round\" class='ani-tag %s'></path>"
                    , tmpAnimalClipPath.get(index), index, path, index, dash, dash * 2, options.getAnimalShow() ? "animated" : ""));


            float seconds = options.getAnimalSeconds();
            animalStyles.add("        @keyframes keyframes" + index + " {\n" +
                    "          from {\n" +
                    "            stroke: " + options.getAnimalColor() + ";\n" +
                    "            stroke-dashoffset: " + (dash + 128) + ";\n" +
                    "            stroke-width: 128;\n" +
                    "          }\n" +
                    "          60% {\n" +
                    "            animation-timing-function: step-end;\n" +
                    "            stroke: " + options.getMidColor() + ";\n" +
                    "            stroke-dashoffset: 0;\n" +
                    "            stroke-width: 128;\n" +
                    "          }\n" +
                    "          to {\n" +
                    "            stroke: " + options.getDrawColor() + ";\n" +
                    "            stroke-width: 1024;\n" +
                    "          }\n" +
                    "        }\n" +
                    "        #animation-" + index + ".animated {\n" +
                    "          animation: keyframes" + index + " " + seconds + "s both;\n" +
                    "          animation-delay: " + (index * seconds) + "s;\n" +
                    "          animation-timing-function: linear;\n" +
                    "        }");


            biHuaPath.add(String.format("\t\t\t<text x=\"%d\" y=\"%d\" style=\"transform-origin:%dpx %dpx; transform:scale(1,-1);\">%d</text>",
                    pointList.get(0).x, pointList.get(0).y, pointList.get(0).x, pointList.get(0).y, index + 1));

            tipPath.add(String.format("\t\t\t<path d=\"%s\" class=\"median-stroke\"></path>", path));
            index += 1;
        }

        HanZiRenderPathBo vo = new HanZiRenderPathBo();
        vo.setAnimalPaths(animalPath)
                .setAnimalStyles(animalStyles)
                .setBiHuaPaths(biHuaPath)
                .setOutlinePaths(outlinePath)
                .setTipPaths(tipPath);
        return vo;
    }

}
