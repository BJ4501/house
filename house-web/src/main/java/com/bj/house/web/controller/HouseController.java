package com.bj.house.web.controller;

import com.bj.house.biz.service.HouseService;
import com.bj.house.common.entity.House;
import com.bj.house.common.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by BJ on 2018/1/22.
 */
@Controller
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * 房产列表
     * 1.实现分页
     * 2.支持小区搜索、类型搜索
     * 3.支持排序
     * 4.支持展示图片、价格、标题、地址等信息
     * @return
     */
    @RequestMapping("/house/list")
    public String houseList(Integer pageSize,Integer pageNum,House query,ModelMap modelMap){
        houseService.queryHouse(query, PageParams.build(pageSize, pageNum));





        return "homepage/index";
    }


}
