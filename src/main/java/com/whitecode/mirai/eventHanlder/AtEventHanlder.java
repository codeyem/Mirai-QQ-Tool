package com.whitecode.mirai.eventHanlder;

import com.whitecode.mirai.client.chat.ChatGptClient;
import com.whitecode.mirai.config.ChatGptConfig;
import com.whitecode.mirai.model.MessageContext;
import lombok.AllArgsConstructor;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.QuoteReply;
import org.springframework.stereotype.Service;

/**
 * @ClassName:AtEventHanlder.java
 * @Author:Yem
 * @CreateTime:2023-04-13
 * @Description:
 */
@Service
@AllArgsConstructor
public class AtEventHanlder extends AbstractEventHanlder{

    private ChatGptClient client;

    private ChatGptConfig config;

    @Override
    public Message hanlder(MessageContext messageContext, String message) {
        if(config.getOpen()){
            QuoteReply quoteReply = new QuoteReply(messageContext.getSource());
            String messgae = client.messgae(message);
            MessageChain plus = quoteReply.plus(messgae);
            return plus;
        }else {
            return null;
        }
        //QuoteReply quoteReply = messageContext.getOriginalMessage().get(QuoteReply.Key);
    }
}
