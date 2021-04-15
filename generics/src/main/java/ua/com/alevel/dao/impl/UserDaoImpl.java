package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<User> find() {
        return Collections.emptyList();
    }

    @Override
    public User find(Integer integer) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
