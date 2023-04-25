package com.whitecode.mirai.client.model;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:ChatGptModel.java
 * @Author:Yem
 * @CreateTime:2023-04-13
 * @Description:
 */
public class ChatGptModel {
    private String model;

    private double temperature;

    private List<ChatGtpMessage> messages;

    private ChatGptModel(ChatGtpMessage message){
        this.model = "gpt-3.5-turbo";
        this.temperature = 0.1;
        this.messages = Arrays.asList(message);
    }

    public static ChatGptModel getDefault(String content){
        return new ChatGptModel(ChatGtpMessage.getDefault(content));
    }
}
