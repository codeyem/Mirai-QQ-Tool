package com.whitecode.mirai.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @ClassName:CommandModel.java
 * @Author:Yem
 * @CreateTime:2023-04-04
 * @Description:
 */
@Getter
public class CommandModel {
    private HashSet<String> commandHeads;
    private HashSet<String> commandBodys;

    private CommandModel(HashSet<String> commandHeads, HashSet<String> commandBodys){
        this.commandHeads = commandHeads;
        this.commandBodys = commandBodys;
    }

    /**
     * 组命令行
     */
    private static final HashSet<String> GROUP_HEAD = new HashSet<>(Arrays.asList("#", "&", "!"));
    private static final HashSet<String> GROUP_BODY = new HashSet<>(Arrays.asList("hello", "fd"));

    public static CommandModel getGroupModel(){
        return new CommandModel(GROUP_HEAD, GROUP_BODY);
    }

    /**
     * 权限命令行
     */
    private static final HashSet<String> ROLE_HEAD = new HashSet<>(Arrays.asList("$"));
    private static final HashSet<String> ROLE_BODY = new HashSet<>(Arrays.asList("封号", "open-chat", "close-chat"));
    public static CommandModel getRoleModel(){
        return new CommandModel(ROLE_HEAD, ROLE_BODY);
    }

}
