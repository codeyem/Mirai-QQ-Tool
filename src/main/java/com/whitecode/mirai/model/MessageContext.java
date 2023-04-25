package com.whitecode.mirai.model;

import lombok.Data;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.OnlineMessageSource;

/**
 * @ClassName:MessageContext.java
 * @Author:Yem
 * @CreateTime:2023-03-27
 * @Description:
 */
@Data
public class MessageContext {

    /**
     * 发送者
     */
    private Contact sender;

    /**
     * 消息事件主体
     */
    private Contact subject;

    /**
     * 消息链
     */
    private MessageChain messageChain;

    private MessageChain originalMessage;

    private OnlineMessageSource.Incoming source;

    /**
     * 自定义消息
     */
    private CustomMessage customMessage;

    public MessageContext(MessageEvent event){
        this.customMessage = CustomMessage.builder().message(event.getMessage().contentToString()).build();
        this.subject = event.getSubject();
        this.sender = event.getSender();
        this.originalMessage = event.getMessage();
        this.source = event.getSource();
    }
}
