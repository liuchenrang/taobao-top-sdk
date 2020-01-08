package com.github.liuchenrang.taobao.client.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chen
 */
@Data
public class CreatePwdResponse extends TopError {
    @JSONField(name = "tbk_tpwd_create_response")
    public Pwd tbkTaoPwdCreateResponse;
    @Data
    public static class Pwd{
        @JSONField(name = "request_id")

        String requestId;
        PwdData data;
    }
    @Data
    public static class  PwdData{
        String model;
    }
}
