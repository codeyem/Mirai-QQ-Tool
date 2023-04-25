package com.whitecode.mirai.strategy.hanlder;

import com.whitecode.mirai.annotate.CommandStrategyResolver;
import com.whitecode.mirai.client.chat.ChatGptClient;
import com.whitecode.mirai.config.ChatGptConfig;
import com.whitecode.mirai.strategy.HanlderResult;
import com.whitecode.mirai.strategy.MessageHanlder;
import lombok.AllArgsConstructor;

/**
 * @ClassName:OpenChatHandle.java
 * @Author:Yem
 * @CreateTime:2023-04-06
 * @Description:
 */
@CommandStrategyResolver("close-chat")
@AllArgsConstructor
public class CloseChatHandle implements MessageHanlder {

    private ChatGptConfig config;

    private ChatGptClient client;

    @Override
    public HanlderResult hanlder(String message) {
        config.setOpen(false);
        return HanlderResult.success("已关闭");
    }
}
