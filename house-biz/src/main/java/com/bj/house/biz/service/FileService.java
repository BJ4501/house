package com.bj.house.biz.service;

import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.time.Instant;
import java.util.List;

/**
 * 用来负责图片等文件的上传操作
 * Created by BJ on 2018/1/20.
 */
@Service
public class FileService {

    @Value("${file.path}")
    private String filePath;

    //将上传文件列表返回一个列表路径
    //存入数据库的只是一个路径

    public List<String> getImgPath(List<MultipartFile> files){
        List<String> paths = Lists.newArrayList();

        files.forEach(file -> {
            File localFile = null;
            if (!file.isEmpty()){
                try {
                    localFile = saveToLocal(file,filePath);
                    //返回的结果只是相对路径
                    String path = StringUtils.substringAfterLast(localFile.getAbsolutePath(),filePath);
                    paths.add(path);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        return paths;
    }

    private File saveToLocal(MultipartFile file, String filePath) throws IOException {
        File newFile = new File(filePath+"/"+ Instant.now().getEpochSecond()+"/"+file.getOriginalFilename());
        if(!newFile.exists()){
            //如果有这个文件夹就不创建
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        }
        //使用Guava的Files将上传的文件写到刚才创建的newFile中
        Files.write(file.getBytes(),newFile);
        return null;
    }


}
