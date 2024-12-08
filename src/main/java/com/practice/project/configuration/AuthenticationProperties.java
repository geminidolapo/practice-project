package com.practice.project.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import java.util.List;
import java.util.Locale;


@Data
@ConfigurationProperties(prefix = "app")
@ConfigurationPropertiesScan("com.practice.project")
public class AuthenticationProperties {
    private String secretKey;
    private String name;
    private String description;
    private String version;
    private String licensesName;
    private String licensesUrl;
    private String githubUrl;
    private String pathPrefix;
    private String[] permitAllPaths;
    private int pageableSize;
    private long jwtExpiresAt;
    private String jwtIssuer;
    private Locale defaultLocale;
    private List<Locale> supportedLocales;
}
