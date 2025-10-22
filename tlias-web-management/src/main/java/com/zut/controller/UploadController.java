package com.zut.controller;

import com.zut.pojo.Result;
import com.zut.util.AliyunOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.MultiPixelPackedSampleModel;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    String bucketName = "javaweb-proj01";
    //本地存储
   /* @PostMapping("/upload")
     public Result upload(String username, Integer age, MultipartFile file) throws Exception {
         log.info("文件上传：username:{},age:{},file:{}",username,age,file);

        //获得原始文件名
        String originalFilename = file.getOriginalFilename();
        //截取文件后缀
        String exName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //构建不能重复的文件名。UUID
        String newFileName = UUID.randomUUID().toString()+exName;
        //将文件存储在本地
        file.transferTo(new File("D:/tempFile/images"+ newFileName));

         return Result.success();
     }*/

    //oss云存储
    @PostMapping("/upload")
    public Result upload (MultipartFile file) throws Exception{
        log.info("文件上传，上传文件名：{}",file.getOriginalFilename());
        String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = AliyunOSSUtils.upload(endpoint, bucketName, file.getBytes(), exName);
        return Result.success(url);
    }
}
