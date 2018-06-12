package com.aoineko.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "post_content")
public class PostContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "content")
    private String content;
    @Column(name="gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_update")
    private Date gmtUpdate;
}
