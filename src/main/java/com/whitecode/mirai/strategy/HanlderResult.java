package com.whitecode.mirai.strategy;

import lombok.Builder;
import lombok.Data;
import net.mamoe.mirai.message.data.Message;

/**
 * @ClassName:HanlderResult.java
 * @Author:Yem
 * @CreateTime:2023-03-30
 * @Description:
 */
@Data
@Builder
public class HanlderResult {
    private boolean isSuccess;

    private String returnMessage;

    private Message message;

    public static HanlderResult success(String message){
        return HanlderResult.builder().isSuccess(true).returnMessage(message).build();
    }

}
