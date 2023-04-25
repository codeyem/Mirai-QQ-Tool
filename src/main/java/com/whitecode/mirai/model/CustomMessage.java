package com.whitecode.mirai.model;

import cn.hutool.core.util.StrUtil;
import lombok.Builder;
import lombok.Data;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Message;

import java.util.HashSet;

/**
 * @ClassName:CustomMessage.java
 * @Author:Yem
 * @CreateTime:2023-03-27
 * @Description:
 */
@Data
@Builder
public class CustomMessage{
    private Boolean isSuccess;

    /**
     * 校验结果
     */
    private Message checkResutl;

    /**
     * 指令头
     */
    private String commandHead;

    /**
     * 指令体
     */
    private String commandBody;

    /**
     * 消息体
     */
    private String message;


    public static CustomMessage fail(){
         return CustomMessage.builder().isSuccess(false).build();
    }

    public static CustomMessageBuilder success(){
        return CustomMessage.builder().isSuccess(true);
    }

    public static CustomMessage success(Message message){
        return success().checkResutl(message).build();
    }

    public static CustomMessage checkRoleFail(long senderID, String messgae){
        return CustomMessage.builder().isSuccess(false).checkResutl(new At(senderID).plus(messgae)).build();
    }

    public static CustomMessage checkCommand(CommandModel commandModel, String message){
        HashSet<String> heads = commandModel.getCommandHeads();
        if(StrUtil.isEmptyIfStr(message)){
            return fail();
        }

//        //命令和消息内容中间必须有间隔
//        int index = message.indexOf(" ");
//        if(index == -1){
//            return fail();
//        }
        String[] messages = message.split(" ");
        //截取命令
        String commandMsg = messages[0];
        //命令头
        String commandMsgHead = commandMsg.substring(0,1);
        if(!heads.contains(commandMsgHead)){
            return fail();
        }
        HashSet<String> bodys = commandModel.getCommandBodys();
        //命令体
        String commandMsgBody = commandMsg.substring(1);
        if(!bodys.contains(commandMsgBody)){
            return fail();
        }

        //消息
        CustomMessage customMessage = CustomMessage.builder()
                .message(messages.length > 1 ? messages[1] : "")
                .commandHead(commandMsgHead)
                .commandBody(commandMsgBody)
                .isSuccess(true)
                .build();
        return customMessage;
    }
}
