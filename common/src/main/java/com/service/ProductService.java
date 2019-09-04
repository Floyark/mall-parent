package com.service;

import com.dto.MainTableDTO;
import com.github.pagehelper.PageInfo;
import com.pojo.Product;
import com.vo.CartVO;
import com.vo.ProductDetailVO;
import com.vo.ProductInfoVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {

    //根据id查询信息
    Product getProductById(Integer id);

    //根据查询条件查询信息
    PageInfo<Product> products(MainTableDTO mainTableDTO);

    //根据id删除信息
    int deleteProductById(Integer id);

    //更新商品信息
    int updateProductInfo(Product product);

    //新增商品信息
    void InsertProductInfo(Product product);

    //给主页面展示商品
    PageInfo<ProductInfoVO> getProductForMain(String productName,Integer page);

    //显示购物车信息
    List<CartVO> getCartProductInfo(HashMap<Integer, Integer> cartItem);

    //根据productId，productQuantity减库存
    int updateProductQuantityById(String orderId);

}
