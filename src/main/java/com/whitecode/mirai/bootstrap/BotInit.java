package com.whitecode.mirai.bootstrap;

import com.whitecode.mirai.events.GroupCommandEvent;
import com.whitecode.mirai.properties.MiraiConfigurationProperties;
import com.whitecode.mirai.properties.MiraiQqProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import xyz.cssxsh.mirai.tool.FixProtocolVersion;

import javax.annotation.Resource;
import java.io.File;

/**
 * @ClassName:BotInit.java
 * @Author:Yem
 * @CreateTime:2023-03-24
 * @Description:
 */
@Slf4j
@Component
public class BotInit implements ApplicationRunner {
    @Resource
    private MiraiConfigurationProperties configurationProperties;

    @Resource
    private MiraiQqProperties qqProperties;
    @Resource
    private GroupCommandEvent event;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //注册事件
        BotConfiguration botConfiguration = initConfig();

        //协议修复
        FixProtocolVersion.update();

        Bot bot = BotFactory.INSTANCE.newBot(qqProperties.getAccount(), qqProperties.getPassword(), botConfiguration);
        GlobalEventChannel.INSTANCE.registerListenerHost(event);
        GlobalEventChannel instance = GlobalEventChannel.INSTANCE;
        bot.login();
        bot.join();
        //开个新线程跑，避免阻塞主线程
        new Thread(() -> bot.join()).start();
    }

    private BotConfiguration initConfig(){

        BotConfiguration configuration = new BotConfiguration();
        String basePath = configurationProperties.getWorkSpace() + "\\" + qqProperties.getAccount();
        String devicePath = basePath +"\\device.json";
        //工作目录
        configuration.setCacheDir(new File(basePath));
        //加载设备信息
        configuration.fileBasedDeviceInfo(devicePath);
        //设置策略
        configuration.setHeartbeatStrategy(BotConfiguration.HeartbeatStrategy.STAT_HB);
        //设置缓存目录
        configuration.setCacheDir(new File(basePath + "\\" + "cache"));
        //设置协议
        configuration.setProtocol(BotConfiguration.MiraiProtocol.valueOf(configurationProperties.getProtocol()));
        //自定义缓存
        BotConfiguration.ContactListCache cache = new BotConfiguration.ContactListCache();
        // 开启好友列表缓存
        cache.setFriendListCacheEnabled(false);
        // 关闭群成员列表缓存, 开启可能导致无法判断群管理员
        cache.setGroupMemberListCacheEnabled(false);
        // 可选设置有更新时的保存时间间隔, 默认 60 秒
        cache.setSaveIntervalMillis(60000);
        configuration.setContactListCache(cache);
        return configuration;
    }

}
