package com.aoineko.dto;

import com.aoineko.entity.Post;
import com.aoineko.entity.PostContent;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class PostDTO {
    private Long id;

    private String title;

    @JsonProperty(value = "abstract")
    private String abstractText;

    private String tag;

    private String content;

    private Long createTimestamp;

    public PostDTO(Post post) {
        this.setAbstractText(post.getAbstractText());
        this.setId(post.getId());
        this.setTag(post.getTag());
        this.setTitle(post.getTitle());
        this.setCreateTimestamp(post.getGmtCreate().getTime());
    }

    public PostDTO(PostContent postContent) {
        this.setId(postContent.getPostId());
        this.setContent(postContent.getContent());
    }
}

