package com.cqut.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_admin")
public class Admin extends BaseEntity {
    @TableId(value = "admin_id",type = IdType.AUTO)
    private Integer adminId;
    private String adminName;
    private String adminPassword;
    private Integer role;
    private Integer isDeleted;
}
