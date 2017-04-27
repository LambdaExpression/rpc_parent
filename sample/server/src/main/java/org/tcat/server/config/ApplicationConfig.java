package org.tcat.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.tcat.parent.rpc.scanner.RemoteServiceConfigurer;

/**
 * Created by Lin on 2017/4/27.
 */
@Configuration
@PropertySource("classpath:/config.properties")
public class ApplicationConfig {

    @Bean
    public RemoteServiceConfigurer apiService() {
        RemoteServiceConfigurer server = new RemoteServiceConfigurer();
        server.setBasePackage("org.tcat.api.**.service");
        return server;
    }

}
