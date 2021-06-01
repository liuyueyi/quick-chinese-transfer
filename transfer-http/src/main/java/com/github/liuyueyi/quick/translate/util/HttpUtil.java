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


    public static String opsForGet(String url) throws IOException {
        //创建一个Request
        Request request = new Request.Builder()
                .get()
                .header(AGENT_KEY, AGENT_VALUE)
                .url(url)
                .build();
        Response re = client.newCall(request).execute();
        return buildResponse(re);
    }

    public static String opsForPost(String url, Map<String, String> params) throws IOException {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry: params.entrySet()){
            bodyBuilder.add(entry.getKey(), entry.getValue());
        }

        Request request = new Request.Builder()
                .url(url)
                .header(AGENT_KEY, AGENT_VALUE)
                .post(bodyBuilder.build())
                .build();

        Response re = client.newCall(request).execute();
        return buildResponse(re);
    }

    private static String buildResponse(Response re) throws IOException {
        ResponseBody body = re.body();
        if (re.isSuccessful() && body != null) {
            return body.string();
        }

        return null;
    }
}
