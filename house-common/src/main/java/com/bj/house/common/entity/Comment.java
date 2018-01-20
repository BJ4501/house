package com.bj.house.common.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 评论表
 * Created by BJ on 2018/1/19.
 */
@Entity(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(11) COMMENT '主键id'", unique = true)
    private Integer id;
    @Column(columnDefinition = "VARCHAR(512) COMMENT '评论内容'", nullable = false)
    private String content = "";
    @Column(columnDefinition = "BIGINT(20) COMMENT '房屋id'", nullable = false)
    private Integer houseId = 0;
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'", nullable = false, updatable = false)
    private Date createTime = new Date();
    @Column(columnDefinition = "INT(11) COMMENT '博客id'", nullable = false)
    private Integer blogId = 0;
    @Column(columnDefinition = "TINYINT(1) COMMENT '1-房产 2-博客'", nullable = false)
    private Integer type = 0;
    @Column(columnDefinition = "BIGINT(11) COMMENT '用户id'", nullable = false)
    private Integer userId = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
