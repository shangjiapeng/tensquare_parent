package com.tensquare.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>文件工具类</p>
 * 用于文本文件的读 取，合并，以及获取某目录下的文本文件名称
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-30 16:18
 */
public class FileUtil {

    /**
     * 将多个文本文件合并为一个文本文件
     *
     * @param outFileName 输出文件的文件名
     * @param inFileNames 输入文件的文件名集合
     * @throws IOException
     */
    public static void merge(String outFileName, List<String> inFileNames) throws IOException {

        FileWriter fileWriter = new FileWriter(outFileName, false);
        for (String inFileName : inFileNames) {
            try {
                String text = readToString(inFileName);
                fileWriter.write(text);
                System.out.println(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fileWriter.close();
    }

    /**
     * 查找某目录下的所有文件名称
     *
     * @param path
     * @return
     */
    public static List<String> getFiles(String path) {

        List<String> fielNames = new ArrayList<>();
        File file = new File(path);
        File[] tempfiles = file.listFiles();
        if (tempfiles!=null){
            for (File tempfile : tempfiles) {
                //如果是文件
                if (tempfile.isFile()) {
                    fielNames.add(tempfile.toString());
                }
                //如果是文件夹
                if (tempfile.isDirectory()) {
                    fielNames.addAll(Objects.requireNonNull(getFiles(tempfile.toString())));
                }
            }
            return fielNames;
        }
        return null;
    }


    /**
     * 读取文本文件内容到字符串
     *
     * @param fileName
     * @return
     */
    public static String readToString(String fileName) throws IOException {

        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream inputStream = new FileInputStream(file);
        int read = inputStream.read(filecontent);
        inputStream.close();
        return new String(filecontent, encoding);
    }
}
