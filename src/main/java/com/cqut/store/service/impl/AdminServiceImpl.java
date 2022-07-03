package com.cqut.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqut.store.entity.Admin;
import com.cqut.store.entity.Product;
import com.cqut.store.entity.User;
import com.cqut.store.mapper.AdminMapper;
import com.cqut.store.mapper.ProductMapper;
import com.cqut.store.mapper.UserMapper;
import com.cqut.store.service.IAdminService;
import com.cqut.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     *添加商品
     * @param adminName
     * @param product
     */
    @Override
    public void addProduct(String adminName, Product product) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("id",product.getId());
        Product res = productMapper.selectOne(productQueryWrapper);
        if (res!=null){
            throw new FailToAddProductException("商品已存在");
        }
        Date now = new Date();
        product.setCreatedUser(adminName);
        product.setCreatedTime(now);
        product.setModifiedUser(adminName);
        product.setModifiedTime(now);
        int insert = productMapper.insert(product);
    }

    @Override
    public void deleteProduct(Integer id, String adminName) {
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.eq("id",id);
        Product product = productMapper.selectOne(qw);
        if (product==null){
            throw new ProductNotFoundException("商品不存在");
        }
        Date now = new Date();
        UpdateWrapper<Product> up = new UpdateWrapper<>();
        up.eq("id",id)
                .set("status",3)
                .set("created_time",now)
                .set("created_user",adminName)
                .set("modified_time",now)
                .set("modified_user",adminName);
        int delete = productMapper.update(product,up);
    }

    @Override
    public void updateProduct(Product product, String adminName) {
        product.setModifiedUser(adminName);
        int i = productMapper.updateById(product);
        System.out.println(i);
    }

    @Override
    public List<User> selectAllUser() {
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @Override
    public void updateUser(User user, String adminName) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User result = userMapper.findByUid(user.getUid());
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        // 判断查询结果中的isDelete是否为1
        if (result.getIsDelete().equals(1)) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        // 向参数user中补全数据：uid
        user.setUid(user.getUid());
        // 向参数user中补全数据：modifiedUser(username)
        user.setModifiedUser(adminName);
        // 向参数user中补全数据：modifiedTime(new Date())
        user.setModifiedTime(new Date());
        // 调用userMapper的updateInfoByUid(User user)方法执行修改，并获取返回值
        Integer rows = userMapper.updateInfoByUid(user);
        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException异常
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public Admin login(String username, String password) {
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("admin_name",username).eq("admin_password",password);
        Admin admin = adminMapper.selectOne(qw);
        if (admin==null) {
            throw new AdminNotFoundException("管理员不存在");
        }
        if (admin.getIsDeleted() == 1) {
            // 是：抛出UserNotFoundException异常
            throw new AdminNotFoundException("管理员无权限");
        }
        return admin;
    }

    @Override
    public List<Admin> selectAdminByRole(Integer role) {
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("role",role);
        List<Admin> adminList = adminMapper.selectList(qw);
        return adminList;
    }

    @Override
    public void addAmin(Admin admin, String adminName) {
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("admin_name",admin.getAdminName());
        Admin admin1 = adminMapper.selectOne(qw);
        if (admin1!=null){
            throw new InsertException("该管理员已存在");
        }
        Date now = new Date();
        admin.setCreatedTime(now);
        admin.setCreatedUser(adminName);
        admin.setModifiedTime(now);
        admin.setModifiedUser(adminName);
        adminMapper.insert(admin);
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("admin_id",adminId);
        Admin admin1 = adminMapper.selectOne(qw);
        if(admin1==null){
            throw  new DeleteException("管理员不存在,删除失败!");
        }
        adminMapper.deleteById(adminId);
    }

    @Override
    public Admin getOneAdminById(Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        if (admin==null){
            throw new InsertException("没查找到相关管理员信息");
        }
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin, String adminName) {
        Date date = new Date();
        admin.setModifiedTime(date);
        admin.setModifiedUser(adminName);
        adminMapper.updateById(admin);
    }
}
