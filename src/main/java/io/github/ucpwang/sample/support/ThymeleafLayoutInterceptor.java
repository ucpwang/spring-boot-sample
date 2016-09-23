package io.github.ucpwang.sample.support;

import io.github.ucpwang.sample.support.annotation.Layout;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

    private static final String DEFAULT_LAYOUT = "layout/base";
    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    private String defaultLayout = DEFAULT_LAYOUT;
    private String viewAttributeName = DEFAULT_VIEW_ATTRIBUTE_NAME;

    public ThymeleafLayoutInterceptor() {}
    public ThymeleafLayoutInterceptor(String defaultLayout, String viewAttributeName) {
        Assert.hasLength(defaultLayout);
        Assert.hasLength(viewAttributeName);
        this.defaultLayout = defaultLayout;
        this.viewAttributeName = viewAttributeName;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null || !modelAndView.hasView()) return;

        String originalViewName = modelAndView.getViewName();
        if (isRedirectOrForward(originalViewName)) return;

        String layoutName = getLayoutName(handler);
        if (Layout.NONE.equals(layoutName)) return;

        modelAndView.setViewName(layoutName);
        modelAndView.addObject(this.viewAttributeName, originalViewName);
    }

    private boolean isRedirectOrForward(String viewName) {
        return viewName.startsWith("redirect:") || viewName.startsWith("forward:");
    }

    private String getLayoutName(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Layout layout = getMethodOrTypeAnnotation(handlerMethod);
        return layout == null ? this.defaultLayout : layout.value();
    }

    private Layout getMethodOrTypeAnnotation(HandlerMethod handlerMethod) {
        Layout layout = handlerMethod.getMethodAnnotation(Layout.class);
        return layout == null ?
                handlerMethod.getBeanType().getAnnotation(Layout.class) : layout;
    }
}