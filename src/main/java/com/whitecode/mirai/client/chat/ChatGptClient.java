package com.whitecode.mirai.client.chat;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.google.gson.Gson;
import com.whitecode.mirai.client.model.ChatGptModel;
import com.whitecode.mirai.client.model.response.ChatGptResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:ChatGptClient.java
 * @Author:Yem
 * @CreateTime:2023-04-13
 * @Description:
 */
@Component
@Slf4j
public class ChatGptClient {
    private static final String url = "https://api.openai.com/v1/chat/completions";

    private static String secret = null;

    static {
        try {
            secret = new String(Base64.getDecoder().decode("c2stRkFUaGo3NEhIa0pMS0RVanV5NmdUM0JsYmtGSldrRzdwY3U1S1hPSmdqUkJOMElV"),"utf-8");
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }

    public String messgae(String message){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", secret);
        String json = new Gson().toJson(ChatGptModel.getDefault(message));
        HttpRequest request = HttpRequest.post(url).addHeaders(headers).body(json);
        request.setHttpProxy("127.0.0.1", 7890);
        HttpResponse response = request.execute();
        Gson gson = new Gson();
        System.out.println(response.body());
        ChatGptResponseModel responseModel = gson.fromJson(response.body(), ChatGptResponseModel.class);
        String content = responseModel.getChoices().get(0).getMessage().getContent();
        return content;
    }
}
