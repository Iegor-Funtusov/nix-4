package ua.com.alevel.service;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.dao.gen.AbsGenDao;
import ua.com.alevel.di.Inject;
import ua.com.alevel.di.Service;
import ua.com.alevel.dto.UserDto;
import ua.com.alevel.entity.User;

import java.io.Serializable;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, Serializable {

    @Inject
    private AbsGenDao<User, Integer> dao;

    @Inject
    private UserDao userDao;

    @Override
    public void create(UserDto dto) {

    }

    @Override
    public void update(UserDto dto) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<UserDto> find() {
        return null;
    }

    //    @Override
    public List<User> findA() {
        return dao.find(userDao);
    }

    @Override
    public UserDto find(Integer integer) {
        return null;
    }

    @Override
    public <T> void test(T t) {

    }

    @Override
    public <T, E> void test(T t, E e) {

    }

    @Override
    public <U extends User> void test2(U u) {

    }
}
