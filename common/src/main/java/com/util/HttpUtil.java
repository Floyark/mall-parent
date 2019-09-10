package com.util;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.common.json.ParseException;
import com.exception.MyException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static String post(String address,String xml){
        HttpURLConnection connection = null;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            URL url = new URL(address); //url地址
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);   //设置该连接可输入
            connection.setDoOutput(true);   //设置该连接可输出
            connection.setRequestMethod("POST");   //设置请求方式
            connection.setUseCaches(false);  //不使用缓存
            connection.setInstanceFollowRedirects(true);  //设置是否自动处理重定向
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");  //设置header信息
            connection.connect();

            PrintWriter writer = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            writer.print(xml);
            writer.flush();
            writer.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));

            int n =-1;
            String string="";
            while ((string = reader.readLine())!=null){
                stringBuffer.append(string).append("\n");
            }
        }catch (Exception e){
            throw new MyException(e.getMessage());
        }finally {
            connection.disconnect();
        }

        return stringBuffer.toString();
    }
}
