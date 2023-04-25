package com.whitecode.mirai.command;

import cn.hutool.core.util.ObjectUtil;
import com.whitecode.mirai.enums.CommandTypeEnum;
import com.whitecode.mirai.model.CommandModel;
import com.whitecode.mirai.model.CustomMessage;
import com.whitecode.mirai.model.MessageContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:BaseGroupCommand.java
 * @Author:Yem
 * @CreateTime:2023-03-27
 * @Description:
 */
public abstract class BaseGroupCommand implements GroupCommand, RoleCommand{
    @Value("${mirai.qq-roboot.admin}")
    protected Long adminId;

    protected Map<String, CommandModel> commands = new HashMap<String, CommandModel>();
    /**
     * @Description:处理命令同意入库
     * @Author:Yem
     * @Date:2023/3/27
     * @param messageContext
     * @Return:com.whitecode.mirai.model.CustomMessage
     **/
    @Override
    public CustomMessage execute(MessageContext messageContext) {

        /**
         * 先校验权限
         */
        CustomMessage checkRole = checkRole(commands.get(CommandTypeEnum.ROLE.name()), messageContext.getCustomMessage().getMessage(), messageContext.getSender().getId());
        if(checkRole.getIsSuccess()){
            /**
             * 是权限组命令，且有权限
             */
            return executeHandle(messageContext, checkRole);
        }

        if(!ObjectUtil.isEmpty(checkRole.getCheckResutl())){
            /**
             * 是权限组但没权限
             */
            return checkRole;
        }
        /**
         * 不在权限组，判断是否群组命令
         */

        CustomMessage checkCommand = CustomMessage.checkCommand(commands.get(CommandTypeEnum.GROUP.name()), messageContext.getCustomMessage().getMessage());
        if (!checkCommand.getIsSuccess()) {
            /**
             * 是权限组，但是无权限
             */
            return CustomMessage.fail();
        }
        return executeHandle(messageContext, checkCommand);
    }

    /**
     * @Description:命令后续处理
     * @Author:Yem
     * @Date:2023/3/27
     * @param messageContext
     * @Return:com.whitecode.mirai.model.CustomMessage
     **/
    protected abstract CustomMessage executeHandle(MessageContext messageContext, CustomMessage customMessage);

    @Override
    public void initCommands(String key, CommandModel commandModel) {
        commands.put(key, commandModel);
    }
}
