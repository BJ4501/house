package com.bj.house.persistance.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 * Created by BJ on 2018/1/19.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "user",
        indexes = {@Index(name = "idx_email",columnList = "email",unique = true)})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) COMMENT '主键id'", unique = true)
    private Integer id;
    @Column(columnDefinition = "VARCHAR(20) COMMENT '姓名'", nullable = false)
    private String name = "";
    @Column(columnDefinition = "CHAR(13) COMMENT '手机号'", nullable = false)
    private Integer phone = 0;
    @Column(columnDefinition = "VARCHAR(90) COMMENT '电子邮箱'", nullable = false)
    private String email = "";
    @Column(columnDefinition = "VARCHAR(250) COMMENT '自我介绍'", nullable = false)
    private String aboutme = "";
    @Column(columnDefinition = "VARCHAR(512) COMMENT '密码通过MD5加密'", nullable = false)
    private String passwd = "";
    @Column(columnDefinition = "VARCHAR(512) COMMENT '头像图片'", nullable = false)
    private String avatar = "";
    @Column(columnDefinition = "TINYINT(1) COMMENT '1-普通用户 2-经纪人'", nullable = false)
    private int type = 0;
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'", nullable = false, updatable = false)
    private Date createTime = new Date();
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否启用 1-启用 2-停用'", nullable = false)
    private int enable = 0;
    @Column(columnDefinition = "INT(11) COMMENT '所属经纪机构'", nullable = false)
    private Integer agencyId = 0;

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

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
}
