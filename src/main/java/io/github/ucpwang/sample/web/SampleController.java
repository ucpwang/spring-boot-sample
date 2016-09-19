package io.github.ucpwang.sample.web;

import io.github.ucpwang.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping
    public String index(Model model) {

        // TODO 메뉴 관리 / 인터셉터 영역으로 옮겨야 함
        HashMap<String, String> menu = new HashMap<>();
        menu.put("id", "sample");
        model.addAttribute("menu", menu);

        model.addAttribute("msg", sampleService.getSampleMsg());
        return "sample/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<String> list() {
        return sampleService.getList();
    }

}
