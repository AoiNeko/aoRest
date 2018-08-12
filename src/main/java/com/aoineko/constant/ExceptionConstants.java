package com.aoineko.constant;

/**
 * Created by aoineko on 2018/8/11.
 */
public class ExceptionConstants {
    public enum Error {
        SERVER_ERROR(500, "unexpected error"),USER_AUTH(401, "not authorized");
        Integer status;
        String desc;


        Error(int i, String s) {
            this.status = i;
            this.desc = s;
        }

        public Integer getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }
    }
}
