package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserTest {

    public void test() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            User user = new User();
            Random random = new Random();
            int age = random.nextInt(78) + 1;
            user.setAge(age);
            user.setId(UUID.randomUUID().toString());
            users.add(user);
        }

        System.out.println("users = " + users);

        List<UserDto> userDtos = users
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());

        System.out.println("userDtos = " + userDtos);


    }
}
