package com.example.util;


import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.URLDecoder;
import java.util.Map;
import java.util.UUID;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;


public class TestUtil {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        InputStream is = new FileInputStream(new File("D:\\材料pdf\\testPdf\\ade83e91-2551-48da-9e0e-e893f0fcf2ed.pdf"));
        String result = new httpUtil().uploadFile("http://192.168.1.37:8080/file/atta/uploadSingleFile.do", UUID.randomUUID().toString() + ".pdf", "", is);
        result = URLDecoder.decode(result, "UTF-8");
        Map<String, Object> relMap = com.alibaba.fastjson.JSON.parseObject(result);
        Map<String, Object> resultMap =  (Map<String, Object>) relMap.get("result");
        Object oid = resultMap.get("oid");
    }
}
