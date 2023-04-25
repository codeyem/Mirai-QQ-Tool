package com.whitecode.mirai.eventHanlder;

import com.whitecode.mirai.model.CustomMessage;
import com.whitecode.mirai.model.MessageContext;

/**
 * @ClassName:EventHanlder.java
 * @Author:Yem
 * @CreateTime:2023-04-13
 * @Description:
 */
public interface EventHanlder {
    CustomMessage execute(MessageContext messageContext);
}
