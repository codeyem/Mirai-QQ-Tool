package com.whitecode.mirai.strategy;

/**
 * @ClassName:MessageHanlder.java
 * @Author:Yem
 * @CreateTime:2023-03-30
 * @Description:
 */
public interface MessageHanlder {
    HanlderResult hanlder(String message);
}
