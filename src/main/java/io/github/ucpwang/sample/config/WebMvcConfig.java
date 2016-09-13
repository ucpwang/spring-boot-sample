package io.github.ucpwang.sample.config;

//@Configuration
//public class WebMvcConfig extends WebMvcConfigurationSupport {
//
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        // for [ Matrix Variables ]
//        UrlPathHelper urlPathHelper = new UrlPathHelper();
//        urlPathHelper.setRemoveSemicolonContent(false);
//        configurer.setUrlPathHelper(urlPathHelper);
//    }
//
//}
// TODO WebMvcConfigurationSupport.configurePathMatch 오버라이드 하면서 @EnableWebMvc 기본설정들이 먹통이 되는듯하다.. 이때문에 public 폴더 내부 file들에 대한 404가 발생하였다. 원인/해결책을 찾기 전까지 메트릭스 베리어블 관련 로직은 거둬놓아야 할듯...