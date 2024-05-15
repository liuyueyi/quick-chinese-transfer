package com.github.liuyueyi.quick.hanzi.util;

/**
 * @author YiHui
 * @date 2024/5/14
 */
public class OSUtil {
    public static boolean isWinOS() {
        boolean isWinOS = false;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            String sharpOsName = osName.replaceAll("windows", "{windows}").replaceAll("^win([^a-z])", "{windows}$1")
                    .replaceAll("([^a-z])win([^a-z])", "$1{windows}$2");
            isWinOS = sharpOsName.contains("{windows}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isWinOS;
    }
}
