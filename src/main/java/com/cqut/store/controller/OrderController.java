package com.cqut.store.controller;

import com.cqut.store.entity.Order;
import com.cqut.store.service.IOrderService;
import com.cqut.store.util.JsonResult;
import com.cqut.store.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("list")
    public JsonResult<List<List<OrderVO>>> Orderlist(HttpSession session){
        Integer uid= (Integer) session.getAttribute("uid");
        List<List<OrderVO>> orderlists = orderService.getOrderlist(uid);
        return new JsonResult<>(OK,orderlists);
    }

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        // 从Session中取出uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行业务
        Order data = orderService.create(aid, cids, uid, username);
        // 返回成功与数据
        return new JsonResult<Order>(OK, data);
    }
}
