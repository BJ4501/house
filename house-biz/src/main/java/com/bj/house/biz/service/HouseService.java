package com.bj.house.biz.service;

import com.bj.house.biz.persistance.repository.HouseRepository;
import com.bj.house.common.entity.House;
import com.bj.house.common.entity.User;
import com.bj.house.common.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BJ on 2018/1/22.
 */
@Service
public class HouseService {

    @Autowired
    HouseRepository houseRepository;

    public void queryHouse(House query, PageParams build) {
        //分页查询
        Pageable pageable = new PageRequest(build.getPageNum(),build.getPageSize());
        Page<House> pageResult = houseRepository.findAll(pageable);
        List<House> houseList = pageResult.getContent();

        


    }
}
