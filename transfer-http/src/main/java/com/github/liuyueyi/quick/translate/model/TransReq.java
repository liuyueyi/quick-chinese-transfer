package com.github.liuyueyi.quick.translate.model;

import java.io.Serializable;

/**
 * 请求参数
 *
 * @author yihui
 * @date 21/5/28
 */
public class TransReq implements Serializable {
    private static final long serialVersionUID = -6797386145834973020L;
    /**
     * 源语言
     */
    private LanguageEnum sourceLanguage;

    /**
     * 目标语言
     */
    private LanguageEnum targetLanguage;

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
     * http method
     */
    private ReqMethodEnum method;

    /**
     * Method getSourceLanguage returns the sourceLanguage of this TransReq object.
     * <p>
     * 源语言
     *
     * @return the sourceLanguage (type String) of this TransReq object.
     */
    public LanguageEnum getSourceLanguage() {
        return sourceLanguage;
    }

    /**
     * Method setSourceLanguage sets the sourceLanguage of this TransReq object.
     * <p>
     * 源语言
     *
     * @param sourceLanguage the sourceLanguage of this TransReq object.
     * @return TransReq
     */
    public TransReq setSourceLanguage(LanguageEnum sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
        return this;
    }

    /**
     * Method getTargetLanguage returns the targetLanguage of this TransReq object.
     * <p>
     * 目标语言
     *
     * @return the targetLanguage (type String) of this TransReq object.
     */
    public LanguageEnum getTargetLanguage() {
        return targetLanguage;
    }

    /**
     * Method setTargetLanguage sets the targetLanguage of this TransReq object.
     * <p>
     * 目标语言
     *
     * @param targetLanguage the targetLanguage of this TransReq object.
     * @return TransReq
     */
    public TransReq setTargetLanguage(LanguageEnum targetLanguage) {
        this.targetLanguage = targetLanguage;
        return this;
    }

    /**
     * Method getContent returns the content of this TransReq object.
     * <p>
     * 待翻译内容
     *
     * @return the content (type String) of this TransReq object.
     */
    public String getContent() {
        return content;
    }

    /**
     * Method setContent sets the content of this TransReq object.
     * <p>
     * 待翻译内容
     *
     * @param content the content of this TransReq object.
     * @return TransReq
     */
    public TransReq setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Method getAppId returns the appId of this TransReq object.
     * <p>
     * 需要授权的id
     *
     * @return the appId (type String) of this TransReq object.
     */
    public String getAppId() {
        return appId;
    }

    /**
     * Method setAppId sets the appId of this TransReq object.
     * <p>
     * 需要授权的id
     *
     * @param appId the appId of this TransReq object.
     * @return TransReq
     */
    public TransReq setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    /**
     * Method getSalt returns the salt of this TransReq object.
     * <p>
     * 盐
     *
     * @return the salt (type String) of this TransReq object.
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Method setSalt sets the salt of this TransReq object.
     * <p>
     * 盐
     *
     * @param salt the salt of this TransReq object.
     * @return TransReq
     */
    public TransReq setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    /**
     * Method getSign returns the sign of this TransReq object.
     * <p>
     * 签名
     *
     * @return the sign (type String) of this TransReq object.
     */
    public String getSign() {
        return sign;
    }

    /**
     * Method setSign sets the sign of this TransReq object.
     * <p>
     * 签名
     *
     * @param sign the sign of this TransReq object.
     * @return TransReq
     */
    public TransReq setSign(String sign) {
        this.sign = sign;
        return this;
    }

    /**
     * Method getMethod returns the method of this TransReq object.
     * <p>
     * http method
     *
     * @return the method (type ReqMethodEnum) of this TransReq object.
     */
    public ReqMethodEnum getMethod() {
        return method;
    }

    /**
     * Method setMethod sets the method of this TransReq object.
     * <p>
     * http method
     *
     * @param method the method of this TransReq object.
     * @return TransReq
     */
    public TransReq setMethod(ReqMethodEnum method) {
        this.method = method;
        return this;
    }
}
