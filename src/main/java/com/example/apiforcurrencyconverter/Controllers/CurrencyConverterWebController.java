package com.example.apiforcurrencyconverter.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CurrencyConverterWebController {

    @GetMapping("/index")
    public String serverWebPage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/index")
    public String WebPage() {
        return "index";
    }
}
