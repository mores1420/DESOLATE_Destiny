package org.desolate.FileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File {

    //写入文件
    public void writeFile(String path, String data) {
        try {
            Path Filepath = Paths.get(path);
            if (!Filepath.getParent().toFile().exists()) {
                Filepath.getParent().toFile().mkdirs();
            } else {
                Files.write(Filepath, data.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建新文件
    public static void createNewFile(String path) {
        // 获取文件的路径
        Path file = Paths.get(path);
        // 创建文件的父目录（如果不存在）
        file.getParent().toFile().mkdirs();
    }
}
