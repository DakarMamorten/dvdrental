package com.dvdrental.connection;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpExample {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("https://docs.google.com/spreadsheets/d/1LPFePQ_X35eyvn4Dch__bikvNakQz21xg6EX8c8hDZo/edit#gid=502067853");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        String body = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(body);

        HttpPost httpPost = new HttpPost("https://docs.google.com/spreadsheets/d/1LPFePQ_X35eyvn4Dch__bikvNakQz21xg6EX8c8hDZo/edit#gid=502067853");
        httpPost.setHeader("Data","data");
        httpResponse = httpClient.execute(httpPost);
        body = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(body);
    }
}
