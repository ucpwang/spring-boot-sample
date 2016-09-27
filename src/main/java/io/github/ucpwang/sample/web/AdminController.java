package io.github.ucpwang.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String index(Model model) {

        // TODO 메뉴 관리 / 인터셉터 영역으로 옮겨야 함
        HashMap<String, String> menu = new HashMap<>();
        menu.put("id", "admin");
        model.addAttribute("menu", menu);

        return "admin/index";
    }

}
