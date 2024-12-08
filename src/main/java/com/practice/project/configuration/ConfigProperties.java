package com.practice.project.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import java.util.List;
import java.util.Map;

@Data
//@Configuration
@ConfigurationProperties(prefix = "mail")
@ConfigurationPropertiesScan("com.practice.project") // do this and do not add @Configuration or @EnableConfigurationProperties(ConfigProperties.class)
public class ConfigProperties {

    private String hostname;
    private int port;
    private String from;
    private List<String> defaultRecipients;
    private Map<String, String> additionalHeaders;
    private Credentials credentials;

    // standard getters and setters

    @Data
    static class Credentials {
        private String username;
        private String password;
        private String authMethod;
    }
}
