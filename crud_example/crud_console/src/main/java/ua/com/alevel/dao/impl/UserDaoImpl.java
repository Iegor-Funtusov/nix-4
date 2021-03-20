package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.data.User;
import ua.com.alevel.db.TestDB;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void create(User data) {
        TestDB.getInstance().createUser(data);
    }

    @Override
    public User read(int id) {
        return TestDB.getInstance().readUser(id);
    }

    @Override
    public List<User> read() {
        return TestDB.getInstance().readAllUsers();
    }

    @Override
    public void update(User data) {
        TestDB.getInstance().updateUser(data);
    }

    @Override
    public void delete(int id) {
        TestDB.getInstance().deleteUser(id);
    }

    @Override
    public User readUserByEmail(String email) {
        return TestDB.getInstance().readUserByEmail(email);
    }
}
