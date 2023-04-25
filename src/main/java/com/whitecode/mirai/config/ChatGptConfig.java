package com.whitecode.mirai.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName:ChatGptConfig.java
 * @Author:Yem
 * @CreateTime:2023-04-13
 * @Description:
 */

@Data
@Component
public class ChatGptConfig {

    private Boolean open = false;
}
