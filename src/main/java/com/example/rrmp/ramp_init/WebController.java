package com.example.rrmp.ramp_init;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @RequestMapping(value = {"/", "/prof", "/other-routes"})
    public String forwardToReact() {
        return "forward:/index.html";
    }
}