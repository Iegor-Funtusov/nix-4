package ua.com.alevel.persistence.listener;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import ua.com.alevel.persistence.entity.user.PersonalEntity;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class AgeByBirthDayGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateAgeByBirthDay(PersonalEntity personal) {
        if (personal.getBirthDay() == null) {
            personal.setAge(null);
            return;
        }
        Years years = Years.yearsBetween(new LocalDate(personal.getBirthDay()), new LocalDate());
        personal.setAge(years.getYears());
    }
}
