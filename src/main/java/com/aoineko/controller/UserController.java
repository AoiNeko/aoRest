package com.aoineko.controller;

import com.aoineko.entity.Response;


import com.aoineko.entity.User;
import com.aoineko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by com.aoineko on 2018/5/14.
 */
@RestController("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public Response login(HttpServletRequest request, HttpServletResponse response, @RequestParam("userName") String name, @RequestParam("passwd") String passwd) {
        User user = userService.validate(name, passwd);
        if (user != null) {
            String jwt = userService.genJWT(user);
            response.addHeader("t", jwt);
            return new Response(user);
        }
        return new Response(400, "validate error");
    }

}
