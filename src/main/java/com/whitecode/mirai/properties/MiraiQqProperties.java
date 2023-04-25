package com.whitecode.mirai.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName:MiraiQqProperties.java
 * @Author:Yem
 * @CreateTime:2023-03-17
 * @Description:
 */
@Data
@Component
@ConfigurationProperties("mirai.qq-roboot")
public class MiraiQqProperties {
    private Long account;

    private String password;

    private String name = "哈巴";

    private String admin;
}
