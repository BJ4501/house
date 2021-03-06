package com.bj.house.common.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 * Created by BJ on 2018/1/19.
 */
@SuppressWarnings("unused")
@Entity
//fixme 如果直接将表命名为user 则会出现以下异常，原因不明
//SchemaExtractionException: More than one table found in namespace (, ) : user
//解决方法：先使用hms_user作为表名导入，再在数据库中将表名修改回user，同时将Table name 改为user
@Table(name = "user",
        indexes = {@Index(name = "idx_email",columnList = "email",unique = true)})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) COMMENT '主键id'", unique = true)
    private Long id;
    @Column(columnDefinition = "VARCHAR(20) COMMENT '姓名'", nullable = false)
    private String name = "";
    @Column(columnDefinition = "VARCHAR(13) COMMENT '手机号'", nullable = false)
    private String phone = "";
    @Column(columnDefinition = "VARCHAR(90) COMMENT '电子邮箱'", nullable = false)
    private String email = "";
    @Column(columnDefinition = "VARCHAR(250) COMMENT '自我介绍'", nullable = false)
    private String aboutme = "";
    @Column(columnDefinition = "VARCHAR(512) COMMENT '密码通过MD5加密'", nullable = false)
    private String passwd = "";
    @Column(columnDefinition = "VARCHAR(512) COMMENT '头像图片'", nullable = false)
    private String avatar = "";
    @Column(columnDefinition = "TINYINT(1) COMMENT '1-普通用户 2-经纪人'", nullable = false)
    private Integer type = 0;
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'", nullable = false, updatable = false)
    private Date createTime = new Date();
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否启用 1-启用 2-停用'", nullable = false)
    private Integer enable = 0;
    @Column(columnDefinition = "INT(11) COMMENT '所属经纪机构'", nullable = false)
    private Long agencyId = 0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }
}
