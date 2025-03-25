package com.nguyenthanh.notification_service.configurations.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.InfoResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {
    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Value("${elasticsearch.username}")
    private String username;

    @Value("${elasticsearch.password}")
    private String password;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClientBuilder builder = RestClient.builder(org.apache.http.HttpHost.create(elasticsearchHost))
                .setRequestConfigCallback(requestConfigBuilder ->
                        requestConfigBuilder.setConnectTimeout(5000)
                                .setSocketTimeout(30000)
                );

        if (!username.isEmpty() && !password.isEmpty()) {
            builder.setHttpClientConfigCallback(httpClientBuilder ->
                    httpClientBuilder.setDefaultCredentialsProvider(
                            new org.apache.http.impl.client.BasicCredentialsProvider() {{
                                setCredentials(
                                        org.apache.http.auth.AuthScope.ANY,
                                        new org.apache.http.auth.UsernamePasswordCredentials(username, password)
                                );
                            }}
                    )
            );
        }

        RestClient restClient = builder.build();
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        ElasticsearchClient client = new ElasticsearchClient(transport);
        checkElasticsearchConnection(client);

        return new ElasticsearchClient(transport);
    }

    private void checkElasticsearchConnection(ElasticsearchClient client) {
        try {
            InfoResponse response = client.info();
            System.out.println("Connected to Elasticsearch cluster: " + response.clusterName());
        } catch (Exception e) {
            System.err.println("Failed to connect to Elasticsearch: " + e.getMessage());
        }
    }
}
