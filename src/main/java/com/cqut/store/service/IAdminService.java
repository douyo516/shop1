package com.cqut.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqut.store.entity.Admin;
import com.cqut.store.entity.Product;
import com.cqut.store.entity.User;

import java.util.List;


public interface IAdminService extends IService<Admin> {
    void addProduct(String adminName, Product product);

    void deleteProduct(Integer id, String adminName);

    void updateProduct(Product product, String adminName);

    List<User> selectAllUser();

    void updateUser(User user, String adminName);

    Admin login(String username, String password);

    List<Admin> selectAdminByRole(Integer role);

    void addAmin(Admin admin, String adminName);

    void deleteAdmin(Integer adminId);

    Admin getOneAdminById(Integer adminId);

    void updateAdmin(Admin admin, String adminName);
}
