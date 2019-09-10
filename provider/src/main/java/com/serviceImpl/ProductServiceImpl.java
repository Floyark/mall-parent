package com.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dto.MainTableDTO;
import com.dto.QuantityDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.ProductMapper;
import com.pojo.Product;
import com.service.ProductService;
import com.vo.CartVO;
import com.vo.ProductInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = ProductService.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    public Product getProductById(Integer id) {
        Product productInfoById = productMapper.getProductInfoById(id);
        return productInfoById;
    }

    //根据查询条件查询商品数据
    public PageInfo<Product> products(MainTableDTO mainTableDTO) {
        PageHelper.startPage(mainTableDTO.getPage(),mainTableDTO.getLimit());
        List<Product> productInfo = productMapper.getProductInfo(mainTableDTO);
        PageInfo pageInfo = new PageInfo(productInfo);
        return pageInfo;
    }

    @Transactional
    public int deleteProductById(Integer id) {
        int result = productMapper.deleteProductById(id);
        return result;
    }

    @Transactional
    public int updateProductInfo(Product product) {
        int i = productMapper.updateProductInfo(product);
        if(i!=1){
            throw new RuntimeException("更新失败");
        }
        return i;
    }

    public void InsertProductInfo(Product product) {
        int i = productMapper.insertInfo(product);
        if(i!=1){
            throw new RuntimeException("增加异常");
        }
    }

    public PageInfo<ProductInfoVO> getProductForMain(String productName,Integer page) {
        PageHelper.startPage(page,6);
        List<ProductInfoVO> list = productMapper.getProductForMain(productName);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    //根据map中的productId productQuantity 封装CartVO
    public List<CartVO> getCartProductInfo(HashMap<Integer, Integer> cartItem) {

        List<CartVO> list = new ArrayList<CartVO>();
        //将所有productId放入set中
        Set<Integer> productIds = cartItem.keySet();
        //遍历set，获取每个productId
        Iterator<Integer> iterator = productIds.iterator();
        while (iterator.hasNext()){
            Integer productId = iterator.next();
            //通过productId获取商品信息
            CartVO cartVO = productMapper.getCartVoById(productId);
            //获取productQuantity
            Integer productQuantity = cartItem.get(productId);
            //封装到vo中
            cartVO.setQuantity(productQuantity);
            list.add(cartVO);
        }
        return list;
    }

    //根据map传递参数，修改库存
    @Transactional
    public int updateProductQuantityById(String orderId) {
        //1.获取order中所有的商品的购买数量
        List<QuantityDTO> orderItemList = productMapper.getOrderItems(orderId);
        //2.获取product和order对应产品的库存
        List<QuantityDTO> productItemList = productMapper.getProductStockByOrderId(orderId);
        //3.双重for循环更新库存
        List<QuantityDTO> newStock= new ArrayList<QuantityDTO>();
        for (QuantityDTO quantityDTO : orderItemList) {
            for (QuantityDTO dto : productItemList) {
                //判断商品id是否相等
                if(quantityDTO.getProductId()==dto.getProductId()){
                    int temp = dto.getQuantity() - quantityDTO.getQuantity();
                    if(temp<0){
                        throw new RuntimeException("超出库存");
                    }
                    newStock.add(new QuantityDTO(quantityDTO.getProductId(),temp));
                }
            }
        }
        //4.刷新到数据库
        for (QuantityDTO quantityDTO : newStock) {
            int result = productMapper.updateProductStock(quantityDTO);
            if(result!=1){
                throw new RuntimeException("更新库存失败");
            }
        }

        return 1;
    }


}
