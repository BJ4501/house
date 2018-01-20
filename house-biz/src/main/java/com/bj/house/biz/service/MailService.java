package com.bj.house.biz.service;

import com.bj.house.biz.persistance.repository.UserRepository;
import com.bj.house.common.entity.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by BJ on 2018/1/20.
 */
@Service
public class MailService {

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
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${domain.name}")
    private String domainName;

    /**
     * 发送邮件
     * @param title 邮件标题
     * @param url 激活邮件地址
     * @param email 邮箱位置
     */
    public void sendEmail(String title, String url, String email) {
        //构建邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); //发送者
        message.setTo(email); //接收者
        message.setText(title + url); //内容
        mailSender.send(message);
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
        sendEmail("激活邮件，地址：",url,email);
    }

    //检测邮件Key合法性
    public boolean enable(String key) {
        String email = registerCache.getIfPresent(key);
        if (StringUtils.isBlank(email)){
            return false;
        }
        User updateUser = userRepository.findByEmail(email);
        //如果这个邮箱的用户不存在，也返回false
        if (updateUser == null)
            return false;
        updateUser.setEnable(1);
        //更新用户状态为已激活
        userRepository.save(updateUser);
        registerCache.invalidate(key);
        return true;
    }
}
