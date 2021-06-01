package com.github.liuyueyi.quick.translate.core;

import com.github.liuyueyi.quick.translate.model.HttpMethod;
import com.github.liuyueyi.quick.translate.model.TransReq;
import com.github.liuyueyi.quick.translate.util.HttpUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * @author yihui
 * @date 21/5/28
 */
public abstract class BaseTranslator {

    /**
     * 构建请求参数
     *
     * @return
     */
    public abstract TransReq buildReq();

    /**
     * url template
     *
     * @return
     */
    protected abstract String url(TransReq req);

    protected Map<String, String> buildParams(TransReq req) {
        return Collections.emptyMap();
    }

    public String translate(TransReq req) throws IOException {
        String url = url(req);
        if (req.getMethod() == HttpMethod.GET) {
            return HttpUtil.opsForGet(url);
        } else if (req.getMethod() == HttpMethod.POST){
            return HttpUtil.opsForPost(url, buildParams(req));
        }
        return "";
    }
}
