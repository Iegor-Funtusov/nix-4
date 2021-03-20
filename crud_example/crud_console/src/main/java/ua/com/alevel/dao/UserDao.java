package ua.com.alevel.dao;

import ua.com.alevel.data.User;

public interface UserDao extends AbstractDao<User> {

    User readUserByEmail(String email);
}
