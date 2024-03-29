package com.cqut.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.store.entity.Product;

import java.util.List;

/** 处理商品数据的持久层接口 */
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);
}
