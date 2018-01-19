package com.bj.house.persistance.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 房屋用户信息表
 * Created by BJ on 2018/1/19.
 */
@Entity  //NOTE:索引的建立
@Table(name = "house_user",
        indexes = {@Index(name = "house_id_user_id_type",columnList = "house_id,user_id,type",unique = true)})
public class HouseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) COMMENT '主键id'", unique = true)
    private Integer id;
    @Column(name = "house_id", columnDefinition = "BIGINT(20) COMMENT '房屋id'", nullable = false)
    private Integer houseId = 0;
    @Column(name = "user_id", columnDefinition = "BIGINT(20) COMMENT '用户id'", nullable = false)
    private Integer userId = 0;
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'", nullable = false, updatable = false)
    private Date createTime = new Date();
    @Column(columnDefinition = "TINYINT(1) COMMENT '1-售卖 2-收藏'", nullable = false)
    private int type = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
