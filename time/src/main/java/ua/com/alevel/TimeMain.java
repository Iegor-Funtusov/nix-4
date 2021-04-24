package ua.com.alevel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeMain {

    public static void main(String[] args) throws Exception {
        time();
    }

    private static void time() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("localTime = " + localTime);
        System.out.println("localDate = " + localDate);
        System.out.println("localDateTime = " + localDateTime);

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("offsetDateTime = " + offsetDateTime);

        // client = +1, 23.00.00 24 april
        // server = +3, 01.00.00 25 april
    }

    private static void zoneInfo() {
        // zdump -v /usr/share/zoneinfo/Europe/Zaporozhye | grep 2020
        // cat /etc/timezone
        // dateutils.ddiff --from-zone "right/UTC" -f '%S' "1970-01-01 00:00:00" "2021-04-27 12:52:00"
        // dateutils.ddiff --from-zone "right/UTC" -f '%rS' "1970-01-01 00:00:00" "2021-04-27 12:52:00"
        TimeZone tz = TimeZone.getTimeZone("Europe/Zaporozhye");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setLenient(false);
        dateFormat.setTimeZone(tz);

        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(2020, Calendar.MARCH, 29, 2, 59, 59);

        for (int i = 0; i < 62; i++) {
            String mark = dateFormat.format(calendar.getTime());
            System.out.printf("%s - %d, %s\n", mark,
                    tz.getOffset(calendar.getTimeInMillis()),
                    tz.inDaylightTime(calendar.getTime()));
            calendar.add(Calendar.MINUTE, +1);
        }
    }

    private static void basic() {
        System.out.println(TimeZone.getDefault());
        System.out.println(TimeZone.getTimeZone("Europe/Kharkiv"));
        Date date = new Date(Long.MAX_VALUE);
        System.out.println("date = " + date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date1 = dateFormat.parse("2021-04-24 12:09:00");
        System.out.println("date1 = " + dateFormat.format(new Date()));

//        write(date);

        Calendar calStart = Calendar.getInstance();
        calStart.set(2021, 1, 28);
        System.out.println("calStart = " + calStart.getTime());
        calStart.add(Calendar.MONTH, 1);
        System.out.println("calStart = " + calStart.getTime());

        calStart = Calendar.getInstance();
        calStart.set(2021, 2, 31);
        System.out.println("calStart = " + calStart.getTime());
        calStart.add(Calendar.MONTH, 1);
        System.out.println("calStart = " + calStart.getTime());

//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.MONTH, -1);
//        Date date = calendar.getTime();
//        System.out.println("date = " + date);
    }

    private static void write(Date date) throws IOException {
        //problem
        String str = date.toString();
        BufferedWriter writer = new BufferedWriter(new FileWriter("time.txt", true));
        writer.write(str);
        writer.write('\n');

        writer.close();
    }
}
