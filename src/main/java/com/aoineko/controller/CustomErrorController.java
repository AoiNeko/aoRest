package com.aoineko.controller;

import com.aoineko.annotation.Login;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aoineko on 2018/9/12.
 */

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value="error")
    @Login(false)
    public String handleError(){
        return "errorHtml.html";
    }


    @Override
    public String getErrorPath() {
        return "error";
    }
}
