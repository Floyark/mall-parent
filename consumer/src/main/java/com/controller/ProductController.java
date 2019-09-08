package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dto.MainTableDTO;
import com.github.pagehelper.PageInfo;
import com.pojo.Product;
import com.response.ServerResponse;
import com.response.TableResponse;
import com.service.ProductService;
import com.service.UserService;
import com.util.ResponseUtil;
import com.vo.ProductDetailVO;
import com.vo.ProductInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class ProductController {


    @Reference
    ProductService productService;

    @Reference
    UserService userService;
    //****查询产品详细信息
    @RequestMapping("/productInfo.html/{id}")
    public ModelAndView getProductInfoById(@PathVariable Integer id, HttpSession session){
        String sessionId = session.getId();
        Integer userId = userService.getUserId(sessionId);
        if(userId == null){
            return new ModelAndView("login/login");
        }
        if(userId<0){
            ModelAndView modelAndView = new ModelAndView("main/iframe/addUserInfo.html");
            if(-userId%2==0){
                //用户id单复数，判断用户是邮件登录(负数)   还是手机号登录（单数）
                System.out.println("判断进入邮件email");
                String email = userService.getUserIdByEmail(-userId);
                System.out.println("游客id为"+userId+":"+email);
                modelAndView.addObject("email",email);
            }else{
                System.out.println("判断进入手机号iphone");
            }
            return modelAndView;
        }
        ModelAndView modelAndView=new ModelAndView("main/iframe/productDetail.html");
        //数据库中查找数据
        Product product = productService.getProductById(id);
        modelAndView.addObject("product",product);
        return modelAndView;
    }


    //****根据查询条件 到数据库查询数据
    @RequestMapping("/productInfo.html/getInfo")
    public @ResponseBody TableResponse getAllProductInfo(MainTableDTO mainTableDTO, @PathParam("page") Integer page, @PathParam("limit") Integer limit){

       mainTableDTO.setLimit(limit);
       mainTableDTO.setPage(page);
        PageInfo<Product> productPageInfo = productService.products(mainTableDTO);
        return new TableResponse(productPageInfo.getTotal(),productPageInfo.getList());
    }

    //****跳转到修改单个商品的界面
    @RequestMapping("/update.html/{id}")
    public String toUpdate(@PathVariable(value = "id") Integer id, Model model){
        //Service层中根据id获取商品数据
        Product productById = productService.getProductById(id);
        model.addAttribute("product",productById);
        return "main/iframe/updateProduct";
    }


    //****根据id删除商品信息
    @GetMapping("/productDel/{id}")
    public @ResponseBody ServerResponse toDelete(@PathVariable Integer id){
       int result = productService.deleteProductById(id);
       if(result==1){
           return ResponseUtil.success();
       }
       return ResponseUtil.error("删除失败",30);
    }

    //****修改商品信息
    @RequestMapping("/updateProductInfo.html")
    public @ResponseBody ServerResponse doUpdateInfo(Product product){
        int result = productService.updateProductInfo(product);
        return ResponseUtil.success();
    }

    //****新增商品信息
    @RequestMapping("/addProduct")
    public @ResponseBody ServerResponse addNewProduct(Product product){
        productService.InsertProductInfo(product);
        System.out.println(product);
        return ResponseUtil.success();
    }

    //****商品信息总览
    @RequestMapping("/productInfo.html/info")
    public @ResponseBody ServerResponse getProductInfo(@PathParam(value = "productName")String productName,@PathParam(value = "page")Integer page){

        PageInfo<ProductInfoVO> pageInfo = productService.getProductForMain(productName,page);
        return ResponseUtil.successWithData(pageInfo);

    }

}
