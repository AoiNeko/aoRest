package com.aoineko.exception;

import com.aoineko.constant.ExceptionConstants;

/**
 * Created by aoineko on 2018/8/11.
 */
public class UserAuthException extends ServerException {
    public UserAuthException() {
        super(ExceptionConstants.Error.USER_AUTH.getStatus(), ExceptionConstants.Error.USER_AUTH.getDesc());
    }
}
