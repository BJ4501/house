package com.bj.house.biz.service;

import com.bj.house.biz.persistance.repository.UserRepository;
import com.bj.house.common.entity.User;
import com.bj.house.common.model.UserModel;
import com.bj.house.common.utils.BeanHelper;
import com.bj.house.common.utils.HashUtils;
import com.bj.house.common.utils.ModelAdapter;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by BJ on 2018/1/12.
 */
@Service
public class UserService {

    @Autowired
    private FileService fileService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @Value("${file.prefix}")
    private String imgPrefix;

    /**
     * 1.插入数据库，状态时非激活；密码加盐MD5；保存头像到本地
     * 2.生成随机Key，绑定email
     * 3.给用户发送邮件
     * @param account
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addAccount(UserModel account) {
        //ModelAdapter :将Vo的Model转换为entity Model
        account.setPasswd(HashUtils.encryPassword(account.getNewPassword()));
        //上传头像，保存地址
        List<String> imgList = fileService.getImgPath(Lists.newArrayList(account.getAvatarFile()));
        if (!imgList.isEmpty()){
            account.setAvatar(imgList.get(0));
        }

        //NOTE：填充默认值，在这里使用一次BeanHelper，以后就不用了
        // 因为在JPA中已经对默认值进行了设置，简化了操作，这里记录一
        // 下BeanHelper设计时是如何使用的。
        // 如果以后有需要在非JPA环境下设置默认值的操作，可以直接拿去用
        BeanHelper.setDefaultProp(account,UserModel.class);
        BeanHelper.onInsert(account); //插入操作，将创建时间进行设置
        account.setEnable(0);
        //使用Jpa插入操作
        User user = userRepository.save(ModelAdapter.ToUser(account));
        //发送注册邮件
        mailService.registerNotify(account.getEmail());
        return true;
    }


    public boolean enable(String key) {
        return mailService.enable(key);
    }

    //校验用户名密码
    public UserModel auth(String username, String password) {
        User user = userRepository.findByEmail(username);
        String pwd = HashUtils.encryPassword(password);
        //如果用户不存在，用户不为已激活状态，用户密码不正确-->返回null
        if (user == null || user.getEnable() != 1 || !user.getPasswd().equals(pwd)){
            return null;
        }
        user.setAvatar(imgPrefix+user.getAvatar());

        //TODO 转换为UserModel
        return ModelAdapter.ToUserModel(user);
    }
}
