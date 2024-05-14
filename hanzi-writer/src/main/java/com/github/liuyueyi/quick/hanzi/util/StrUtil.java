package com.github.liuyueyi.quick.hanzi.util;


import java.util.List;

/**
 * 字符串工具类
 *
 * @author YiHui
 * @date 2024/5/14
 */
public class StrUtil {

    /**
     * 转字符串
     *
     * @param list      列表
     * @param separator 分隔符
     * @return 拼接的字符串
     */
    public static String join(List<String> list, String separator) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, listSize = list.size(); i < listSize; i++) {
            String s = list.get(i);
            if (i != 0) {
                builder.append(separator);
            }
            builder.append(s);
        }
        return builder.toString();
    }

}
