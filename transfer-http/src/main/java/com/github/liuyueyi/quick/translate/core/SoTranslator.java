package com.github.liuyueyi.quick.translate.core;

import com.github.liuyueyi.quick.translate.model.LanguageEnum;
import com.github.liuyueyi.quick.translate.model.ReqMethodEnum;
import com.github.liuyueyi.quick.translate.model.TransReq;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 360翻译
 *
 * @author yihui
 * @date 2021/6/1
 */
public class SoTranslator extends BaseTranslator {
    public SoTranslator(TransReq req) {
        super(req);
    }

    @Override
    protected void initReq() {
        req.setMethod(ReqMethodEnum.POST_FORM);
    }

    @Override
    protected Map<String, Object> buildParams(TransReq req) throws Exception {
        Map<String, Object> params = new HashMap<>(8);
        if (req.getSourceLanguage() == LanguageEnum.CHINESE) {
            params.put("eng", 0);
        } else {
            params.put("eng", 1);
        }
        params.put("query", req.getContent());
        return params;
    }

    @Override
    protected Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>(4);
        headers.put("pro", "fanyi");
        return headers;
    }

    @Override
    protected String url() {
        return "https://fanyi.so.com/index/search?validate=&ignore_trans=0";
    }

    @Override
    protected String buildResponse(String response) {
        JsonObject obj = JsonParser.parseString(response).getAsJsonObject().get("data").getAsJsonObject();
        return obj.get("fanyi").getAsString();
    }
}
