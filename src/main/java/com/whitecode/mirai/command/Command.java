package com.whitecode.mirai.command;

import com.whitecode.mirai.model.CommandModel;
import com.whitecode.mirai.model.CustomMessage;

/**
 * @ClassName:Command.java
 * @Author:Yem
 * @CreateTime:2023-03-24
 * @Description:
 */
public interface Command {
    CustomMessage isCommand(String message);

    void initCommands(String key, CommandModel commandModel);
}
