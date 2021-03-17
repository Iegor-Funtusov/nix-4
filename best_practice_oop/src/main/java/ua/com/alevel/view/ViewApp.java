package ua.com.alevel.view;

import ua.com.alevel.controller.ConsoleController;

import java.math.BigInteger;

public class ViewApp {

    private static final ConsoleController CONSOLE_CONTROLLER = new ConsoleController();

    public static void view() throws Exception {
        CONSOLE_CONTROLLER.read();
    }
}
