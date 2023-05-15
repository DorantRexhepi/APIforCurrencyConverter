package com.example.apiforcurrencyconverter.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrencyConverterWebController {

    @GetMapping("/")
    public String serverWebPage() {
        return "index";
    }
}
