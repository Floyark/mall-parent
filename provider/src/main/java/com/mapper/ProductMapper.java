package com.mapper;

import com.dto.MainTableDTO;
import com.dto.QuantityDTO;
import com.pojo.Product;
import com.vo.CartVO;
import com.vo.ProductInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    //根据搜索条件获取商品
    List<Product> getProductInfo(MainTableDTO mainTableDTO);

    Product getProductInfoById(Integer id);

    int deleteProductById(Integer id);

    int updateProductInfo(Product product);

    int insertInfo(Product product);

    List<ProductInfoVO> getProductForMain(@Param("productName")String productName);

    CartVO getCartVoById(Integer productId);

    BigDecimal getProductPriceById(Integer id);

    List<QuantityDTO> getOrderItems(String orderId);

    List<QuantityDTO> getProductStockByOrderId(String orderId);

    //todo 测试不通过
    int updateProductStock(List<QuantityDTO> dto);
}
