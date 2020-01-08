package com.github.liuchenrang.taobao.top.client.utils;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * @author zhangyalong
 * @version 2019-03-24 19:19
 */
public class HttpClientUtils {

    public static RestTemplate getInstance(Proxy proxy, int connTimeout, int readTimeout, boolean enableSslCheck) {
        final RestTemplate restTemplate = new RestTemplate();

        // sslIgnore
        SimpleClientHttpRequestFactory requestFactory;
        if (!enableSslCheck) {
            requestFactory = getUnsafeClientHttpRequestFactory();
        } else {
            requestFactory = new SimpleClientHttpRequestFactory();
        }

        // proxy
        if (proxy != null) {
            requestFactory.setProxy(proxy);
        }

        // timeout
        requestFactory.setConnectTimeout(connTimeout);
        requestFactory.setReadTimeout(readTimeout);

        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

    private static SimpleClientHttpRequestFactory getUnsafeClientHttpRequestFactory() {
        TrustManager[] byPassTrustManagers = new TrustManager[]{new X509TrustManager() {

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }};
        final SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, byPassTrustManagers, new SecureRandom());
            sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }

        return new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                super.prepareConnection(connection, httpMethod);
                if (connection instanceof HttpsURLConnection) {
                    ((HttpsURLConnection) connection).setSSLSocketFactory(
                            sslContext.getSocketFactory());
                }
            }
        };
    }

    public static RestTemplate getInstance(Proxy proxy, boolean enableSsLCheck) {
        return getInstance(proxy, -1, -1, enableSsLCheck);
    }

}