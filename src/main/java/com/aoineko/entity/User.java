package com.aoineko.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by com.aoineko on 2018/5/14.
 */

@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name="mobile")
    private String mobile;
    @Column(name="email")
    private String email;
    @Column(name="age")
    private Integer age;
    @Column(name="sex")
    private Boolean sex;
    @Column(name="gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_update")
    private Date gmtUpdate;
}
