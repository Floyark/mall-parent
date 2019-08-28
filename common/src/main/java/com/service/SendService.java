package com.service;


public interface SendService {

    public void sendmail(String mail,String code);
    public void sendMessage(String phoneNumber,String code);

}
