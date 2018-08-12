package com.aoineko.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aoineko on 2018/6/11.
 */

@Data
@Entity(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "abstract_text")
    private String abstractText;
    @Column(name = "tag")
    private String tag;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_update")
    private Date gmtUpdate;
}
