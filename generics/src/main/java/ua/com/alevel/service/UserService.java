package ua.com.alevel.service;

import ua.com.alevel.dto.UserDto;
import ua.com.alevel.entity.User;

public interface UserService extends AbsService<User, UserDto, Integer> {

    <T> void test(T t);
    <T, E> void test(T t, E e);
    <U extends User> void test2(U u);
}
