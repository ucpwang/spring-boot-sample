package io.github.ucpwang.sample.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class BasicInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{

        log.debug("========= preHandle Interceptor [{}] Start ========", req.getRequestURI());

        req.setCharacterEncoding("UTF-8");
        res.setHeader("cache-control","no-cache");
        res.setHeader("cache-control","no-store");
        res.setHeader("expires","-1");
        res.setHeader("pragma","no-cache");

        log.debug("========= preHandle Interceptor [{}] End ========", req.getRequestURI());

        return super.preHandle(req, res, handler);
    }

    @Override
    public void postHandle(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Object obj, ModelAndView modelandview)throws Exception{
        log.info("========= postHandle Interceptor ========");
    }


    @Override
    public void afterCompletion(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Object obj, Exception exception)throws Exception{
        log.info("========= afterCompletion Interceptor ========");
    }

}