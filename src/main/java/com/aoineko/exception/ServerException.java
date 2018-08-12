package com.aoineko.exception;

import lombok.Data;

/**
 * Created by aoineko on 2018/8/11.
 */
@Data
public abstract class ServerException extends Throwable {
    Integer code;
    String desc;

    public ServerException(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
