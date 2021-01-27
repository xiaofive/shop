package com.jyzt.es.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/***
 *功能描述: Elasticsearch Client
 * @author wangyang
 */
@Slf4j
@Component
public class EsClientFactory {

    private static String userName;
    private static String password;
    private static String hostName;
    private static Integer port;

    @Value(value = "${es.username}")
    public void setUserName(String userName) {
        EsClientFactory.userName = userName;
    }

    @Value(value = "${es.password}")
    public void setPassword(String password) {
        EsClientFactory.password = password;
    }

    @Value(value = "${es.hostName}")
    public void setHostName(String hostName) {
        EsClientFactory.hostName = hostName;
    }

    @Value(value = "${es.port}")
    public void setPort(Integer port) {
        EsClientFactory.port = port;
    }


    public volatile static RestHighLevelClient client;

    private EsClientFactory() {
    }

    public static RestHighLevelClient getEsClient() {
        if (client == null) {
            synchronized (EsClientFactory.class) {
                if (client == null) {
                    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    credentialsProvider.setCredentials(AuthScope.ANY,
                            new UsernamePasswordCredentials(userName, password));

                    RestClientBuilder builder = RestClient.builder(new HttpHost(hostName, port))
                            .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                                @Override
                                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                                }
                            });
                    client = new RestHighLevelClient(builder);
                }
            }
        }
        return client;
    }

    public static void close() throws IOException {
        client.close();
    }

}