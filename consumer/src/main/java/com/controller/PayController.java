package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.response.ServerResponse;
import com.service.PayService;
import com.sun.net.httpserver.Authenticator;
import com.util.ResponseUtil;
import com.util.WXPayUtil;
import com.util.ZxingUtil;
import org.jboss.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.util.Map;

@Controller
public class PayController {

    @Reference
    PayService payService;

    /**
     * **支付回调 没法写验证
     */

    @RequestMapping("/payment")
    public ServerResponse payment(HttpServletRequest request,HttpServletResponse response){
        InputStream inputStream = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            inputStream = request.getInputStream();
            String line = null;
            reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            while ((line =reader.readLine())!=null){
                stringBuilder.append(line);
            }
            String xml = stringBuilder.toString();
            System.out.println(xml);
            try {
                boolean isRight = WXPayUtil.isSignatureValid(xml, "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC");
                if(isRight){
                    Map<String,String> data=WXPayUtil.xmlToMap(xml);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseUtil.success();
    }


    //****显示二维码图片
    @RequestMapping("/getQrPic")
    public void getQrPic(String code, HttpServletResponse response){
        System.out.println(code);
        String decode = URLDecoder.decode(code);
        System.out.println(decode);
        BufferedImage qrPic = ZxingUtil.createImage(decode,500,500);

        try {
             ImageIO.write(qrPic, "JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
