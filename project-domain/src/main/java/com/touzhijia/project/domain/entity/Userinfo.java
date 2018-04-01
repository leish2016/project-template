package com.touzhijia.project.domain.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Data;

@Data
public class Userinfo implements Serializable{
    @Id
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;
}