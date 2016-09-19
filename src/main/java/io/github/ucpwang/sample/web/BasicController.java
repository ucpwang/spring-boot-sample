package io.github.ucpwang.sample.web;

import io.github.ucpwang.sample.support.ApplicationProperties;
import io.github.ucpwang.sample.support.exception.CustomSampleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@Controller
public class BasicController {

    @Autowired
    private ApplicationProperties applicationProperties;

    @RequestMapping(value = { "/", "/home", "/main" })
    public String index(Model model) {

        // TODO 메뉴 관리 / 인터셉터 영역으로 옮겨야 함
        HashMap<String, String> menu = new HashMap<>();
        menu.put("id", "home");
        model.addAttribute("menu", menu);

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    /*****************************************************************
     * 이하 샘플 코드 맵핑
     *****************************************************************/

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

    /**
     * [샘플] [ Matrix Variables ]
     */
// 메트릭스 베리어블 관련 내용 주석
//    @GetMapping("/mv/{pathVariable}")
//    @ResponseBody
//    public ResponseEntity<String> mv(@PathVariable String pathVariable, @MatrixVariable int matrixVariable) {
//        log.debug("pathVariable = {}", pathVariable);
//        log.debug("matrixVariable = {}", matrixVariable);
//        return new ResponseEntity<>("[샘플] [ Matrix Variables ]", HttpStatus.OK);
//    }

    @GetMapping("/sampleDefError")
    @ResponseBody
    public String sampleDefError() throws Exception {
        if (true) {
            throw new Exception("샘플로 에러(기본 Exception 에러)를 발생시킵니다.");
        }
        return "N/A";
    }

    @GetMapping("/sampleError")
    @ResponseBody
    public String sampleError() throws CustomSampleException {
        if (true) {
            throw new CustomSampleException("샘플로 에러(비즈니스 로직 관련 에러)를 발생시킵니다.");
        }
        return "N/A";
    }


}