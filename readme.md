quick-chinese-transfer
---

简体中文，繁体中文，香港繁体，台湾繁体 相互转换的Java库

本项目主要来自于 

- [https://github.com/hankcs/HanLP](https://github.com/hankcs/HanLP)
- [https://github.com/luhuiguo/chinese-utils](https://github.com/luhuiguo/chinese-utils)


使用姿势
---

**maven依赖**

```xml
<dependency>
    <groupId>com.liuyueyi.quick</groupId>
    <artifactId>transfer-core</artifactId>
    <version>0.1</version>
</dependency>
```

**测试case**

```java
@Test
public void testTrans() {
    String text = "乾坤无极，急急如律令，太阳很大，天气很好，欢迎关注一灰灰blog";
    String out = ChineseUtils.s2t(text);
    System.out.println("s2t -->" + out);
    String hkOut = ChineseUtils.s2hk(text);
    System.out.println("s2hk -->" + hkOut);
    String twOut = ChineseUtils.s2tw(text);
    System.out.println("s2tw --> " + twOut);

    String origin = ChineseUtils.t2s(out);
    System.out.println("t2s -->" + origin);
    System.out.println("hk2s -->" + ChineseUtils.hk2s(hkOut));
    System.out.println("tw2s -->" + ChineseUtils.tw2s(twOut));
}
```