package com.aoineko.controller;

import com.aoineko.dto.PostDTO;
import com.aoineko.entity.Response;
import com.aoineko.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        PostDTO postDTO = postService.getPostContent(Long.valueOf(id));
        return  new Response(postDTO);
    }

    @RequestMapping("/day/list")
    public Response dayList(@RequestParam("t") Long dayTimestamp) {
        List<PostDTO> postDTOS = postService.getDayList(dayTimestamp);
        return new Response(postDTOS);

    }


    public Response savePost() {

        return null;
    }

    

}
