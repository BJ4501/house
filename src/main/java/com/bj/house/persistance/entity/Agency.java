package com.bj.house.persistance.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 经济机构表
 * Created by BJ on 2018/1/19.
 */
@Entity(name = "agency")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) COMMENT '主键id'", unique = true)
    private Integer id;
    @Column(columnDefinition = "VARCHAR(20) COMMENT '经济机构名称'", nullable = false)
    private String name = "";
    @Column(columnDefinition = "VARCHAR(100) COMMENT '地址'", nullable = false)
    private String address = "";
    @Column(columnDefinition = "CHAR(13) COMMENT '手机号'", nullable = false)
    private Integer phone = 0;
    @Column(columnDefinition = "VARCHAR(50) COMMENT '电子邮箱'", nullable = false)
    private String email = "";
    @Column(columnDefinition = "VARCHAR(100) COMMENT '描述'", nullable = false)
    private String aboutUs = "";
    @Column(columnDefinition = "VARCHAR(11) COMMENT '电话'", nullable = false)
    private String mobile = "";
    @Column(columnDefinition = "VARCHAR(20) COMMENT '网站'", nullable = false)
    private String webSite = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
