package com.cqut.store.service;

import com.cqut.store.entity.Cart;
import com.cqut.store.service.ex.ServiceException;
import com.cqut.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
    @Autowired
    private ICartService cartService;

    @Test
    public void addToCart() {
        try {
            Integer uid = 1;
            Integer pid = 10000036;
            Integer amount = 3;
            String username = "陈相颖";
            cartService.addToCart(uid, pid, amount, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getCart(){
        Integer uid=1;
        List<Cart> carts = cartService.selectMyCartById(uid);
        carts.forEach(System.out::println);
    }
    @Test
    public void getVOByUid() {
        List<CartVO> list = cartService.getVOByUid(31);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void addNum() {
        try {
            Integer cid = 6;
            Integer uid = 31;
            String username = "管理员";
            Integer num = cartService.addNum(cid, uid, username);
            System.out.println("OK. New num=" + num);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getVOByCids() {
        Integer[] cids = {1, 2};
        Integer uid = 1;
        List<CartVO> list = cartService.getVOByCids(uid, cids);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void getCartByUid(){
        Integer uid=1;
        cartService.getVOByUid(1).forEach(System.out::println);
    }

    @Test
    public void addCartNum(){
        Integer cid=1;
        Integer uid=1;
        cartService.addNum(1,1,"陈相颖");
        cartService.selectMyCartById(1).forEach(System.out::println);
    }

    @Test
    public void reduceCartNum(){
        Integer cid=1;
        Integer uid=1;
        cartService.reduceNum(1,1,"陈相颖");
        cartService.selectMyCartById(1).forEach(System.out::println);
    }
    @Test
    public void updateNum(){
        Integer cid=1;
        Integer uid=1;
        Integer amount=20;
        String username="陈相颖";
        cartService.updateNum(1,1,username,amount);
        cartService.selectMyCartById(1).forEach(System.out::println);
    }

    @Test
    public void deleteCart(){
        Integer cid=2;
        Integer uid=1;
        cartService.deleteCart(2,1);
        cartService.selectMyCartById(1).forEach(System.out::println);
    }
}
