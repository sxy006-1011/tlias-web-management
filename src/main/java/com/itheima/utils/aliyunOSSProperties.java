package com.itheima.utils;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class aliyunOSSProperties {
    private String endPoint;
    private String bucketName;
    private String region;
}
