package com.bj.house.persistance.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 博客表
 * Created by BJ on 2018/1/19.
 */
@Entity(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) COMMENT '主键id'", unique = true)
    private Integer id;
    @Column(columnDefinition = "VARCHAR(20) COMMENT '标签'", nullable = false)
    private String tags = "";
    @Column(columnDefinition = "TEXT COMMENT '内容'", nullable = false)
    private String content = "";
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'", nullable = false, updatable = false)
    private Date createTime = new Date();
    @Column(columnDefinition = "VARCHAR(20) COMMENT '标题'", nullable = false)
    private String title = "";
    @Column(columnDefinition = "INT(11) COMMENT '分类'", nullable = false)
    private Integer cat = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }
}
