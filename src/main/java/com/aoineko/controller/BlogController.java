package com.aoineko.controller;

import com.aoineko.entity.Post;
import com.aoineko.entity.Response;
import com.aoineko.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aoineko on 2018/5/23.
 */
@RestController
@RequestMapping("post")
public class BlogController {

    @Autowired
    private PostService postService;

    public Response listPost() {

        return null;
    }

    @RequestMapping("/get")
    public Response getPost(@RequestParam("id") String id) {
        Post post = postService.getPost(Long.valueOf(id));
        return  new Response(post);
    }



    public Response savePost() {

        return null;
    }

    

}
