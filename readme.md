quick-chinese-transfer
---

[![Builder](https://travis-ci.org/liuyueyi/quick-chinese-transfer.svg?branch=master)](https://travis-ci.org/liuyueyi/quick-chinese-transfer)
[![JitPack](https://jitpack.io/v/liuyueyi/quick-chinese-transfer.svg)](https://jitpack.io/#liuyueyi/quick-chinese-transfer)
[![codecov](https://codecov.io/gh/liuyueyi/quick-chinese-transfer/branch/master/graph/badge.svg)](https://codecov.io/gh/liuyueyi/quick-chinese-transfer)
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/liuyueyi/quick-chinese-transfer.svg)](http://isitmaintained.com/project/liuyueyi/quick-chinese-transfer "Average time to resolve an issue")
[![Percentage of issues still open](http://isitmaintained.com/badge/open/liuyueyi/quick-chinese-transfer.svg)](http://isitmaintained.com/project/liuyueyi/quick-chinese-transfer "Percentage of issues still open")

简体中文，繁体中文，香港繁体，台湾繁体 相互转换的Java库

本项目主要来自于 

- [https://github.com/hankcs/HanLP](https://github.com/hankcs/HanLP)
- [https://github.com/luhuiguo/chinese-utils](https://github.com/luhuiguo/chinese-utils)


使用姿势
---

**maven依赖**

中央仓库导入依赖

```xml
<dependency>
    <groupId>com.github.liuyueyi</groupId>
    <artifactId>quick-transfer-core</artifactId>
    <version>0.1.2</version>
</dependency>
```

使用jitpack导入依赖

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.liuyueyi.quick-chinese-transfer</groupId>
    <artifactId>quick-transfer-core</artifactId>
    <version>0.1.2</version>
</dependency>
```

**gradle依赖**

```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
    implementation 'com.github.liuyueyi.quick-chinese-transfer:quick-transfer-core:0.1-s'
}
```


**测试case**

```java
@Test
public void testTrans() {
    String text = "这斜月三星洞…… 长寿面，孙悟空，猪八戒，唐僧，沙和尚，白龙马，李靖，托塔天王, 戏说西游，许多人都这样说，收拾一下，拾金不昧；纔=才";
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

输出结果如下

```text
s2t -->這斜月三星洞…… 長壽麪，孫悟空，豬八戒，唐僧，沙和尚，白龍馬，李靖，托塔天王, 戲說西遊，許多人都這樣說，收拾一下，拾金不昧；纔=才
s2hk -->這斜月三星洞…… 長壽麪，孫悟空，豬八戒，唐僧，沙和尚，白龍馬，李靖，托塔天王, 戲説西遊，許多人都這樣説，收拾一下，拾金不昧；才=才
s2tw --> 這斜月三星洞…… 長壽麵，孫悟空，豬八戒，唐僧，沙和尚，白龍馬，李靖，托塔天王, 戲說西遊，許多人都這樣說，收拾一下，拾金不昧；才=才
t2s -->这斜月三星洞…… 长寿面，孙悟空，猪八戒，唐僧，沙和尚，白龙马，李靖，托塔天王, 戏说西游，许多人都这样说，收拾一下，拾金不昧；才=才
hk2s -->这斜月三星洞…… 长寿面，孙悟空，猪八戒，唐僧，沙和尚，白龙马，李靖，托塔天王, 戏说西游，许多人都这样说，收拾一下，拾金不昧；才=才
tw2s -->这斜月三星洞…… 长寿面，孙悟空，猪八戒，唐僧，沙和尚，白龙马，李靖，托塔天王, 戏说西游，许多人都这样说，收拾一下，拾金不昧；才=才
```
