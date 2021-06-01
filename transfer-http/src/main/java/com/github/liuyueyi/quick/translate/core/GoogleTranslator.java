package com.github.liuyueyi.quick.translate.core;

import com.github.liuyueyi.quick.translate.model.ReqMethodEnum;
import com.github.liuyueyi.quick.translate.model.TransReq;
import com.github.liuyueyi.quick.translate.util.JsEngine;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

/**
 * 谷歌翻译
 *
 * @author yihui
 * @date 21/5/31
 */
public class GoogleTranslator extends BaseTranslator {
    private static final String URL = "http://translate.google.cn/translate_a/single?client=t&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&clearbtn=1&otf=1&pc=1&srcrom=0&ssel=0&tsel=0&kc=2";
    private static final String TK_JS = "function TL(a) { \n" +
            "var k = \"\"; \n" +
            "var b = 406644; \n" +
            "var b1 = 3293161072; \n" +
            "var jd = \".\"; \n" +
            "var $b = \"+-a^+6\"; \n" +
            "var Zb = \"+-3^+b+-f\"; \n" +
            "for (var e = [], f = 0, g = 0; g < a.length; g++) { \n" +
            "    var m = a.charCodeAt(g); \n" +
            "    128 > m ? e[f++] = m : (2048 > m ? e[f++] = m >> 6 | 192 : (55296 == (m & 64512) && g + 1 < a.length && 56320 == (a.charCodeAt(g + 1) & 64512) ? (m = 65536 + ((m & 1023) << 10) + (a.charCodeAt(++g) & 1023), \n" +
            "    e[f++] = m >> 18 | 240, \n" +
            "    e[f++] = m >> 12 & 63 | 128) : e[f++] = m >> 12 | 224, \n" +
            "    e[f++] = m >> 6 & 63 | 128), \n" +
            "    e[f++] = m & 63 | 128) \n" +
            "} \n" +
            "a = b; \n" +
            "for (f = 0; f < e.length; f++) a += e[f], \n" +
            "a = RL(a, $b); \n" +
            "a = RL(a, Zb); \n" +
            "a ^= b1 || 0; \n" +
            "0 > a && (a = (a & 2147483647) + 2147483648); \n" +
            "a %= 1E6; \n" +
            "return a.toString() + jd + (a ^ b) \n" +
            "    }; \n" +
            "    function RL(a, b) { \n" +
            "var t = \"a\"; \n" +
            "var Yb = \"+\"; \n" +
            "for (var c = 0; c < b.length - 2; c += 3) { \n" +
            "    var d = b.charAt(c + 2), \n" +
            "    d = d >= t ? d.charCodeAt(0) - 87 : Number(d), \n" +
            "    d = b.charAt(c + 1) == Yb ? a >>> d: a << d; \n" +
            "    a = b.charAt(c) == Yb ? a + d & 4294967295 : a ^ d \n" +
            "} \n" +
            "return a \n" +
            "} ";

    /**
     * Constructor GoogleTranslator creates a new GoogleTranslator instance.
     *
     * @param req of type TransReq
     */
    public GoogleTranslator(TransReq req) {
        super(req);
    }

    /**
     * 计算用于签名的token
     *
     * @param msg 请求翻译内容
     * @return {@link String}
     * @throws ScriptException       脚本异常
     * @throws NoSuchMethodException 没有这样的方法异常
     */
    public String calculateToken(String msg) throws ScriptException, NoSuchMethodException {
        return JsEngine.execJs(TK_JS, "TL", msg);
    }

    /**
     * 构建请求参数
     *
     * @return
     */
    @Override
    public void initReq() {
        req.setMethod(ReqMethodEnum.GET);
    }

    /**
     * 请求参数构建
     *
     * @param req
     * @return
     * @see BaseTranslator#buildParams(TransReq)
     */
    @Override
    protected Map<String, Object> buildParams(TransReq req) throws Exception {
        Map<String, Object> params = new HashMap<>(8);
        params.put("sl", req.getSourceLanguage().getCode());
        params.put("tl", req.getTargetLanguage().getCode());
        params.put("tk", calculateToken(req.getContent()));
        params.put("q", req.getContent());
        return params;
    }

    /**
     * 请求的url模板
     *
     * @return
     */
    @Override
    protected String url() {
        return URL;
    }

    /**
     * 构建返回结果
     *
     * @param response
     * @return
     */
    @Override
    protected String buildResponse(String response) {
        StringBuilder res = new StringBuilder();
        JsonArray jsonArray = JsonParser.parseString(response).getAsJsonArray();
        JsonArray contents = jsonArray.get(0).getAsJsonArray();
        for (int i = 0; i < contents.size(); i++) {
            JsonElement target = contents.get(i);
            if (!target.isJsonArray()) {
                continue;
            }

            JsonElement transContent = target.getAsJsonArray().get(0);
            if (transContent == null || transContent.isJsonNull() || "null".equals(transContent.getAsString())) {
                continue;
            }
            res.append(target.getAsJsonArray().get(0).getAsString());
        }
        return res.toString();
    }
}