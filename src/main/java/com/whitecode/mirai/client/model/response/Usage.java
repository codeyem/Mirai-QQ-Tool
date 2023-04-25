package com.whitecode.mirai.client.model.response;

import lombok.Data;

/**
 * @ClassName:Usage.java
 * @Author:Yem
 * @CreateTime:2023-04-13
 * @Description:
 */
@Data
public class Usage {

    private Long prompt_tokens;

    private Long completion_tokens;

    private Long total_tokens;
}
