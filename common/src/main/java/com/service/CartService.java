package com.service;

import com.dto.CartDTO;
import com.pojo.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CartService {

    int putProductInRedis(CartDTO cartDTO);

    HashMap<Integer,Integer> getCartItem(Integer userId);

    Boolean clearRedisCache(int userId);
}
