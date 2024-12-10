package com.example.rrmp.ramp_init;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @RequestMapping(value = { "/", "/prof", "/other-route/**" }) // Add other routes as needed
    public String index() {
        return "forward:/index.html"; // Forward to index.html
    }
}