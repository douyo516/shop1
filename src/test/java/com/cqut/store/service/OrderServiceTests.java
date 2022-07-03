package com.cqut.store.service;

import com.cqut.store.entity.Order;
import com.cqut.store.service.ex.ServiceException;
import com.cqut.store.vo.OrderVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;

    @Test
    public void create() {
        try {
            Integer aid = 1;
            Integer[] cids = {1};
            Integer uid = 1;
            String username = "陈相颖";
            Order order = orderService.create(aid, cids, uid, username);
            System.out.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }
        @Test
        public void create1() {

                Integer aid = 3;
                Integer[] cids = {1};
                Integer uid = 1;
                String username = "陈相颖";
                Order order = orderService.create(aid, cids, uid, username);
                System.out.println(order);
    }

    @Test
    public void setOrderlist(){
        List<List<OrderVO>> orderlists = orderService.getOrderlist(1);
        orderlists.forEach(System.out::println);

    }
}
