package com.github.liuyueyi.quick.test;

import com.github.liuyueyi.quick.translate.TranslateUtil;
import com.github.liuyueyi.quick.translate.model.LanguageEnum;
import com.github.liuyueyi.quick.translate.model.TranslatorEnum;

/**
 * @author yihui
 * @date 2021/6/1
 */
public class TranslateUtilTest {

    public static void main(String[] args) throws Exception {
        String content = "最热最全的免费小说阅读器，全网精品小说，火热新书，追书神器，阅读必备，百万小说爱好者首选阅读器\n" +
                "\n" +
                "火爆新书热书全场免费畅读，爱生活，爱阅读，快乐追更; 一款免费书籍搜索、阅读APP，你值得拥有；\n" +
                "\n" +
                "【热门分类】\n" +
                "\n" +
                "追书免费小说，汇集都市言情、玄幻奇幻、武侠仙侠、青春校园、穿越架空、惊悚悬疑、耽美同人、游戏竞技、科幻灵异、职场励志等海量热门小说内容，连载小说及时更新，书荒从此说再见！\n" +
                "\n" +
                "【热门广场】\n" +
                "\n" +
                "高人气广场栏目，优质书单推荐，个性主题、背景一键导入，排忧解闷段子专场，书友书单互换等大批功能排队上线中...\n" +
                "\n" +
                "【实时更新】\n" +
                "\n" +
                "实时跨网站搜索全网热门小说，与作者更新云同步\n" +
                "\n" +
                "【软件特色】\n" +
                "\n" +
                "体积虽小，五脏俱全，已支持功能包括但不限于以下：\n" +
                "\n" +
                "0. 根据系统文字自动识别简繁体，支持简繁转换\n" +
                "1. 全网小说搜索，添加书架，阅读\n" +
                "2. 四种翻页特效，支持自动阅读，听书\n" +
                "3. 黑夜主题，自定义主题，字体、大小、间距、背景都可以由您自己定义\n" +
                "4. 文字替换，净化阅读内容\n" +
                "5. 列表、九宫格两种书架样式，支持书架分类，书籍置顶\n" +
                "6. 更多书源一键拉取，目前也支持自定义修改书源规则哦\n" +
                "7. 一键备份/恢复，书单永不丢失\n" +
                "8. 全本缓存，离线下载，没网也可以阅读\n" +
                "9. 发现页，各种书单，解决书荒难题\n" +
                "10. 本地小说，epub图文完美支持\n" +
                "11. 免费，全网小说免费阅读\n" +
                "12.有声小说支持（手机TTS包含语音包即可使用有声，小说阅读页点击耳机图标即可开启）\n" +
                "13. 首个支持上下阅读模式，支持从右向左的阅读器\n" +
                "\n" +
                "【使用说明】\n" +
                "\n" +
                "- 首次安装，进入侧边菜单，点击书源管理，点击右上角菜单图标，选中默认书源导入，400+优质书源满足您的阅读需求哦\n" +
                "- 手机TTS包含语音包，即可开启有声阅读模式\n";

        String result =
                TranslateUtil.build(LanguageEnum.CHINESE, LanguageEnum.ENGLISH).select(TranslatorEnum.GOOGLE).translate(content);
        System.out.println("google translate:\n" + result);


        String so =
                TranslateUtil.build(LanguageEnum.CHINESE, LanguageEnum.ENGLISH).select(TranslatorEnum.SO).translate(content);
        System.out.println("360 translate: \n" + so);
    }

}
