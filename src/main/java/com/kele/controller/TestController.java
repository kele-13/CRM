package com.kele.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 12402
 */
@Controller
public class TestController {

    @RequestMapping("/t")
    public String test(Model model) {
        model.addAttribute("msg", "success!");
        return "test";
    }
}
