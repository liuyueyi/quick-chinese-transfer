package com.github.liuyueyi.quick.translate.model;

import com.github.liuyueyi.quick.translate.core.BaseTranslator;
import com.github.liuyueyi.quick.translate.core.GoogleTranslator;
import com.github.liuyueyi.quick.translate.core.SoTranslator;

/**
 * @author yihui
 * @date 2021/6/1
 */
public enum TranslatorEnum {
    GOOGLE {
        @Override
        public BaseTranslator build(TransReq req) {
            return new GoogleTranslator(req);
        }
    },
    SO {
        @Override
        public BaseTranslator build(TransReq req) {
            return new SoTranslator(req);
        }
    };

    public abstract BaseTranslator build(TransReq req);
}
