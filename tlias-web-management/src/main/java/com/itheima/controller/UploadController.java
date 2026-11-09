package com.itheima.controller;


import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
 /*
******
 本地磁盘存储文件
*******
 @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {

        log.info("name:{}, age:{}, file:{}", name, age, file);
        String originalFilename = file.getOriginalFilename();
        String last = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename= UUID.randomUUID().toString()+last;
        //获取文件名
        String fileName = file.getOriginalFilename();
        //保存至本地
        file.transferTo(new File("D:/upload/" + newFilename));
        return Result.success();
    }*/

    @Autowired
    private AliyunOSSperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传{}",file.getOriginalFilename());
        //将文件交给阿里云OSS存储
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传OSS,url:{}",url);
        return Result.success(url);


    }
}
