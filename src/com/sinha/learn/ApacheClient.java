package com.sinha.learn;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApacheClient {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://example.org");
        request.addHeader("User-Agent","Chrome");
        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(request);
            System.out.println(response.getCode());
            BufferedReader pageReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            while((line = pageReader.readLine())!=null){
                System.out.println(line);
            }
            pageReader.close();
        }catch (IOException e){
            System.out.println("Exception :" + e.getMessage());
        }finally {
            try{
                response.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
