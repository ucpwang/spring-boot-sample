package io.github.ucpwang.sample.config;

import io.github.ucpwang.sample.support.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final String[] noAuthRequestMappings = new String[] {
                // ROOT
                "/", "/main", "/home",

                // ETC
                "/_hcheck", "/logLevel"
        };

        http.authorizeRequests()
                .antMatchers(noAuthRequestMappings).permitAll().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("user.id").passwordParameter("user.password").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();

    }

    @Resource
    private ApplicationProperties applicationProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        log.debug("applicationProperties.getAdmins() = {}", applicationProperties.getAdmins());

        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryUserDetailsManagerConfigurer = auth.inMemoryAuthentication();
        applicationProperties.getAdmins().forEach(user ->
                inMemoryUserDetailsManagerConfigurer.withUser((String) user.get("id")).password((String) user.get("pw")).roles("USER").and()
        );

    }
}
