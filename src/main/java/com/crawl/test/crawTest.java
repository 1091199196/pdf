package com.crawl.test;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class crawTest {

    public static void main(String[] args) {
        httpPost();
    }

    public static void httpPost() {
        HttpClient httpClient = new HttpClient();
        // set timeOut
        httpClient.getParams().setConnectionManagerTimeout(5000);
        GetMethod getMethod = new GetMethod("https://www.xs8.cn/");
        // timeOut
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 3000);
        // try again timeOut
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        try {
            httpClient.executeMethod(getMethod);
            byte[] responseBody = getMethod.getResponseBody();
            String content = new String(responseBody, "UTF-8");
            getMethod.getResponseHeaders();
            Document parse = Jsoup.parse(content,"https://www.xs8.cn/");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
