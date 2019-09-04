package com.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dto.CartDTO;
import com.service.CartService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    RedisTemplate redisTemplate;


//     Integer userId;   //主key
//     Integer productId;   //小key
//     Integer productQuantity;  //value

    public int putProductInRedis(CartDTO cartDTO) {

        if(cartDTO.getProductQuantity()==0){
            return 0;
        }
        redisTemplate.opsForHash().put(cartDTO.getUserId(),cartDTO.getProductId(),cartDTO.getProductQuantity());
        return 1;
    }


    //根据用户id获取购物车信息
    public HashMap<Integer,Integer> getCartItem(Integer userId) {
        //1.获取userid下对应的key和value值
        Set keys = redisTemplate.opsForHash().keys(userId);
        if (keys.isEmpty()){
            return null;
        }
        Iterator iterator = keys.iterator();
        //2.返回带有 productId、productQuantity的map
        HashMap<Integer,Integer> hashMap = new HashMap<Integer, Integer>();
        while (iterator.hasNext()){
            int productId = (Integer) iterator.next();
            Integer productQuantity = (Integer) redisTemplate.opsForHash().get(userId,productId);
            hashMap.put(productId,productQuantity);
        }
        return hashMap;
    }

    //清除用户在redis中的购物车信息
    public Boolean clearRedisCache(int userId) {
        Boolean delete = redisTemplate.delete(userId);
        return delete;
    }


}
