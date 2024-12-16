package com.example.rrmp.ramp_init;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Web controller for handling HTTP requests in the RAMP application.
 * This controller is responsible for routing requests to the React frontend.
 */
@Controller
public class WebController {

    /**
     * Handles requests to the root path, "/prof", and other specified routes.
     * This method forwards these requests to the React frontend's index.html.
     *
     * @return A string indicating to forward the request to "/index.html"
     */
    @RequestMapping(value = {"/", "/prof", "/other-routes"})
    public String forwardToReact() {
        return "forward:/index.html";
    }
}