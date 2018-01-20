package com.bj.house.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by BJ on 2018/1/20.
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;


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
}
