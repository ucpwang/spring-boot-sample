package io.github.ucpwang.sample.web;

import io.github.ucpwang.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("msg", sampleService.getSampleMsg());
        return "sample/index";
    }

    @RequestMapping(value = "/sample/list", produces = {"application/json; charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public String[] list() {
        return new String[] { "item1", "item2", "item3" };
    }

}
