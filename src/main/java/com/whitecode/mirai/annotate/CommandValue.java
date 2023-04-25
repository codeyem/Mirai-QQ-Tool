package com.whitecode.mirai.annotate;

import com.whitecode.mirai.enums.CommandTypeEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName:Command.java
 * @Author:Yem
 * @CreateTime:2023-03-28
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface CommandValue {
    CommandTypeEnum[] value() default CommandTypeEnum.DISABLE;
}
