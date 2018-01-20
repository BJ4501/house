package com.bj.house.common.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 房屋信息表
 * Created by BJ on 2018/1/19.
 */
@Entity(name = "house_msg")
public class HouseMsg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) COMMENT '主键id'", unique = true)
    private Integer id;
    @Column(columnDefinition = "VARCHAR(1024) COMMENT '消息'", nullable = false)
    private String msg = "";
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'", nullable = false, updatable = false)
    private Date createTime = new Date();
    @Column(columnDefinition = "BIGINT(20) COMMENT '经纪人id'", nullable = false)
    private Integer agentId = 0;
    @Column(columnDefinition = "BIGINT(20) COMMENT '房屋id'", nullable = false)
    private Integer houseId = 0;
    @Column(columnDefinition = "VARCHAR(20) COMMENT '用户名'", nullable = false)
    private String userName = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
