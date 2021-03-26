package ua.com.alevel;

import org.apache.commons.collections.CollectionUtils;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import ua.com.alevel.dao.UserService;
import ua.com.alevel.dao.impl.PersonalService;
import ua.com.alevel.entity.Personal;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommonServiceTest {

    private static final UserService<Personal> userService = new PersonalService();
    private static final String str = "ZZZXXX";

    @BeforeAll
    public static void init() {
        for (int i = 0; i < 10; i++) {
            String str = "test" + i;
            Personal personal = new Personal();
            personal.setFirstName(str);
            personal.setLastName(str);
            personal.setLogin(str);
            personal.setPassword(str);
            userService.create(personal);
        }
        Assert.assertTrue(CollectionUtils.isNotEmpty(userService.findAll()));
    }

    @Test
    @Order(1)
    public void createUser() {

        List<Personal> personals = userService.findAll();
        int previous = personals.size();

        Personal personal = new Personal();
        personal.setFirstName(str);
        personal.setLastName(str);
        personal.setLogin(str);
        personal.setPassword(str);
        userService.create(personal);

        personal = userService.findByLogin(str);

        Assert.assertTrue(personal != null);

        personals = userService.findAll();
        int last = personals.size();

        Assert.assertEquals(previous + 1, last);
    }

    @Test
    @Order(2)
    public void updateUser() {
        Personal personal = userService.findByLogin(str);
        personal.setLastName(str + "1");
        userService.update(personal);

        personal = userService.findByLogin(str);

        Assert.assertEquals(str + "1", personal.getLastName());
    }
}
