package ua.com.alevel;

import java.io.IOException;

public class UserMain {

    public static void main(String[] args) throws IOException {
        UserController userController = new UserController();
        userController.run();
    }
}
