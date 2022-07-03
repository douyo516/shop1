package com.cqut.store.controller;

import com.cqut.store.entity.Product;
import com.cqut.store.service.IProductService;
import com.cqut.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @GetMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        // 调用业务对象执行获取数据
        Product data = productService.findById(id);
        // 返回成功和数据
        return new JsonResult<Product>(OK, data);
    }

    @GetMapping("{cateid}")
    public JsonResult<List<Product>> getProductCateList(@PathVariable("cateid") Integer cateid){
        List<Product> byCateId = productService.findByCateId(cateid);
        return new JsonResult<List<Product>>(OK, byCateId);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Product>> getAllProduct(){
        List<Product> products = productService.selectAllProduct();
        return new JsonResult<List<Product>>(OK,products);
    }
}
