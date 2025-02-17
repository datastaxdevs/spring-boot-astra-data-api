package com.datastax.workshop.conf;

import com.datastax.astra.client.DataAPIClient;
import com.datastax.astra.client.databases.Database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AstraDbDataApiConfig {

    @Value("${datastax.astra.token}")
    private String token;

    @Value("${datastax.astra.keyspace}")
    private String keyspace;

    @Value("${datastax.astra.endpoint}")
    private String endpoint;

    @Bean
    public DataAPIClient createDataApiClient() {
        return new DataAPIClient(token);
    }

    @Bean
    public Database createDatabase(DataAPIClient client) {
        return new DataAPIClient(token).getDatabase(endpoint, keyspace);
    }
    
}
