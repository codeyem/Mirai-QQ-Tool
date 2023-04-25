package com.whitecode.mirai.command;

import com.whitecode.mirai.model.CommandModel;
import com.whitecode.mirai.model.CustomMessage;

/**
 * @ClassName:RoleCommand.java
 * @Author:Yem
 * @CreateTime:2023-03-27
 * @Description:权限命令
 */
public interface RoleCommand extends Command{

    CustomMessage checkRole(CommandModel commandModel, String message, long senderId);
}
