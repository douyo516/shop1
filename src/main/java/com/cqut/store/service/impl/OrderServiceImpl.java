package com.cqut.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqut.store.entity.Address;
import com.cqut.store.entity.Cart;
import com.cqut.store.entity.Order;
import com.cqut.store.entity.OrderItem;
import com.cqut.store.mapper.CartMapper;
import com.cqut.store.mapper.OrderItemMapper;
import com.cqut.store.mapper.OrderMapper;
import com.cqut.store.service.IAddressService;
import com.cqut.store.service.ICartService;
import com.cqut.store.service.IOrderService;
import com.cqut.store.service.ex.InsertException;
import com.cqut.store.vo.CartVO;
import com.cqut.store.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 处理订单和订单数据的业务层实现类 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Transactional
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        // 创建当前时间对象
        Date now = new Date();

        // 根据cids查询所勾选的购物车列表中的数据
        List<CartVO> carts = cartService.getVOByCids(uid, cids);

        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("uid",uid).in("cid", cids);
        List<Cart> carts1 = cartMapper.selectList(qw);


        // 计算这些商品的总价
        long totalPrice = 0;
        for (Cart cart:carts1){
            totalPrice+=cart.getPrice()*cart.getNum();
        }
//        for (CartVO cart : carts) {
//            totalPrice += cart.getRealPrice() * cart.getNum();
//        }


        // 创建订单数据对象
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        // 查询收货地址数据
        Address address = addressService.getByAid(aid, uid);
        // 补全数据：收货地址相关的6项
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        // 补全数据：totalPrice
        order.setTotalPrice(totalPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(now);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        // 插入订单数据
        Integer rows1 = orderMapper.insertOrder(order);
        if (rows1 != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }

        // 遍历carts，循环插入订单商品数据
        for (CartVO cart : carts) {
            // 创建订单商品数据
            OrderItem item = new OrderItem();
            // 补全数据：setOid(order.getOid())
            item.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());
            // 补全数据：4项日志
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            // 插入订单商品数据
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }

        // 返回
        return order;
    }

    @Override
    public List<List<OrderVO>> getOrderlist(Integer uid) {
        List<List<OrderVO>> lists=new ArrayList<>();
        QueryWrapper<Order> qw = new QueryWrapper<>();
        qw.eq("uid",uid);
        List<Order> orders = orderMapper.selectList(qw);
        for (int j=0;j<orders.size();j++) {
            Integer oid = orders.get(j).getOid();
            QueryWrapper<OrderItem> qwer = new QueryWrapper<>();
            qwer.eq("oid", oid);
            Integer count = orderItemMapper.selectCount(qwer);
            List<OrderItem> orderItems = orderItemMapper.selectList(qwer);
            List<OrderVO> orderVOList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                OrderVO orderVO = new OrderVO();
                orderVO.setOid(orders.get(j).getOid());
                orderVO.setNum(orderItems.get(i).getNum());
                orderVO.setPrice(orderItems.get(i).getPrice());
                orderVO.setTitle(orderItems.get(i).getTitle());
                orderVO.setImage(orderItems.get(i).getImage());
                orderVO.setRecv_name(orders.get(j).getRecvName());
                orderVO.setCreateTime(orders.get(j).getCreatedTime());
//                orderVOList.get(listcout).setOid(orders.get(j).getOid());
//                orderVOList.get(listcout).setNum(orderItems.get(i).getNum());
//                orderVOList.get(listcout).setPrice(orderItems.get(i).getPrice());
//                orderVOList.get(listcout).setTitle(orderItems.get(i).getTitle());
//                orderVOList.get(listcout).setImage(orderItems.get(i).getImage());
//                orderVOList.get(listcout).setRecv_name(orders.get(j).getRecvName());
//                orderVOList.get(listcout).setCreateTime(orders.get(j).getCreatedTime());
                orderVOList.add(orderVO);
            }
            lists.add(orderVOList);
        }

        return lists;
    }
}
