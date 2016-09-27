package io.github.ucpwang.sample.config;

import io.github.ucpwang.sample.support.BasicInterceptor;
import io.github.ucpwang.sample.support.ThymeleafLayoutInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error/403").setStatusCode(HttpStatus.FORBIDDEN).setViewName("error/403");
        registry.addViewController("/error/404").setStatusCode(HttpStatus.NOT_FOUND).setViewName("error/404");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BasicInterceptor())
                .addPathPatterns("/**");
        registry.addInterceptor(new ThymeleafLayoutInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/error/**");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // for [ Matrix Variables ]
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

}
