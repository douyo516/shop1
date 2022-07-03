package com.cqut.store.service;


import com.cqut.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class insertProductTset {

    @Autowired
    private IAdminService adminService;

    @Test
    public void insetP(){
        String adminName="韦滔";
        Product product = new Product();
        product.setId(1100001101);
        product.setItemType("牛皮纸记事本");
        adminService.addProduct(adminName, product);
    }
}
