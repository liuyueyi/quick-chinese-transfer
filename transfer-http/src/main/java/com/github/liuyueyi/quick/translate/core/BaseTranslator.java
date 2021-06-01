package com.github.liuyueyi.quick.translate.core;

import com.github.liuyueyi.quick.translate.model.ReqMethodEnum;
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
    protected TransReq req;

    public BaseTranslator(TransReq req) {
        this.req = req;
        initReq();
    }

    /**
     * 初始化请求参数
     */
    protected abstract void initReq();

    /**
     * 建立参数
     *
     * @param req 要求的事情
     * @return {@link Map<String, Object>}
     * @throws Exception 异常
     */
    protected abstract Map<String, Object> buildParams(TransReq req) throws Exception;

    protected Map<String, String> buildHeaders() {
        return Collections.emptyMap();
    }

    /**
     * 请求的url模板
     *
     * @return
     */
    protected abstract String url();

    /**
     * 执行翻译
     *
     * @return
     * @throws IOException
     */
    public String translate() throws Exception {
        String url = url();

        String response = null;
        if (req.getMethod() == ReqMethodEnum.GET) {
            response = HttpUtil.opsForGet(url, buildParams(req), buildHeaders());
        } else if (req.getMethod() == ReqMethodEnum.POST_FORM) {
            response = HttpUtil.opsForPost(url, buildParams(req), buildHeaders());
        }
        return buildResponse(response);
    }

    /**
     * 构建返回结果
     *
     * @param response
     * @return
     */
    protected String buildResponse(String response) {
        return response;
    }
}
