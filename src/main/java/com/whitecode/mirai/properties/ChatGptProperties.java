package com.whitecode.mirai.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName:ChatGptProperties.java
 * @Author:Yem
 * @CreateTime:2023-04-25
 * @Description:
 */
@Data
@Component
@ConfigurationProperties("mirai.chat-gpt")
public class ChatGptProperties {
}
