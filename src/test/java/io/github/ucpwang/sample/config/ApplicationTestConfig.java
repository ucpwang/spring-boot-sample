package io.github.ucpwang.sample.config;

import io.github.ucpwang.sample.support.ApplicationProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan("io.github.ucpwang.sample")
@EnableConfigurationProperties(ApplicationProperties.class) // 환경변수 사용을 위한 빈등록
public class ApplicationTestConfig {
}
