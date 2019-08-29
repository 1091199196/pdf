package com.example.util;

import java.io.File;
import java.util.List;

public class fileDeleteUtil {

    public static void deleteAllFile(String fileDir) {
        File file = new File(fileDir);
        File[] files = file.listFiles();
        for (File file1 : files) {
            file1.delete();
        }
    }

    public static void main(String[] args) {
        deleteAllFile("D:\\材料pdf\\testPdf\\");
    }
}
