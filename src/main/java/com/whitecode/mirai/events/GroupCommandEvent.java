package com.whitecode.mirai.events;

import com.whitecode.mirai.command.AdminCommand;
import com.whitecode.mirai.eventHanlder.AtEventHanlder;
import com.whitecode.mirai.model.CustomMessage;
import com.whitecode.mirai.model.MessageContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.EventPriority;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * @ClassName:GroupEvent.java
 * @Author:Yem
 * @CreateTime:2023-03-27
 * @Description:
 */

@Data
@Component
@AllArgsConstructor
public class GroupCommandEvent extends SimpleListenerHost {

    private static final At botAt = new At(2071490196);

    private AdminCommand adminCommand;

    private AtEventHanlder atEventHanlder;

    @NotNull
    @EventHandler(priority = EventPriority.NORMAL)
    public ListeningStatus onMessage(@NotNull GroupMessageEvent event){
        MessageContext messageContext = new MessageContext(event);
        CustomMessage result = null;
        if(event.getMessage().contains(botAt)){
            /**
             * 判断是否是@本人事件
             */
            result = atEventHanlder.execute(messageContext);
        }else {
            result = adminCommand.execute(messageContext);
        }

        if(result.getIsSuccess()) {
            event.getSubject().sendMessage(result.getCheckResutl());
        }
        event.intercept();
        return ListeningStatus.LISTENING;
    }

}
