package com.bj.house.biz.service;

import com.bj.house.biz.persistance.repository.CommunityRepository;
import com.bj.house.biz.persistance.repository.HouseRepository;
import com.bj.house.common.entity.Community;
import com.bj.house.common.entity.House;
import com.bj.house.common.entity.User;
import com.bj.house.common.page.PageData;
import com.bj.house.common.page.PageParams;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by BJ on 2018/1/22.
 */
@Service
public class HouseService {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Value("${file.prefix}")
    private String imgPrefix;

    /**
     * 1.查询小区
     * 2.添加图片服务器地址前缀
     * 3.构建分页结果
     * @param query
     * @param pageParams
     */
    public PageData<House> queryHouse(House query, PageParams pageParams) {
        List<House> houses = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(query.getName())){
            Community community = new Community();
            community.setName(query.getName());
            List<Community> communities = communityRepository.selectCommunity(community.getId(),community.getName());
            if (!communities.isEmpty()){
                query.setCommunityId(communities.get(0).getId());
            }
        }
        houses = queryAndSetImg(query,pageParams);

        Long count = houseRepository.queryHouseCount();

        return PageData.buildPage(houses,count,pageParams.getPageSize(),pageParams.getPageNum());


    }

    private List<House> queryAndSetImg(House query, PageParams pageParams) {
        //分页查询
        Pageable pageable = new PageRequest(pageParams.getPageNum(),pageParams.getPageSize());
        Page<House> pageResult = houseRepository.findAll(pageable);
        List<House> houses = pageResult.getContent();

        houses.forEach(h -> {
            h.setFirstImg(imgPrefix + h.getFirstImg());
            h.setImageList(h.getImageList().stream().map(img -> imgPrefix +img).collect(Collectors.toList()));
            h.setFloorPlanList(h.getFloorPlanList().stream().map(pic -> imgPrefix +pic).collect(Collectors.toList()));
        });

        return houses;
    }

}
