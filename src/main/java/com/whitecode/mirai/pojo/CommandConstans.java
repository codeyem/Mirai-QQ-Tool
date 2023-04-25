package com.whitecode.mirai.pojo;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @ClassName:CommandConstans.java
 * @Author:Yem
 * @CreateTime:2023-03-28
 * @Description:
 */
public class CommandConstans {

    /**
     * 组命令行
     */
    public static final HashSet<String> GROUP_HEAD = new HashSet<>(Arrays.asList("#", "&", "!"));
    public static final HashSet<String> GROUP_BODY = new HashSet<>(Arrays.asList("hello", "fd"));


    /**
     * 权限命令行
     */
    public static final HashSet<String> ROLE_HEAD = new HashSet<>(Arrays.asList("$"));
    public static final HashSet<String> ROLE_BODY = new HashSet<>(Arrays.asList("封号", "open-chat", "close-chat"));
}
