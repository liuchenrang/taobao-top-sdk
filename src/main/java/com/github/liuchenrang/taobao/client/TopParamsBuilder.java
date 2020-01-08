package com.github.liuchenrang.taobao.client;

import com.github.liuchenrang.taobao.client.utils.TaobaoHashMap;
import com.github.liuchenrang.taobao.client.utils.TaobaoUtils;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


/**
 * @author chen
 */
@Builder
@Slf4j
public class TopParamsBuilder {
    public static final String METHOD_TAOBAO_TBK_TPWD_CREATE = "taobao.tbk.tpwd.create";
    public static final String METHOD_TAOBAO_TBK_DG_VEGAS_TLJ_CREATE = "taobao.tbk.dg.vegas.tlj.create";
    String url;
    RequestParametersHolder holder ;
    private String method;
    private String appKey;
    private String appSecret;
    private TaobaoHashMap taobaoHashMap;
    public static final String DATE_FORMAT_ISO8601 = "yyyy-MM-dd HH:mm:ss";

    private String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_ISO8601);
        return df.format(date);
    }

    public String sign(){
        holder = new RequestParametersHolder();
        holder.setProtocalMustParams(new TaobaoHashMap());
        holder.getProtocalMustParams().put(Constants.METHOD,method);
        long timestamp = System.currentTimeMillis();
        Date time = new Date(timestamp);
        String value = formatIso8601Date(time);
        holder.getProtocalMustParams().put(Constants.TIMESTAMP, value);
        holder.getProtocalMustParams().put(Constants.FORMAT,Constants.FORMAT_JSON);
        holder.getProtocalMustParams().put(Constants.APP_KEY,appKey);
        holder.getProtocalMustParams().put(Constants.VERSION,"2.0");
        holder.getProtocalMustParams().put(Constants.SIGN_METHOD,Constants.SIGN_METHOD_MD5);
        holder.setProtocalOptParams(taobaoHashMap);
        String sign = "";
        try {
            sign = TaobaoUtils.signTopRequest(holder,appSecret,Constants.SIGN_METHOD_MD5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sign;
    }
    public String buildMustParam(){
        TaobaoHashMap params = holder.getProtocalMustParams();
        return getString(params);
    }
    public String buildOptParam(){
        TaobaoHashMap params = holder.getProtocalOptParams();
        return getString(params);
    }
    public String buildUrl(){
        String sign = sign();
        return url + "?" + buildMustParam() + buildOptParam() + "&sign=" + sign;
    }
    private String getString(TaobaoHashMap protocalMustParams) {
        Set<String> stringSet = protocalMustParams.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        for (String key:stringSet){
            String value = protocalMustParams.get(key);
            try {
                value = URLEncoder.encode(value, "UTF-8");
                stringBuilder.append(key).append("=").append(value).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return stringBuilder.toString();
    }
}
