package ua.com.alevel;

import java.util.Date;
import java.util.Optional;

public class OptionalTest {

    private Optional<Date> date;

    public Optional<Date> getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = Optional.ofNullable(date);
    }
}
