package com.whitecode.mirai.client.model.response;

import lombok.Data;

import java.util.List;

/**
 * @ClassName:ChatGptResponseModel.java
 * @Author:Yem
 * @CreateTime:2023-04-13
 * @Description:
 */
@Data
public class ChatGptResponseModel {
    private String id;

    private String object;

    private Long created;

    private List<Choices> choices;

    private String finish_reason;
}
