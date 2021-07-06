package com.cn.exam.restful.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 *@Author fengzhilong
 *@Date 2021/7/6 15:39
 *@Desc
 **/
@Slf4j
public class PostRequestTest {


    public static void getRequestTest(String url, Integer count) {
        long beginTime = System.currentTimeMillis();
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");

            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            log.info("[测试] ## threadCnt ->{}; ## statusCode ->{}", count, statusCode);
            String str_res = EntityUtils.toString(response.getEntity());
            log.info("[测试] ## response ->{}", str_res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            long endTime = System.currentTimeMillis(); //结束时间
            log.info("[测试] ## Time-consuming ->{}", (endTime - beginTime));
        }


    }
}
