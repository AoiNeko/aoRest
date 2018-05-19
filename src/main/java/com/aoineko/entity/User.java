package com.aoineko.entity;

import lombok.Data;

/**
 * Created by com.aoineko on 2018/5/14.
 */

@Data
public class User {
    private Long userId;
    private String userName;
    private String password;
    private String mobile;
    private String email;
}
