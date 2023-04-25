package com.whitecode.mirai.client.model;

import lombok.Data;

/**
 * @ClassName:ChatGtpMessage.java
 * @Author:Yem
 * @CreateTime:2023-04-13
 * @Description:
 */
@Data
public class ChatGtpMessage {
    private String role;

    private String content;

    public ChatGtpMessage(String content){
        this.role = "user";
        this.content = content;
    }

    public static ChatGtpMessage getDefault(String content){
        return new ChatGtpMessage(content);
    }

}
