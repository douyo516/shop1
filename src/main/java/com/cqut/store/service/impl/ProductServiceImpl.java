package com.cqut.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqut.store.entity.Product;
import com.cqut.store.mapper.ProductMapper;
import com.cqut.store.service.IProductService;
import com.cqut.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** 处理商品数据的业务层实现类 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        // 根据参数id调用私有方法执行查询，获取商品数据
        Product product = productMapper.findById(id);
        // 判断查询结果是否为null
        if (product == null) {
            // 是：抛出ProductNotFoundException
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        // 将查询结果中的部分属性设置为null
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        // 返回查询结果
        return product;
    }

    @Override
    public List<Product> findByCateId(Integer cateid) {
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.eq("category_id",cateid).eq("status",1);
        List<Product> productList = productMapper.selectList(qw);
        return productList;
    }

    @Override
    public List<Product> selectAllProduct() {
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.ne("status",3);
        List<Product> products = productMapper.selectList(qw);
        return products;
    }
}
