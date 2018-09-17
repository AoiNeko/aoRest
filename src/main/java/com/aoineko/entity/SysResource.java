package com.aoineko.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "sys_resource")
public class SysResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "parent")
    private Long parent;
    @Column(name = "resource")
    private String resource;
    @Column(name = "level")
    private Byte level;
    @Column(name = "deleted")
    private Boolean deleted;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_update")
    private Date gmtUpdate;
}