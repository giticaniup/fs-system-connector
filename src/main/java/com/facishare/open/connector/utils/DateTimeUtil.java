package com.facishare.open.connector.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间转换工具
 * Created by zhongcy on 2016/6/7.
 */
public class DateTimeUtil {

    public static long parseDateToTimeStamp(Date date) {
        if (date != null) {
            Instant instant = date.toInstant();
            return instant.toEpochMilli();
        }
        return 0;
    }

    public static long parseDatetoTimeStamp(String date) {
        if (date != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse("2016-6-07 15:11", dtf);
            Instant instant = dateTime.toInstant(ZoneOffset.UTC);
            return instant.toEpochMilli();
        }
        return 0;
    }

    public static String parseTimeStampToDate(Long timeStamp) {
        if (timeStamp != null) {
            Instant specificTime = Instant.ofEpochMilli(timeStamp);
            LocalDateTime dateTime = LocalDateTime.ofInstant(specificTime, ZoneId.of("GMT+8"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return dateTime.format(formatter);
        }
        return null;
    }
}
