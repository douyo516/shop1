package com.cqut.store.controller;

import com.cqut.store.entity.Address;
import com.cqut.store.service.IAddressService;
import com.cqut.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService addressService;

    /**
     * 添加新收货地址
     * @param address
     * @param session
     * @return
     */
    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // 调用业务对象的方法执行业务
        addressService.addNewAddress(uid, username, address);
        // 响应成功
        return new JsonResult<Void>(OK);
    }

    @GetMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<List<Address>>(OK, data);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid, uid, username);
        return new JsonResult<Void>(OK);
    }

    /**
     * 删除收货地址
     * @param aid
     * @param session
     * @return
     */
    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.delete(aid, uid, username);
        return new JsonResult<Void>(OK);
    }

    /**
     * 提交地址更新信息
     * @param aid
     * @param address
     * @param session
     * @return
     */
    @RequestMapping("/update")
    public JsonResult<Void> updateAdd(Address address, HttpSession session){
        System.out.println("修改成功");
        System.out.println(address.getAid());
        System.out.println(address);
        Integer uid= (Integer) session.getAttribute("uid");
        String username= (String) session.getAttribute("username");
        addressService.updateAdd(uid,address,username);
        return new JsonResult<Void>(OK);
    }

    /**
     * 地址更新请求
     * @param aid
     * @return
     */
    @RequestMapping("{aid}/updatesubmit")
    public JsonResult<Address> getAddSubmit(@PathVariable("aid") Integer aid){
        Address addSubmit = addressService.getAddSubmit(aid);
        return new JsonResult<Address>(OK,addSubmit);
    }

}

