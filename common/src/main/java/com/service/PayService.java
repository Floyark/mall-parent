package com.service;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public interface PayService {

    String getPayQr(ConcurrentHashMap map);

}
