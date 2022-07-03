package com.cqut.store.controller;


import com.cqut.store.entity.Admin;
import com.cqut.store.entity.Product;
import com.cqut.store.entity.User;
import com.cqut.store.service.IAdminService;
import com.cqut.store.service.IProductService;
import com.cqut.store.service.IUserService;
import com.cqut.store.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController extends BaseController{

//    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IAdminService adminService;


    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;



    @RequestMapping("login")
    public JsonResult<Admin> login(@RequestParam("adminName") String username,
                                   @RequestParam("adminPassword") String password, HttpSession session) {
        // 调用业务对象的方法执行登录，并获取返回值
        Admin data = adminService.login(username, password);
        //登录成功后，将uid和username存入到HttpSession中
        session.setAttribute("adminId", data.getAdminId());
        session.setAttribute("adminName", data.getAdminName());
        // System.out.println("Session中的uid=" + getUidFromSession(session));
        // System.out.println("Session中的username=" + getUsernameFromSession(session));

        // 将以上返回值和状态码OK封装到响应结果中并返回
        return new JsonResult<Admin>(OK, data);
    }

    @RequestMapping("layout")
    public JsonResult<Void> layout(HttpSession session){
        System.out.println("开始");
        System.out.println(session.getAttribute("adminId"));
        session.removeAttribute("uid");
        System.out.println(session.getAttribute("adminId"));
        System.out.println(session.getAttribute("adminName"));
        session.removeAttribute("username");
        System.out.println(session.getAttribute("adminName"));
        session.invalidate();
        return new JsonResult<>(OK);
    }

    /**提出添加修改请求
     * 添加商品
     * @param product
     * @return
     */
    @RequestMapping("add_product")
    public JsonResult<Void> insertProduct(Product product, HttpSession session){
        String adminName = (String) session.getAttribute("adminName");
        adminService.addProduct(adminName,product);
        return new JsonResult<Void>(OK);
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping("{id}/delete_product")
    public JsonResult<Void> deleteProduct(@PathVariable("id") Integer id,HttpSession session){
        String adminName = (String) session.getAttribute("adminName");
        adminService.deleteProduct(id,adminName);
        return new JsonResult<Void>(OK);
    }

    /**
     * 提出修改商品请求
     * @param pid
     * @return
     */
    @RequestMapping("product/{pid}/update")
    public JsonResult<Product> getOneProduct(@PathVariable("pid") Integer pid){
        Product product = productService.findById(pid);
        return new JsonResult<>(OK,product);
    }

    /**
     * 更新商品信息
     * @param product
     * @return
     */
    @RequestMapping("update_product")
    public JsonResult<Void> updataProduct(Product product,HttpSession session){
        String adminName = (String) session.getAttribute("adminName");
        adminService.updateProduct(product,adminName);
        return new JsonResult<Void>(OK);
    }

    /**
     * 营销经理对用户信息进行查询
     * @return
     */
    @RequestMapping("get_all_user")
    public JsonResult<List<User>> getALlUser(){
        List<User> users = adminService.selectAllUser();
        return new JsonResult<List<User>>(OK,users);
    }

    /**
     * 提出user更新请求
     * @param uid
     * @return
     */

    @RequestMapping("user/{uid}/update")
    public JsonResult<User> getOneUser(@PathVariable("uid") Integer uid){
        User user = userService.getByUid(uid);
        return new JsonResult<>(OK,user);
    }

    /**
     * 更新user信息点击提交按钮
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("update_user")
    public JsonResult<Void> updateUser(User user,HttpSession session){
        String adminName= (String) session.getAttribute("adminName");
//        System.out.println(user);
//        User u = userService.getByUid(user.getUid());
//        System.out.println(u);
//        u.setEmail(user.getEmail());
        adminService.updateUser(user,adminName);
        return new JsonResult<Void>(OK);
    }



    @RequestMapping("{role}/select")
    public JsonResult<List<Admin>> getAdminByRole(@PathVariable("role") Integer role){
        List<Admin> adminList = adminService.selectAdminByRole(role);
        return new JsonResult<>(OK,adminList);
    }

    /**
     * 添加管理员
     * @param admin
     * @param session
     * @return
     */

    @RequestMapping("addAdmin")
    public JsonResult<Void> addAdmin(Admin admin,HttpSession session){
        String adminName = (String) session.getAttribute("adminName");
        adminService.addAmin(admin,adminName);
        return new JsonResult<>(OK);
    }

    /**
     * 删除管理员
     * @param adminId
     * @return
     */
    @RequestMapping("{adminId}/deleteAdmin")
    public JsonResult<Void> deleteAdmin(@PathVariable("adminId") Integer adminId){
        adminService.deleteAdmin(adminId);
        return new JsonResult<>(OK);
    }

    /**
     * 修改管理员请求发送
     * @param adminId
     * @return
     */
    @RequestMapping("{adminId}/update")
    public JsonResult<Admin> getAdmin(@PathVariable("adminId") Integer adminId){
        Admin admin = adminService.getOneAdminById(adminId);
        return new JsonResult<>(OK,admin);
    }

    /**
     * 修改管理员信息完成
     * @param admin
     * @param session
     * @return
     */
    @PostMapping("/updateAdmin")
    public JsonResult<Void> updateAdmin(Admin admin,HttpSession session){
        String adminName = (String) session.getAttribute("AdminName");
        adminService.updateAdmin(admin,adminName);
        return new JsonResult<>(OK);

    }

    @RequestMapping("{adminId}/getAdmin")
    public JsonResult<List<Admin>> getYingXiaoAdmin(@PathVariable("adminId") Integer adminId){
        List<Admin> adminList = adminService.selectAdminByRole(adminId);
        return new JsonResult<>(OK,adminList);
    }




}
