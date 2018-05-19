package com.aoineko.controller;

import com.aoineko.entity.Response;


import com.aoineko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by com.aoineko on 2018/5/14.
 */
@RestController("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public Response login(@RequestParam("userName") String name, @RequestParam("passwd") String passwd) {

        if (userService.validate(name, passwd)) {
            String jwt = "";
            return new Response(jwt);
        }


        return new Response(400, "validate error");
    }

}
