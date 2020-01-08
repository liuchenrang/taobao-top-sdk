# 淘宝top淘宝客sdk基础版本 
#mvn deploy -DaltDeploymentRepository=liuchenrang-mvn-repo::default::file:/Users/chen/.chen-maven-repo/repository/

# 添加仓库

```$xslt
 <repositories>
        <repository>
            <id>liuchenrang-mvn-repo</id>
            <url>https://raw.githubusercontent.com/liuchenrang/mvnrepo/master/repository</url>
        </repository>
    </repositories> 

    <dependency>
            <groupId>com.github.liuchenrang.taobao</groupId>
            <artifactId>top-sdk</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```
   
```java

package com.github.liuchenrang.taobao.top.client.config;

import com.fangkuaiyun.haoalbum.config.dto.TopProperty;
import com.fangkuaiyun.taobao.client.TopClient;
import com.fangkuaiyun.taobao.client.TopParamsBuilder;
import com.fangkuaiyun.taobao.tkl.ITklEncoder;
import com.fangkuaiyun.taobao.tkl.Impl.TklEncoderImpl;
import com.fangkuaiyun.utils.HttpClientUtils;
import com.github.liuchenrang.taobao.top.client.TopClient;
import com.github.liuchenrang.taobao.top.client.TopParamsBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author chen
 */
@Configuration
public class TopConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    TopClient topClient() {
        return new TopClient(TopClient.getRestTemplate());
    }

    @Bean("prototype")
    TopParamsBuilder.TopParamsBuilderBuilder topParamsBuilder(TopProperty topProperty) {
        return TopParamsBuilder.builder()
                .url(topProperty.getRestUrl())
                .appSecret(topProperty.getAppSecret())
                .appKey(topProperty.getAppKey());
    }

    @Bean
    @ConfigurationProperties("spring.top-client")
    TopProperty topProperty() {
        return new TopProperty();
    }
}

```