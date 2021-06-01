package com.github.liuyueyi.quick.translate;

import com.github.liuyueyi.quick.translate.core.GoogleTranslator;
import com.github.liuyueyi.quick.translate.model.TransReq;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;

/**
 * @author yihui
 * @date 21/5/28
 */
public class TranslateUtil {

    public static String execJs(String msg) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager se = new ScriptEngineManager();
        ScriptEngine engine = se.getEngineByName("javascript");
        engine.eval("function TL(a) { \n" +
                "        var k = \"\"; \n" +
                "        var b = 406644; \n" +
                "        var b1 = 3293161072; \n" +
                "        var jd = \".\"; \n" +
                "        var $b = \"+-a^+6\"; \n" +
                "        var Zb = \"+-3^+b+-f\"; \n" +
                "        for (var e = [], f = 0, g = 0; g < a.length; g++) { \n" +
                "            var m = a.charCodeAt(g); \n" +
                "            128 > m ? e[f++] = m : (2048 > m ? e[f++] = m >> 6 | 192 : (55296 == (m & 64512) && g + 1 < a.length && 56320 == (a.charCodeAt(g + 1) & 64512) ? (m = 65536 + ((m & 1023) << 10) + (a.charCodeAt(++g) & 1023), \n" +
                "            e[f++] = m >> 18 | 240, \n" +
                "            e[f++] = m >> 12 & 63 | 128) : e[f++] = m >> 12 | 224, \n" +
                "            e[f++] = m >> 6 & 63 | 128), \n" +
                "            e[f++] = m & 63 | 128) \n" +
                "        } \n" +
                "        a = b; \n" +
                "        for (f = 0; f < e.length; f++) a += e[f], \n" +
                "        a = RL(a, $b); \n" +
                "        a = RL(a, Zb); \n" +
                "        a ^= b1 || 0; \n" +
                "        0 > a && (a = (a & 2147483647) + 2147483648); \n" +
                "        a %= 1E6; \n" +
                "        return a.toString() + jd + (a ^ b) \n" +
                "    }; \n" +
                "    function RL(a, b) { \n" +
                "        var t = \"a\"; \n" +
                "        var Yb = \"+\"; \n" +
                "        for (var c = 0; c < b.length - 2; c += 3) { \n" +
                "            var d = b.charAt(c + 2), \n" +
                "            d = d >= t ? d.charCodeAt(0) - 87 : Number(d), \n" +
                "            d = b.charAt(c + 1) == Yb ? a >>> d: a << d; \n" +
                "            a = b.charAt(c) == Yb ? a + d & 4294967295 : a ^ d \n" +
                "        } \n" +
                "        return a \n" +
                "    } ");


        Object ans = ((Invocable) engine).invokeFunction("TL", msg);
        System.out.println(ans);
        return String.valueOf(ans);
    }

    public static void main(String[] args) throws IOException {
        String msg = "hello world";

        GoogleTranslator translator = new GoogleTranslator();
        TransReq req = translator.buildReq();
        req.setSourceLanguage("en").setTargetLanguage("zh-CN").setContent(msg);
        String res = translator.translate(req);
        System.out.println(res);
    }
}
