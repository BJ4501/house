package com.bj.house.biz.service;

import com.bj.house.biz.persistance.repository.*;
import com.bj.house.common.entity.*;
import com.bj.house.common.model.UserModel;
import com.bj.house.common.model.UserMsg;
import com.bj.house.common.page.PageData;
import com.bj.house.common.page.PageParams;
import com.bj.house.common.utils.BeanHelper;
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

    @Autowired
    HouseMsgRepository houseMsgRepository;

    @Autowired
    HouseUserRepository houseUserRepository;

    @Autowired
    AgencyService agencyService;

    @Autowired
    MailService mailService;

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

    public House queryOneHouse(Long id) {
        House query = houseRepository.findOne(Integer.valueOf(id.toString()));
        if (query != null)
            return query;
        return null;
    }

    public void addUserMsg(UserMsg userMsg) {
        BeanHelper.onInsert(userMsg);
        //SAVE
        insertUserMsg(userMsg);
        UserModel agent = agencyService.getAgentDetail(userMsg.getAgentId());
        mailService.sendEmail("来自用户"+userMsg.getEmail()+"的留言",userMsg.getMsg(),agent.getEmail());
    }

    //添加至信息表
    private void insertUserMsg(UserMsg userMsg) {
        HouseMsg houseMsg = new HouseMsg();
        houseMsg.setMsg(userMsg.getMsg());
        houseMsg.setCreateTime(userMsg.getCreateTime());
        houseMsg.setUserName(userMsg.getUserName());
        houseMsg.setHouseId(userMsg.getHouseId());
        houseMsg.setAgentId(userMsg.getAgentId());
        houseMsgRepository.save(houseMsg);
    }

    public HouseUser getHouseUser(Long id) {
        HouseUser houseUser = houseUserRepository.findOne(Integer.valueOf(id.toString()));
        if (houseUser != null)
            return houseUser;
        return null;
    }
}
