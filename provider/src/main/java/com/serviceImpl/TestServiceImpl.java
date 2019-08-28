package com.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.service.TestService;

@Service
public class TestServiceImpl implements TestService {


    public String test() {
        return "被调用";
    }
}
