package com.aoineko.entity;

import lombok.Data;

/**
 * Created by com.aoineko on 2018/5/15.
 */
@Data
public class Response {
    private int status;
    private Object result;
    private String desc;

    public Response(Object jwt) {
        status = 200;
        result = jwt;
    }

    public Response(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
