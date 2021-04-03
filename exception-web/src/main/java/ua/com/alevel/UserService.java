package ua.com.alevel;

import java.util.List;

public interface UserService {

    void create(UserDto user);
    void update(Integer id, UserDto user);
    void delete(Integer id);
    User findById(Integer integer);
    List<User> findAll();
}
