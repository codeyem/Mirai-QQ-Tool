package com.whitecode.mirai.pojo;

import java.util.HashSet;

/**
 * @ClassName:CommandProperties.java
 * @Author:Yem
 * @CreateTime:2023-03-24
 * @Description:
 */
public class CommandProperties {

    private HashSet<String> commandHead;
    private HashSet<String> commandBody;

    private CommandProperties(){}

    public HashSet<String> getCommandHead() {
        return commandHead;
    }

    public HashSet<String> getCommandBody() {
        return commandBody;
    }

    private CommandProperties addHead(HashSet<String> commandHead){
        this.getCommandHead().addAll(commandHead);
        return this;
    }

    private CommandProperties addBody(HashSet<String> commandBody){
        this.getCommandBody().addAll(commandBody);
        return this;
    }

    public static CommandProperties getGroupCommand(){
        return new CommandProperties();
    }

}
