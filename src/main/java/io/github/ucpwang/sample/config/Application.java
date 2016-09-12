package io.github.ucpwang.sample.config;

import io.github.ucpwang.sample.support.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 일단 주석 @SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan("io.github.ucpwang.sample")
@EnableConfigurationProperties(ApplicationProperties.class) // 환경변수 사용을 위한 빈등록
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
