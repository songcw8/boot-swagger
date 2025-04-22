package org.example.bootswagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "File 업로드 하자고");
        return "index";
    }
}
