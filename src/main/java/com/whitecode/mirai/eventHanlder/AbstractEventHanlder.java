package com.whitecode.mirai.eventHanlder;

import cn.hutool.core.util.ObjectUtil;
import com.whitecode.mirai.model.CustomMessage;
import com.whitecode.mirai.model.MessageContext;
import lombok.AllArgsConstructor;
import net.mamoe.mirai.message.data.Message;
import org.springframework.stereotype.Service;

/**
 * @ClassName:AbstractEventHanlder.java
 * @Author:Yem
 * @CreateTime:2023-04-13
 * @Description:
 */
@Service
@AllArgsConstructor
public abstract class AbstractEventHanlder implements EventHanlder{
    @Override
    public CustomMessage execute(MessageContext messageContext) {
        String message = messageContext.getCustomMessage().getMessage().split(" ")[1];
        Message hanlder = hanlder(messageContext, message);
        if(ObjectUtil.isNull(hanlder)){
            return CustomMessage.fail();
        }
        return CustomMessage.success().checkResutl(hanlder).build();
    }
    public abstract Message hanlder(MessageContext messageContext, String message);
}
