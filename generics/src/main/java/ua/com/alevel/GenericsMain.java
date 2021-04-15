package ua.com.alevel;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.dao.impl.UserDaoImpl;
import ua.com.alevel.entity.AbsType;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.service.UserServiceImpl;

import java.util.List;

public class GenericsMain {

    public static void main(String[] args) {
        System.out.println("GenericsMain.main");

        UserDao userDao = new UserDaoImpl();

        List<? extends AbsType> users = userDao.find();

        UserService userService = new UserServiceImpl();
        userService.find(1);
        userService.test2(new User());
    }
}
