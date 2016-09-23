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

                // public
                "/webjars/**", "/css/**", "/images/**", "/js/**", "/robots.txt",

                // error
                "/error/**",

                // ETC
                "/_hcheck", "/logLevel", "/sampleError"
        };

        http.authorizeRequests()

                .antMatchers(noAuthRequestMappings).permitAll()

                .antMatchers("/login").anonymous()

                .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("user.id")
                    .passwordParameter("user.password")

                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");

        //http.exceptionHandling().accessDeniedPage("/403.html");
        http.exceptionHandling().accessDeniedPage("/");
        http.sessionManagement().invalidSessionUrl("/");
    }

    @Resource
    private ApplicationProperties applicationProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        log.debug("applicationProperties.getAdmins() = {}", applicationProperties.getAdmins());

        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryUserDetailsManagerConfigurer = auth.inMemoryAuthentication();
        applicationProperties.getAdmins().forEach(user ->
                inMemoryUserDetailsManagerConfigurer.withUser((String) user.get("id")).password((String) user.get("pw")).roles("ADMIN", "USER").and()
        );

        inMemoryUserDetailsManagerConfigurer.withUser("guest").password("guest").roles("USER");

    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**", "/images/**", "/js/**", "/robots.txt");
//    }

}
