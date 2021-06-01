package com.github.liuyueyi.quick.translate;

import com.github.liuyueyi.quick.translate.core.BaseTranslator;
import com.github.liuyueyi.quick.translate.model.LanguageEnum;
import com.github.liuyueyi.quick.translate.model.TransReq;
import com.github.liuyueyi.quick.translate.model.TranslatorEnum;

/**
 * @author yihui
 * @date 21/5/28
 */
public class TranslateUtil {

    private TransReq req;

    private BaseTranslator translator;

    public static TranslateUtil build(LanguageEnum source, LanguageEnum target) {
        TranslateUtil util = new TranslateUtil();
        util.req = new TransReq().setSourceLanguage(source).setTargetLanguage(target);
        return util;
    }

    public TranslateUtil select(TranslatorEnum translator) {
        this.translator = translator.build(req);
        return this;
    }

    public String translate(String content) throws Exception {
        req.setContent(content);
        return this.translator.translate();
    }
}
