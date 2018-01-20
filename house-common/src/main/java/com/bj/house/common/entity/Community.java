package com.bj.house.common.entity;

import javax.persistence.*;

/**
 * 小区表
 * Created by BJ on 2018/1/19.
 */
@Entity(name = "community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) COMMENT '主键id'", unique = true)
    private Integer id;
    @Column(columnDefinition = "VARCHAR(11) COMMENT '城市编码'", nullable = false)
    private String cityCode = "";
    @Column(columnDefinition = "VARCHAR(11) COMMENT '城市名称'", nullable = false)
    private String cityName = "";
    @Column(columnDefinition = "VARCHAR(11) COMMENT '小区名称'", nullable = false)
    private String name = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
