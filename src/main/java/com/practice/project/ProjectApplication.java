package com.practice.project;

import com.practice.project.configuration.AuthenticationProperties;
import com.practice.project.configuration.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableRetry
//@EnableKafka
@EnableAsync
@EnableCaching
@EnableConfigurationProperties({ConfigProperties.class, AuthenticationProperties.class})
@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
}