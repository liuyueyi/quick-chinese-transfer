package com.github.liuyueyi.quick.translate.model;

/**
 * @author yihui
 * @date 21/5/28
 */
public class TransReq {
    /**
     * 源语言
     */
    private String sourceLanguage;

    /**
     * 目标语言
     */
    private String targetLanguage;

    /**
     * 待翻译内容
     */
    private String content;

    /**
     * 需要授权的id
     */
    private String appId;

    /**
     * 盐
     */
    private String salt;

    /**
     * 签名
     */
    private String sign;

    /**
     * get or post
     */
    private HttpMethod method;

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public TransReq setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
        return this;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public TransReq setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
        return this;
    }

    public String getContent() {
        return content;
    }

    public TransReq setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public TransReq setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public TransReq setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public TransReq setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public TransReq setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }
}
