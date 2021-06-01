package com.github.liuyueyi.quick.translate.util;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author yihui
 * @date 21/5/31
 */
public class JsEngine {
    private static ScriptEngine engine;

    static {
        ScriptEngineManager se = new ScriptEngineManager();
        engine = se.getEngineByName("javascript");
    }

    public static String execJs(String js, String method, Object... arguments) throws ScriptException, NoSuchMethodException {
        engine.eval(js);
        Object ans = ((Invocable) engine).invokeFunction(method, arguments);
        return String.valueOf(ans);
    }

}
