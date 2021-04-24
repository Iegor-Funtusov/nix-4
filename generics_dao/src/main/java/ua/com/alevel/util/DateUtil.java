package ua.com.alevel.util;

import lombok.experimental.UtilityClass;
import ua.com.alevel.controller.DateRangeModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@UtilityClass
public class DateUtil {

    public long getMinutesBetween(Date start, Date end) {
        return ChronoUnit.MINUTES.between(start.toInstant(), end.toInstant());
    }

    public int getHoursBetween(Date start, Date end) {
        return (int) ChronoUnit.HOURS.between(end.toInstant(), start.toInstant());
    }

    public DateRangeModel generateDateRangeByLastHour() {
        Date end = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(end);
        cal.add(Calendar.HOUR, -1);
        Date start = cal.getTime();
        return new DateRangeModel(start, end);
    }

    public Date addHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        return cal.getTime();
    }

    public Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    public Date generateBeginningOfDay(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    private Date generateEndOfDay(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public Date convertClientTimeToServerTime(String timeZone, Date date, boolean beginning) {

        Calendar serverCalendar = Calendar.getInstance();
        ZoneId serverTimeZone = ZoneId.of(serverCalendar.getTimeZone().getID());
        ZoneId clientTimeZone = ZoneId.of(timeZone);

        date = (date != null) ? date : new Date();

        LocalDateTime serverLocalDateTime = Instant.ofEpochMilli(date.getTime()).atZone(serverTimeZone).toLocalDateTime();

        ZonedDateTime serverTime = serverLocalDateTime.atZone(serverTimeZone);
        ZonedDateTime clientTime = serverTime.withZoneSameInstant(clientTimeZone);

        Instant instant = clientTime.toInstant();
        Instant instantTruncatedToMilliseconds = Instant.ofEpochMilli(instant.toEpochMilli());

        Date targetDate = Date.from(instantTruncatedToMilliseconds);

        if (beginning) {
            return generateBeginningOfDay(targetDate);
        } else {
            return generateEndOfDay(targetDate);
        }
    }
}
