package com.github.liuyueyi.quick.translate.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author yihui
 * @date 2021/6/1
 */
public class UrlUtil {

    /**
     * url参数编码
     *
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(Object params) throws UnsupportedEncodingException {
        String content = URLEncoder.encode(String.valueOf(params), "UTF-8");
        return StringUtils.replace(content, "+", "%20");
    }
}
