package ua.com.alevel.util;

import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class DateUtil {

    public static Date addMinuteTime(Date date, int minute) {
        return DateUtils.addMinutes(date, minute);
    }
}
