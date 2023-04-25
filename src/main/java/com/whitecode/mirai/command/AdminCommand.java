package com.whitecode.mirai.command;

import com.whitecode.mirai.annotate.CommandValue;
import com.whitecode.mirai.config.MiraiBeanListener;
import com.whitecode.mirai.model.CommandModel;
import com.whitecode.mirai.model.CustomMessage;
import com.whitecode.mirai.model.MessageContext;
import com.whitecode.mirai.strategy.HanlderResult;
import net.mamoe.mirai.message.data.At;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.whitecode.mirai.enums.CommandTypeEnum.GROUP;
import static com.whitecode.mirai.enums.CommandTypeEnum.ROLE;

/**
 * @ClassName:AdminCommand.java
 * @Author:Yem
 * @CreateTime:2023-03-27
 * @Description:
 */
@Component
@CommandValue({GROUP, ROLE})
public class AdminCommand extends BaseGroupCommand{

    @Resource
    private MiraiBeanListener miraiBeanListener;


    @Override
    protected CustomMessage executeHandle(MessageContext messageContext, CustomMessage customMessage) {
        HanlderResult hanlder = miraiBeanListener.getMessageHanlder(customMessage.getCommandBody()).hanlder(customMessage.getMessage());
        if(!hanlder.isSuccess()){
            return CustomMessage.fail();
        }
        return CustomMessage.success().checkResutl(new At(messageContext.getSender().getId()).plus(hanlder.getReturnMessage())).build();
    }

    @Override
    public CustomMessage isCommand(String message) {
        return CustomMessage.checkCommand(commands.get(GROUP.name()), message);
    }


    @Override
    public CustomMessage checkRole(CommandModel commandModel, String message, long senderId) {
        CustomMessage checkCommand = CustomMessage.checkCommand(commandModel, message);
        if(!checkCommand.getIsSuccess()){
            return CustomMessage.fail();
        }else if(checkCommand.getIsSuccess() && !(senderId == adminId)){
            /**
             * 是权限组命令，但是没权限
             */
            return CustomMessage.checkRoleFail(senderId, "没权限");
        }else {
            /**
             * 是权限组命令，且有权限
             */
            return checkCommand;
        }
    }
}
