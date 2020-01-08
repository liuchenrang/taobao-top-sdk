package com.github.liuchenrang.taobao.client.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chen
 */
@Data
public class TopError {
    @JSONField(name = "error_response")
    ErrorInfo errorResponse;
    @Data
    public static class ErrorInfo{

        Integer code;
        String msg;
        @JSONField(name = "sub_msg")

        String subMsg;
        @JSONField(name = "sub_code")

        String subCode;
    }
}
