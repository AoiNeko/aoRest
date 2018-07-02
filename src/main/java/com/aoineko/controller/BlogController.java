package com.aoineko.controller;

import com.aoineko.common.util.DateUtils;
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

    /**
     * 取某日的时间戳对应的内容
     * @param dayTimestamp 时间戳
     * @param timeZone 时区(signed int) [-12~12]
     * @return
     */
    @RequestMapping("/day/list")
    public Response dayList(@RequestParam("t") Long dayTimestamp, @RequestParam("tz") Integer timeZone) {
        List<PostDTO> postDTOS = postService.getDayList(dayTimestamp,timeZone);
        return new Response(postDTOS);
    }

    /**
     * 取某月的时间戳对应的内容
     * @param dayTimestamp 时间戳
     * @param timeZone 时区(signed int) [-12~12]
     * @return
     */
    @RequestMapping("/month/list")
    public Response monthList(@RequestParam("t") Long dayTimestamp, @RequestParam("tz") Integer timeZone) {
        List<PostDTO> postDTOS = postService.getMonthList(dayTimestamp,timeZone);
        return new Response(postDTOS);
    }

    public Response savePost() {

        return null;
    }

    

}
