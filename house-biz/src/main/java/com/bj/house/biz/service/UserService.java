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

    //构建一个Guava缓存
    private final Cache<String,String> registerCache = CacheBuilder
            .newBuilder()
            .maximumSize(100) //最大缓存数量
            .expireAfterAccess(15, TimeUnit.MINUTES) //设置过期时间
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    userRepository.deleteByEmail(notification.getValue());
                }
            }).build();

    @Autowired
    private FileService fileService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @Value("${domain.name}")
    private String domainName;

    public List<User> getUsers(){

        List<User> tmp = new ArrayList<>();
        User user = new User();
        user.setName("dsadsad");
        tmp.add(user);

        return tmp;
    }

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
        List<String> imgList = fileService.getImgPath(Lists.newArrayList(account.getAvatorFile()));
        if (!imgList.isEmpty()){
            account.setAvator(imgList.get(0));
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
        registerNotify(account.getEmail());
        return false;
    }

    /**
     * 发送注册邮件-异步操作
     * 1.缓存Key-email的关系
     * 2.使用Spring mail发送邮件
     * 3.借助异步框架进行异步操作
     * @param email
     */
    @Async //SpringBoot异步框架:使用这个注解后，在调用这个方法时，SpringBoot会创建线程池，将此方法放入线程池中运行
    public void registerNotify(String email) {
        //随机生成一个10位的数字字母字符串
        String randomKey = RandomStringUtils.randomAlphabetic(10);
        registerCache.put(randomKey,email);
        //生成链接样式：
        //"/accounts/verify?key="+randomKey
        String url = "http://"+domainName+"/accounts/verify?key="+randomKey;
        mailService.sendEmail("激活邮件，地址：",url,email);
    }

}
