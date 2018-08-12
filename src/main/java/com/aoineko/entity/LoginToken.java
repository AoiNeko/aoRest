package com.aoineko.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Base64;
import java.util.Date;

/**
 * Created by aoineko on 2018/8/13.
 */
@Data
@Entity(name = "login_token")
public class LoginToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "token")
    private String token;
    @Column(name = "deleted")
    private String deleted;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_update")
    private Date gmtUpdate;

}

