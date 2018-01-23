package com.bj.house.web.controller;

import com.bj.house.biz.service.AgencyService;
import com.bj.house.biz.service.HouseService;
import com.bj.house.common.entity.Agency;
import com.bj.house.common.entity.House;
import com.bj.house.common.entity.HouseUser;
import com.bj.house.common.model.UserMsg;
import com.bj.house.common.page.PageData;
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

    @Autowired
    private AgencyService agencyService;

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
        PageData<House> ps = houseService.queryHouse(query, PageParams.build(pageSize, pageNum));
        modelMap.put("ps",ps);
        modelMap.put("vo",query);
        return "house/listing";
    }

    /**
     * 1.查询房屋详情
     * 2.查询关联经纪人
     * @param id
     * @return
     */
    @RequestMapping("/house/detail")
    public String houseDetail(Long id,ModelMap modelMap){
        House house = houseService.queryOneHouse(id);
        HouseUser houseUser = houseService.getHouseUser(id);

        if (houseUser.getUserId() != null && !house.getUserId().equals(0L)){
            modelMap.put("agent",agencyService.getAgentDetail(houseUser.getUserId()));
        }
        modelMap.put("house",house);
        return "house/detail";
    }

    @RequestMapping("/house/leaveMsg")
    public String houseMsg(UserMsg userMsg){
        houseService.addUserMsg(userMsg);
        return "redirect:/house/detail?id=" + userMsg.getHouseId();
    }




}
