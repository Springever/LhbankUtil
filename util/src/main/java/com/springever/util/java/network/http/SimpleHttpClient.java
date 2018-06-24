package com.springever.util.java.network.http;

import com.lhbank.http.HttpEntity;
import com.lhbank.http.HttpResponse;
import com.lhbank.http.client.methods.HttpPost;
import com.lhbank.http.entity.mime.MultipartEntityBuilder;
import com.lhbank.http.entity.mime.content.FileBody;
import com.lhbank.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * 简单的get、post请求
 */
public class SimpleHttpClient {

    public static void httpPostOrGet() throws IOException {
        String httpUrl = "";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        StringBuilder paramsSB = new StringBuilder();
        paramsSB.append("A=A");
        paramsSB.append("&");
        paramsSB.append("B=B");
        paramsSB.deleteCharAt(paramsSB.length() - 1);
        if (paramsSB.length() > 0) {
            httpUrl = httpUrl + "?" + paramsSB.toString();
        }
        FileBody bin = new FileBody(new File("c:\\xx.txt"));
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("file", bin).build();
        HttpPost post = new HttpPost(httpUrl);
        //HttpGet  get =new HttpGet(httpUrl);
        post.setEntity(reqEntity);
        response = httpclient.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
        } else {
        }
    }
}
