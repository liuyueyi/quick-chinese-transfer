package com.github.liuyueyi.quick.translate.util;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author yihui
 * @date 21/5/31
 */
public class HttpUtil {
    private static final String AGENT_KEY = "User-Agent";
    private static final String AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0";
    private static OkHttpClient client = new OkHttpClient();

    /**
     * get请求
     *
     * @param url    url
     * @param params 参数
     * @return {@link String}
     * @throws IOException IOException
     */
    public static String opsForGet(String url, Map<String, Object> params, Map<String, String> headers) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.append("&")
                    .append(entry.getKey())
                    .append("=")
                    .append(UrlUtil.encode(entry.getValue()));
        }

        Request request = buildRequest(url + builder.toString(), headers).get().build();
        Response re = client.newCall(request).execute();
        return buildResponse(re);
    }

    /**
     * post请求
     *
     * @param url    url
     * @param params 参数个数
     * @return {@link String}
     * @throws IOException IOException
     */
    public static String opsForPost(String url, Map<String, Object> params, Map<String, String> headers) throws IOException {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        params.forEach((k, v) -> bodyBuilder.add(k, String.valueOf(v)));

        Request request = buildRequest(url, headers).post(bodyBuilder.build()).build();
        Response re = client.newCall(request).execute();
        return buildResponse(re);
    }

    /**
     * 构建请求
     *
     * @param url     url
     * @param headers 请求头
     * @return {@link Request.Builder}
     */
    private static Request.Builder buildRequest(String url, Map<String, String> headers) {
        Request.Builder reqBuilder = new Request.Builder()
                .get()
                .header(AGENT_KEY, AGENT_VALUE)
                .url(url);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            reqBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        return reqBuilder;
    }

    /**
     * 建立响应
     *
     * @param re 返回结果
     * @return {@link String}
     * @throws IOException IOException
     */
    private static String buildResponse(Response re) throws IOException {
        ResponseBody body = re.body();
        if (re.isSuccessful() && body != null) {
            return body.string();
        }

        return null;
    }
}
