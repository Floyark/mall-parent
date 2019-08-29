package com.service;


public interface SendService {

    void sendmail(String mail,String code);
    void sendMessage(String phoneNumber,String code);

}
