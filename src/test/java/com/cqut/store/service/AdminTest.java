package com.cqut.store.service;

import com.cqut.store.entity.Admin;
import com.cqut.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {

    @Autowired
    private IAdminService adminService;

    @Test
    public void addProduct(){
        String Ad="韦滔";
        Product product = new Product();
        product.setId(1231231);
        product.setCategoryId(1);
        product.setItemType("苹果手机");
        product.setTitle("全国进口货");
        product.setPrice(4999L);
        product.setNum(10000);
        product.setStatus(1);
        adminService.addProduct(Ad,product);
    }

    @Test
    public void deleteProduct() {
        Integer id = 10000001;
        String adminName = "韦滔";
        adminService.deleteProduct(id, adminName);
    }

    @Test
    public void selectById(){
        adminService.selectAdminByRole(1).forEach(System.out::println);
    }

//    @Test
//    public void getOneUser(){
//
//    }
    @Test
    public void addAmin(){
        Admin admin = new Admin();
        admin.setAdminName("郭富城");
        admin.setAdminPassword("160354gfc");
        admin.setIsDeleted(0);
        admin.setRole(1);
        String adminName="chen";
        adminService.addAmin(admin,adminName);
    }

    @Test
    public void deleteAdmin(){
        Integer id=5;
        adminService.deleteAdmin(id);
    }

    @Test
    public void updateAdmin1(){
        Integer id=6;
        System.out.println(adminService.getOneAdminById(id));
    }


    @Test
    public void updateAdmin(){
        Admin admin = new Admin();
        admin.setAdminId(6);
        admin.setAdminName("九妹");
        admin.setAdminPassword("160354jm");
        admin.setRole(2);
        admin.setIsDeleted(1);
        adminService.updateAdmin(admin,"陈相颖");
    }
}
