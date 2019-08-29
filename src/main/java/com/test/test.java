package com.test;

import com.alibaba.fastjson.JSONObject;

public class test {

    public static boolean isNotEmpty(String str){
        if(str!=null && !str.equals("")){
            return true;
        }
        return false;
    }

   /* public static void main(String[] args) {
        JSONObject.toJSONString();
    }*/
}
