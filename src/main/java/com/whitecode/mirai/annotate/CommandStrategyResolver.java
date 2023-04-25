package com.whitecode.mirai.annotate;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName:CommandStrategyResolver.java
 * @Author:Yem
 * @CreateTime:2023-03-30
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface CommandStrategyResolver {
    String[] value() default "";
}
