package com.cqut.store.controller;


import com.cqut.store.entity.Product;
import com.cqut.store.entity.User;
import com.cqut.store.service.IUserService;
import com.cqut.store.service.impl.ProductServiceImpl;
import com.cqut.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cqut.store.controller.BaseController.OK;

@RestController
public class test {

    @Autowired
    private IUserService userService;

    @GetMapping("/api/hello")
    public List<User> test(){
        List<User> list = userService.list();
        return list;
    }
//    @GetMapping("/api/hello")
//    public String test(){
//        return "测试成功！";
//    }
}
