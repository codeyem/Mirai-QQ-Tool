package com.whitecode.mirai.config;

import com.whitecode.mirai.annotate.CommandStrategyResolver;
import com.whitecode.mirai.annotate.CommandValue;
import com.whitecode.mirai.command.Command;
import com.whitecode.mirai.enums.CommandTypeEnum;
import com.whitecode.mirai.model.CommandModel;
import com.whitecode.mirai.strategy.MessageHanlder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:MiraiBeanPostProcess.java
 * @Author:Yem
 * @CreateTime:2023-03-27
 * @Description:
 */
@Slf4j
@Component
public class MiraiBeanListener implements ApplicationListener<ContextRefreshedEvent> {


    Map<String, MessageHanlder> hanlderMap = new HashMap<String, MessageHanlder>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("开始注册指令");
        ApplicationContext context = event.getApplicationContext().getParent();
        try {
            if (null == context) {
                Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(CommandValue.class);
                beans.forEach((key, value) -> {
                    if(value instanceof Command){
                        CommandValue commandAnno = value.getClass().getAnnotation(CommandValue.class);
                        CommandTypeEnum[] typeEnums = commandAnno.value();
                        Command command = (Command) value;
                        Arrays.stream(typeEnums).forEach(e ->{
                            switch (e){
                                case ROLE:
                                    command.initCommands(e.name(), CommandModel.getRoleModel());
                                    break;
                                case GROUP:
                                    command.initCommands(e.name(), CommandModel.getGroupModel());
                            }
                        });
                    }
                });

                /**
                 * 注册策略处理
                 */
                Map<String, Object> messageHanlders = event.getApplicationContext().getBeansWithAnnotation(CommandStrategyResolver.class);
                messageHanlders.forEach((key, value) ->{
                    if(value instanceof MessageHanlder ){
                        CommandStrategyResolver resolver = value.getClass().getAnnotation(CommandStrategyResolver.class);
                        Arrays.stream(resolver.value()).forEach(e -> hanlderMap.put(e, (MessageHanlder)value));
                    }
                });
            }
        }catch (Exception e){
            log.error("注册指令异常");
            log.error(e.getMessage(), e);
        }
    }

    public MessageHanlder getMessageHanlder(String message){
        return hanlderMap.get(message);
    }
}
