package com.cqut.store.vo;


import lombok.Data;

import java.util.Date;

@Data
public class OrderVO {
    private Integer oid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;
    private String recv_name;
    private Integer state;
    private Date createTime;
}
