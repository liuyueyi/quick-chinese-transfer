quick-chinese-transfer
---

[![Builder](https://travis-ci.org/liuyueyi/quick-chinese-transfer.svg?branch=master)](https://travis-ci.org/liuyueyi/quick-chinese-transfer)
[![Maven Central with version prefix filter](https://img.shields.io/maven-central/v/com.github.liuyueyi/quick-transfer-core.svg)](https://search.maven.org/search?q=a:quick-transfer-core)
[![JitPack](https://jitpack.io/v/liuyueyi/quick-chinese-transfer.svg)](https://jitpack.io/#liuyueyi/quick-chinese-transfer)
[![codecov](https://codecov.io/gh/liuyueyi/quick-chinese-transfer/branch/master/graph/badge.svg)](https://codecov.io/gh/liuyueyi/quick-chinese-transfer)
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/liuyueyi/quick-chinese-transfer.svg)](http://isitmaintained.com/project/liuyueyi/quick-chinese-transfer "Average time to resolve an issue")
[![Percentage of issues still open](http://isitmaintained.com/badge/open/liuyueyi/quick-chinese-transfer.svg)](http://isitmaintained.com/project/liuyueyi/quick-chinese-transfer "Percentage of issues still open")

简体中文，繁体中文，香港繁体，台湾繁体 相互转换的Java库

本项目主要来自于 

- [https://github.com/hankcs/HanLP](https://github.com/hankcs/HanLP)
- [https://github.com/luhuiguo/chinese-utils](https://github.com/luhuiguo/chinese-utils)

词典更新维护来自：

- [https://github.com/hankcs/HanLP/tree/1.x/data/dictionary/tc](https://github.com/hankcs/HanLP/tree/1.x/data/dictionary/tc)

使用姿势
---

**maven依赖**

中央仓库导入依赖

```xml
<!-- https://mvnrepository.com/artifact/com.github.liuyueyi/quick-transfer-core -->
<dependency>
    <groupId>com.github.liuyueyi</groupId>
    <artifactId>quick-transfer-core</artifactId>
    <version>0.2.13</version>
</dependency>
```

**中央仓库 gradle**

```gradle
// https://mvnrepository.com/artifact/com.github.liuyueyi/quick-transfer-core
implementation 'com.github.liuyueyi:quick-transfer-core:0.2.13'
```

**使用jitpack导入依赖**

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
    <version>0.2.13</version>
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
    implementation 'com.github.liuyueyi.quick-chinese-transfer:quick-transfer-core:0.2.11'
}
```


**基本使用演示**

```java
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


高性能接入
---

**词典预加载**

> 当系统对性能要求较高时，可以考虑提前异步加载词典、避免首次使用加载词典导致开销高


```java
// 预热加载所有的词典
ChineseUtils.preLoad(true, TransType.values());

// 预加载简体转繁体词典
ChineseUtils.preLoad(true, TransType.SIMPLE_TO_TRADITIONAL);

// 预加载简繁互转词典
ChineseUtils.preLoad(true, TransType.SIMPLE_TO_TRADITIONAL, TransType.TRADITIONAL_TO_SIMPLE);
```

**卸载**

> 当内存敏感时，提前卸载无用词典减少内存占用

```java
// 卸载所有词典
ChineseUtils.unLoad(TransType.values());

// 卸载简繁互转词典
ChineseUtils.unLoad(TransType.SIMPLE_TO_TRADITIONAL, TransType.TRADITIONAL_TO_SIMPLE);
```

**转换**

ChineseUtils封装了通用的字体转换接口，可以直接通过下面的方式实现转换

```java
ChineseUtils.transfer("一灰灰blog", TransType.SIMPLE_TO_TRADITIONAL);
```


版本说明
---
- 0.2.13
    - [#15 那么"标致"和"溜"繁简转换问题](https://github.com/liuyueyi/quick-chinese-transfer/issues/15)
- 0.2.12
    - [#14 那么”繁简转换问题](https://github.com/liuyueyi/quick-chinese-transfer/issues/14)
- 0.2.11
  - [#13 “福斯”繁简转换问题](https://github.com/liuyueyi/quick-chinese-transfer/issues/13)
- 0.2.10
  - [#12 快取 高速缓存](https://github.com/liuyueyi/quick-chinese-transfer/issues/12) 
- 0.2.9
  - [#11 娘 对象 签简体转繁体](https://github.com/liuyueyi/quick-chinese-transfer/issues/11)
- 0.2.8
  - [#10 翻转简 洛哈=>洛哈特](https://github.com/liuyueyi/quick-chinese-transfer/issues/10)
- 0.2.7
  - [#9 繁转简 通道会变信道](https://github.com/liuyueyi/quick-chinese-transfer/issues/9)
- 0.2.6
  - [#8 繁转简： 那個人=>神秘人](https://github.com/liuyueyi/quick-chinese-transfer/issues/8)
- 0.2.5
    - 删除 `唇<=>脣` 简繁转换
        - [#6 修正簡繁轉換：唇<=>脣](https://github.com/liuyueyi/quick-chinese-transfer/issues/6)
    - 支持同步/异步预加载词典、卸载词典
        - [#7 支持词典异步预加载、卸载](https://github.com/liuyueyi/quick-chinese-transfer/issues/7)
- 0.2.4
    - 繁体转简体
        - [#5 骼 繁转简错误](https://github.com/liuyueyi/quick-chinese-transfer/issues/5)
- 0.2.3
    - 繁体转简体
        - [#4 乾 繁转简错误](https://github.com/liuyueyi/quick-chinese-transfer/issues/4)
- 0.2.2
    - 繁转简
        - [#3 克拉转成克拉布](https://github.com/liuyueyi/quick-chinese-transfer/issues/3)
- 0.2.1
    - 繁转简： fix 奔驰，奶油，黄油转换异常
- 0.2.0
    - 修复链式转换
- 0.1.0
    - 简繁转换