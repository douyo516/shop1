package com.cqut.store.service;

import com.cqut.store.entity.Address;
import com.cqut.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress() {
        try {
            Integer uid = 20;
            String username = "管理员";
            Address address = new Address();
            address.setName("张三");
            address.setPhone("17858805555");
            address.setAddress("雁塔区小寨华旗");
            addressService.addNewAddress(uid, username, address);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getByUid() {
        Integer uid = 26;
        List<Address> list = addressService.getByUid(uid);
        System.out.println("count=" + list.size());
        for (Address item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void setDefault() {
        try {
            Integer aid = 18;
            Integer uid = 30;
            String username = "系统管理员";
            addressService.setDefault(aid, uid, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void delete() {
        try {
            Integer aid = 18;
            Integer uid = 30;
            String username = "明明";
            addressService.delete(aid, uid, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    /*
    @Test
    public void getByAid() {
        try {
            Integer aid = 17;
            Integer uid = 30;
            Address address = addressService.getByAid(aid, uid);
            System.out.println(address);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    */
    @Test
    public void addnewAdd(){
        Integer uid=2;
        String username="陈相颖";
        Address address = new Address();
        address.setName("陈相颖");
        address.setProvinceName("云南省");
        address.setCityName("昆明市");
        address.setAreaName("哈哈区");
        address.setPhone("19946881201");
        address.setAddress("嫡女家园三单元");
        address.setZip("402360");
        addressService.addNewAddress(uid,username,address);
    }

    @Test
    public void deleteAdd(){
        Integer aid=4;
        Integer uid=2;
        String username="陈相颖";
        addressService.delete(aid,uid,username);
    }
    @Test
    public void updateAdd(){
        Integer aid=1;
        Integer uid=1;
        String username="陈相颖";
        Address address = new Address();
        address.setProvinceName("新疆省");
        address.setCityName("呼和浩特市");
        address.setAddress("姐姐区");
        address.setAddress("结节街道");
        address.setZip("234143");
        address.setPhone("15923006168");
        addressService.updateAdd(aid, address,username);
    }
    @Test
    public void getAddSubmit(){
        Integer aid=1;
        Address addSubmit = addressService.getAddSubmit(1);
        System.out.println(addSubmit);
    }
}
