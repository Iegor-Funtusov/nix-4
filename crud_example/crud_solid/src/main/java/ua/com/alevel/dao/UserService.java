package ua.com.alevel.dao;

import ua.com.alevel.entity.User;

import java.util.List;

public interface UserService<U extends User> {

    void create(U u);
    void update(U u);
    void delete(String id);
    U findById(String id);
    U findByLogin(String login);
    List<U> findAll();
}
