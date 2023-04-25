package com.whitecode.mirai.strategy.hanlder;

import com.whitecode.mirai.annotate.CommandStrategyResolver;
import com.whitecode.mirai.strategy.HanlderResult;
import com.whitecode.mirai.strategy.MessageHanlder;

/**
 * @ClassName:RepeatHanlder.java
 * @Author:Yem
 * @CreateTime:2023-03-30
 * @Description:
 */
@CommandStrategyResolver("fd")
public class RepeatHanlder implements MessageHanlder {
    @Override
    public HanlderResult hanlder(String message) {

        return HanlderResult.success(message);
    }
}
