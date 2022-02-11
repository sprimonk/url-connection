package com.sinha.learn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.flickr.com/services/feeds/photos_public.gne?tags=cats");
            //below line only creates a connection but does not actually connect. So it just a place where you can
            //config your connection.
            //URLConnection urlConnection = url.openConnection();
            //urlConnection.setDoOutput(true);
            //urlConnection.connect();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent","Chrome");
            connection.setReadTimeout(30000);

            //below code implicitly establishes the network connection so we dont have to explicitly call the connect
            //method in this case.
            int response = connection.getResponseCode();
            if (response!=200){
                System.out.println("something wrong with the connection");
                return;
            }

            //Below code is to fetch the headers being used by the above URL.
            Map<String, List<String>> headerFields = connection.getHeaderFields();
            for (Map.Entry<String,List<String>> entry: headerFields.entrySet()){
                String key = entry.getKey();
                List<String> value = entry.getValue();
                System.out.println("Key :" + key);
                for(String string : value){
                    System.out.println("value :" + value);
                }
            }

            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while(line!=null){
                line = input.readLine();
                System.out.println(line);
            }
            input.close();
        }catch (MalformedURLException e){
            System.out.println("URL is bad :" + e.getMessage());
        }catch (IOException e){
            System.out.println("IO exception :" + e.getMessage());
        }
    }
}
