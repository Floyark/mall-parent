package com.service;



public interface SendService {

    //****发送邮件
    void sendmail(String mail,String subject ,String content);

    //****发送短信
    void sendMessage(String phoneNumber,String subject ,String content);

}
