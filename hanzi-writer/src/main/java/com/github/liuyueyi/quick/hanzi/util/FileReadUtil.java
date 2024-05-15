package com.github.liuyueyi.quick.hanzi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件信息读取
 *
 * @author YiHui
 * @date 2024/5/14
 */
public class FileReadUtil {
    public static boolean isAbsFile(String fileName) {
        if (OSUtil.isWinOS()) {
            // windows 操作系统时，绝对地址形如  c:\descktop
            return fileName.contains(":") || fileName.startsWith("\\");
        } else {
            // mac or linux
            return fileName.startsWith("/");
        }
    }

    /**
     * 将用户目录下地址~/xxx 转换为绝对地址
     *
     * @param path
     * @return
     */
    public static String parseHomeDir2AbsDir(String path) {
        String homeDir = System.getProperties().getProperty("user.home");
        return path.replace("~", homeDir);
    }

    /**
     * 按行的方式读取文件
     *
     * @param fileName
     * @return
     */
    public static List<String> readLines(String fileName) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = createLineRead(fileName)) {
            do {
                String line = reader.readLine();
                if (line != null) {
                    list.add(line);
                } else {
                    break;
                }
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static BufferedReader createLineRead(String fileName) throws IOException {
        return new BufferedReader(new InputStreamReader(getStreamByFileName(fileName), StandardCharsets.UTF_8));
    }

    public static InputStream getStreamByFileName(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName should not be null!");
        }

        if (isAbsFile(fileName)) {
            // 绝对路径
            Path path = Paths.get(fileName);
            return Files.newInputStream(path);
        } else if (fileName.startsWith("~")) {
            // 用户目录下的绝对路径文件
            fileName = parseHomeDir2AbsDir(fileName);
            return Files.newInputStream(Paths.get(fileName));
        } else { // 相对路径
            return FileReadUtil.class.getClassLoader().getResourceAsStream(fileName);
        }
    }
}
