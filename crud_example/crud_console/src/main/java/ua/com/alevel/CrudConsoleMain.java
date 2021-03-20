package ua.com.alevel;

import ua.com.alevel.controller.UserController;

public class CrudConsoleMain {

    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.run();
    }
}
