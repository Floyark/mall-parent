package com.util;

import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.*;

public class WXSignUtil {

    public static HashMap<String,String> hashMap;
    static {
        hashMap= new HashMap<String, String>();
        hashMap.put("appid","wx632c8f211f8122c6");
        hashMap.put("mch_id","1497984412");
        hashMap.put("nonce_str",System.currentTimeMillis()+"");
        hashMap.put("body","腾讯会员充值");
        hashMap.put("out_trade_no",System.currentTimeMillis()+"");
        hashMap.put("total_fee","1");
        hashMap.put("spbill_create_ip","111.203.254.34");
        hashMap.put("notify_url","http://dmg4wp.natappfree.cc/payment");
        hashMap.put("trade_type","NATIVE");
    }

    public static TreeMap<String,String> sortParam(Map<String, String> map) {
        TreeMap <String,String> treeMap = new TreeMap<String,String>();
        Set<String> strings = map.keySet();
         List<String> list = new ArrayList<String>(strings);
         Collections.sort(list);
        for(String str : list){
            treeMap.put(str,map.get(str));
        }
        return treeMap;
    }

    public static String getSign(){
        return "";
    }

    public static void main(String[] args) {
        TreeMap<String, String> treeMap = WXSignUtil.sortParam(hashMap);
        Set<String> strings = treeMap.keySet();
        StringBuffer buffer = new StringBuffer();
        for (String string : strings) {
            buffer.append(string).append("=").append(treeMap.get(string)).append("&");
        }
        //排序参数
        String substring = buffer.substring(0, buffer.length() - 1);
        //拼接密匙
        String signTemp = substring+"&key=";

        WXSignUtil.getSign();
    }


}
