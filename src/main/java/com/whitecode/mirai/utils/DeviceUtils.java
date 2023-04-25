package com.whitecode.mirai.utils;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.utils.DeviceInfo;

import java.io.File;

/**
 * @Author ym
 * @Date 2023/3/18 0:35
 * @Description:yem
 */
@Slf4j
public class DeviceUtils {
    public static String loadDeviceInfo(String path, String qq) {
        String devicePath = path +"\\device.json";
        File file = new File(devicePath);
        String deviceInfoJson = null;
        if (file.exists()) {
            FileReader reader = new FileReader(file);
            deviceInfoJson = reader.readString();
        }else {
            deviceInfoJson = generaterDeviceInfo(qq);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(deviceInfoJson);
        }
        log.info("device.json:{}", deviceInfoJson);
        return deviceInfoJson;
    }

    private static String generaterDeviceInfo(String qq){
        log.info("生成随机deviceInfo");
        String imei = "86"+(qq+""+qq).substring(0, 12);
        imei = imei + luhn(imei);
        DeviceInfo deviceInfo = new DeviceInfo(
                ("MIRAI."+qq.toString().substring(0,6)+".001").getBytes(),//display
                "mirai".getBytes(), //product
                "mirai".getBytes(), //device
                "mirai".getBytes(), //board
                "mamoe".getBytes(), //brand
                "mirai".getBytes(), //model
                "unknown".getBytes(), //bootloader
                ("mamoe/mirai/mirai:10/MIRAI.200122.001/"+qq.toString().substring(0,7)+":user/release-keys").getBytes(), //fingerprint
                SecureUtil.md5(imei).toUpperCase().getBytes(), //bootId
                ("Linux version 3.0.31-"+qq.toString().substring(0,8)+ "(android-build@xxx.xxx.xxx.xxx.com)").getBytes(), //procVersion
                new byte[0], //baseBand
                new DeviceInfo.Version(), //version
                "T-Mobile".getBytes(), //simInfo
                "android".getBytes(), // osType
                "02:00:00:00:00:00".getBytes(), //macAddress
                "02:00:00:00:00:00".getBytes(), //wifiBSSID
                "<unknown ssid>".getBytes(), //wifiSSID
                SecureUtil.md5().digest(imei), //  imsiMd5
                imei, //imei
                "wifi".getBytes() // apn
        );
        return new JSONObject(deviceInfo).toString();
    }

    private static Integer luhn(String imei){
        boolean odd = false;
        Integer sum = 0;
        for (int i = 0; i < imei.length(); i++) {
            Integer temp = Integer.valueOf(imei.substring(i, i+1));
            odd = !odd;
            if (odd) {
                sum +=  temp;
            } else {
                Integer s = temp * 2;
                s = s % 10 + s / 10;
                sum += s;
            }
        }

        return (10 - sum % 10) % 10;
    };
}
