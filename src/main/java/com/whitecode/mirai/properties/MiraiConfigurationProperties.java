package com.whitecode.mirai.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName:MiraiConfiguration.java
 * @Author:Yem
 * @CreateTime:2023-03-17
 * @Description:
 */
@Data
@Component
@ConfigurationProperties("mirai.config")
public class MiraiConfigurationProperties {
    private String workSpace;

    /**
     * ANDROID_PHONE
     * ANDROID_PAD
     * ANDROID_WATCH
     * IPAD
     * MACOS
     */
    private String protocol;

    /**
     * 是否输出机器人日志
     */
    private boolean robootLogout;

    /**
     * 是否进入简化命令模式(推荐进入)
     */
    private boolean simplifyCommand = false;

    private String basePath;

}
