package com.github.liuyueyi.quick.translate.core;

import com.github.liuyueyi.quick.translate.model.HttpMethod;
import com.github.liuyueyi.quick.translate.model.TransReq;
import com.github.liuyueyi.quick.translate.util.JsEngine;

import javax.script.ScriptException;

/**
 * @author yihui
 * @date 21/5/31
 */
public class GoogleTranslator extends BaseTranslator {
    private static final String URL = "http://translate.google.cn/translate_a/single?client=t&sl=%s&tl=%s&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&clearbtn=1&otf=1&pc=1&srcrom=0&ssel=0&tsel=0&kc=2&tk=%s&q=%s";
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

    public static String calculateToken(String msg) throws ScriptException, NoSuchMethodException {
        return JsEngine.execJs(TK_JS, "TL", msg);
    }

    @Override
    public TransReq buildReq() {
        return new TransReq().setMethod(HttpMethod.GET);
    }

    @Override
    protected String url(TransReq req) {
        try {
            return String.format(URL, req.getSourceLanguage(), req.getTargetLanguage(), calculateToken(req.getContent()), req.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}