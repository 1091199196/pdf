package com.readTxt;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class read {
    private static final Integer ONE = 1;

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\86187\\Desktop\\CINVS_dengfeng.txt")),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                System.out.println(lineTxt);
                Thread.currentThread().sleep(500);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
