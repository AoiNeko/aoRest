package com.aoineko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aoineko on 2018/9/15.
 */

@Controller
public class MainController {

    @RequestMapping("login")
    public String login() {
        return "forward:index";
    }


    @RequestMapping("login-error")
    public String loginError() {
        return "forward:index";
    }

    @RequestMapping("/")
    public String start() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
