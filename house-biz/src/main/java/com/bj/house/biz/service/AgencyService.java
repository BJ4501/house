package com.bj.house.biz.service;

import com.bj.house.biz.persistance.repository.AgencyRepository;
import com.bj.house.common.entity.User;
import com.bj.house.common.model.UserModel;
import com.bj.house.common.utils.ModelAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 经纪人
 * Created by BJ on 2018/1/23.
 */
@Service
public class AgencyService {

    @Autowired
    AgencyRepository agencyRepository;

    @Value("${file.prefix}")
    private String imgPrefix;



    /**
     * 访问user表获取详情
     * @param userId
     * @return
     */
    public UserModel getAgentDetail(Long userId) {
        List<User> user = agencyRepository.selectAgent(userId);
        setImg(user);
        if (user != null)
            return ModelAdapter.ToUserModel(user.get(0));
        return null;
    }

    //设置头像
    private void setImg(List<User> list){
        list.forEach(i -> {
            i.setAvatar(imgPrefix + i.getAvatar());
        });
    }
}
