package io.github.ucpwang.sample.web;

import io.github.ucpwang.sample.support.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class BasicController {

    @Autowired
    private ApplicationProperties applicationProperties;

    @RequestMapping(value = { "/", "/home", "/main" })
    public String index() {

        log.debug("===== 환경 변수 불러와서 찍어보기 =====");
        log.debug("ApplicationProperties.getTestmsg = {}", applicationProperties.getTestmsg());
        log.debug("ApplicationProperties.getAdmins = {}", applicationProperties.getAdmins());

        return "index";
    }

    /**
     * L4/L7 health check
     */
    @RequestMapping("/_hcheck")
    @ResponseBody
    public String hCheck() {
        return "HealthCheck OK";
    }

    /**
     * [샘플] 로그 레벨 체크
     */
    @RequestMapping("/logLevel")
    @ResponseBody
    public String logLevel() {
        log.debug("call '/sample'");
        log.info("call '/sample'");
        log.warn("call '/sample'");
        log.error("call '/sample'");
        return "log level check";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

}