package com.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.service.PayService;
import com.util.HttpUtil;
import com.util.WXPayUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PayServiceImpl implements PayService {

    private static Map<String ,String> map = new HashMap<String, String>();

    private final String SUCCESS="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    private final String ERROR="<xml><return_code><![CDATA[ERROR]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";

    static {
        map= new HashMap<String, String>();
        map.put("appid","wx632c8f211f8122c6");
        map.put("mch_id","1497984412");
        map.put("nonce_str",System.currentTimeMillis()+"");
        map.put("body","腾讯会员充值");
        map.put("out_trade_no",System.currentTimeMillis()+"");
        map.put("total_fee","1");
        map.put("spbill_create_ip","111.203.254.34");
        map.put("notify_url","http://qrit6r.natappfree.cc/payment");
        map.put("trade_type","NATIVE");
    }
    //****返回二维码字符串
    public String getPayQr(ConcurrentHashMap productMap) {

        Set set = productMap.keySet();
        Iterator iterator = set.iterator();
        Integer key =0;
        while (iterator.hasNext()){
            key = (Integer) iterator.next();
            System.out.println("productId:"+key+"----quantity:"+productMap.get(key));
        }

        String xml = null;
        try {
            xml = WXPayUtil.generateSignedXml(map,"sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String post = HttpUtil.post("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
        Map<String, String> stringStringMap = null;
        try {
            stringStringMap = WXPayUtil.xmlToMap(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String code_url = stringStringMap.get("code_url");
        return code_url;
    }
    //****判断
}
