package com.jasper.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class fallbackController {

    @RequestMapping("/fl")
    public String fl(){
        return "this is fallback .";
    }
}
