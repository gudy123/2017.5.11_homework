package com.example.a2017511_homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by lenovo on 2017/5/11.
 */

/**定义一个发送Http请求的方法**/
public class HttpUtil {
    public static void sendHttpRequest(final String address, final
                                       HttpCallBackListener listener){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection connection = null;
                    try {
                        URL url = new URL(address);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);
                        connection.setDoInput(true);
                        connection.setDoOutput(true);
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while((line = reader.readLine()) != null){
                            response.append(line);
                        }
                        if(listener != null){
                            //回调onFinish（）函数
                            listener.onFinish(response.toString());
                        }
                    } catch (Exception e) {
                        if(listener != null){
                            // 回调onEror函数
                            listener.onError(e);
                        }
                    } finally {
                        if(connection != null){
                            connection.disconnect();
                        }
                    }
                }
            }).start();
    }
}
