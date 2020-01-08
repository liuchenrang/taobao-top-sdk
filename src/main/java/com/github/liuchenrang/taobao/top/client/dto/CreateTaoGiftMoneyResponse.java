package com.github.liuchenrang.taobao.top.client.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chen
 */
@Data
public class CreateTaoGiftMoneyResponse extends TopError {
    @JSONField(name = "tbk_dg_vegas_tlj_create_response")
    public CreateResponse response;

    @Data
    public static class CreateResponse {
        @JSONField(name = "request_id")
        String requestId;
        Result result;
    }

    @Data
    public static class Result {
        TaoLinJinInfo model;
        Boolean success;
    }
    @Data
    public static class TaoLinJinInfo {
        @JSONField(name = "rights_id")

        String rightsId;
        @JSONField(name = "send_url")

        String sendUrl;
        @JSONField(name = "vegas_code")

        String vegasCode;
    }
}
