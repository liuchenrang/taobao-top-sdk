package com.github.liuchenrang.taobao.top.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chen
 */
@Slf4j
public class TopClient {
    private RestTemplate restTemplate;

    public TopClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static RestTemplate getRestTemplate() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        MediaType mediaType = MediaType.valueOf("text/javascript;charset=UTF-8");
        supportedMediaTypes.add(mediaType);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        FastJsonConfig config = new FastJsonConfig();
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        //格式化时间
        config.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat);
        converter.setFastJsonConfig(config);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }

    //    public T postExecute(TopParams topParams){
//        restTemplate.postForEntity(topParams.buildUrl(),)
//    }
    public <T> T getExecute(TopParamsBuilder topParams, Class<T> t) {

        String buildUrl = topParams.buildUrl();
        ResponseEntity<T> objectResponseEntity = restTemplate.getForEntity(URI.create(buildUrl), t);
        return objectResponseEntity.getBody();
    }


}
