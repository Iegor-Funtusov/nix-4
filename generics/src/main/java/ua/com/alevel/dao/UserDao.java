package ua.com.alevel.dao;

import ua.com.alevel.entity.User;

public interface UserDao extends AbsDao<User, Integer> {

    User findByEmail(String email);
}
