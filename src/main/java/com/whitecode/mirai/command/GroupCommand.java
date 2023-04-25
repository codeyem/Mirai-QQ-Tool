package com.whitecode.mirai.command;

import com.whitecode.mirai.model.CustomMessage;
import com.whitecode.mirai.model.MessageContext;

/**
 * @ClassName:GroupCommand.java
 * @Author:Yem
 * @CreateTime:2023-03-24
 * @Description:
 */
public interface GroupCommand extends Command{
    CustomMessage execute(MessageContext messageContext);
}
