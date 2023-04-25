package com.whitecode.mirai.client.model.response;

import com.whitecode.mirai.client.model.ChatGtpMessage;
import lombok.Data;

/**
 * @ClassName:Choices.java
 * @Author:Yem
 * @CreateTime:2023-04-13
 * @Description:
 */
@Data
public class Choices {
    private Long index;

    private ChatGtpMessage message;
}
