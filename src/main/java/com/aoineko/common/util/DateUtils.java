package com.aoineko.common.util;

/**
 * Created by aoineko on 2018/6/18.
 */
public class DateUtils {

    public static String getTimezone(Integer timeZone) {
        StringBuilder stringBuilder = new StringBuilder("GMT");
        if (timeZone == null || timeZone == 0) {
            return stringBuilder.toString();
        }

        if (timeZone > 0) {
            return stringBuilder.append("+:").append(timeZone > 9 ? timeZone : "0" + timeZone).append(":00").toString();
        }

        if (timeZone < 0) {
            return stringBuilder.append("-:").append(timeZone < -9 ? -1 * timeZone :  "0" + (-1 * timeZone)).append(":00").toString();
        }
        throw new RuntimeException("timeZone error");
    }


    public static String getTimezoneForConvert(Integer timeZone) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (timeZone == null || timeZone == 0) {
            return stringBuilder.toString();
        }

        if (timeZone > 0) {
            return stringBuilder.append("+").append(timeZone).append(":00").toString();
        }

        if (timeZone < 0) {
            return stringBuilder.append(timeZone).append(":00").toString();
        }
        throw new RuntimeException("timeZone error");
    }
}
