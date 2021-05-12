package ua.com.alevel.db_all_circle.dao;

import ua.com.alevel.db_all_circle.entity.User;

import java.util.List;

public interface UserDao {

    void create(User user);
    void update(User user);
    void delete(Integer id);
    User find(Integer id);
    List<User> find();
}
