package com.cqut.store.controller;

import com.cqut.store.entity.Cart;
import com.cqut.store.service.ICartService;
import com.cqut.store.util.JsonResult;
import com.cqut.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        System.out.println("pid=" + pid);
        System.out.println("amount=" + amount);
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行添加到购物车
        cartService.addToCart(uid, pid, amount, username);
        // 返回成功
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("mycart")
    public JsonResult<List<Cart>> getMyCart(HttpSession session){
        Integer uid= (Integer) session.getAttribute("uid");
        List<Cart> carts = cartService.selectMyCartById(uid);
        return new JsonResult<List<Cart>>(OK,carts);
    }

    @GetMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByUid(uid);
        // 返回成功与数据
        return new JsonResult<List<CartVO>>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.addNum(cid, uid, username);
        // 返回成功
        return new JsonResult<Integer>(OK, data);
    }

    @RequestMapping("{cid}/num/reduce")
    public JsonResult<Integer> reduceNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.reduceNum(cid, uid, username);
        // 返回成功
        return new JsonResult<Integer>(OK, data);
    }

    @GetMapping("list")
    public JsonResult<List<CartVO>> getVOByCids(Integer[] cids, HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByCids(uid, cids);
        // 返回成功与数据
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("{cid}/num/update")
    public JsonResult<Integer> updateNum(@PathVariable("cid") Integer cid,Integer amount,HttpSession session){
        Integer uid= (Integer) session.getAttribute("uid");
        String username= (String) session.getAttribute("username");
        Integer data = cartService.updateNum(cid, uid, username, amount);
        return new JsonResult<Integer>(OK,data);
    }

    @RequestMapping("{cid}/delete")
    public JsonResult<Void> deleteCart(@PathVariable("cid") Integer cid,HttpSession session){
        Integer uid= (Integer) session.getAttribute("uid");
        String username= (String) session.getAttribute("username");
        cartService.deleteCart(cid,uid);
        return new JsonResult<Void>(OK);
    }

}
