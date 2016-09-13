package io.github.ucpwang.sample.web;

import io.github.ucpwang.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("msg", sampleService.getSampleMsg());
        return "sample/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<String> list() {
        return sampleService.getList();
    }

}
