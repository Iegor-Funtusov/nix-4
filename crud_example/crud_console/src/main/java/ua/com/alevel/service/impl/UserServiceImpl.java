package ua.com.alevel.service.impl;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.dao.impl.UserDaoImpl;
import ua.com.alevel.data.Profile;
import ua.com.alevel.data.User;
import ua.com.alevel.service.ProfileService;
import ua.com.alevel.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();
    private final ProfileService profileService = new ProfileServiceImpl();

    @Override
    public void create(User data) {
        userDao.create(data);
        data = userDao.readUserByEmail(data.getEmail());
        Profile profile = new Profile();
        profile.setUserId(data.getId());
        profileService.create(profile);
    }

    @Override
    public User read(int id) {
        existUser(id);
        return userDao.read(id);
    }

    @Override
    public List<User> read() {
        return userDao.read();
    }

    @Override
    public void update(User data) {
        existUser(data.getId());
        userDao.update(data);
    }

    @Override
    public void delete(int id) {
        existUser(id);
        userDao.delete(id);
    }

    private void existUser(int id) {
        User user = userDao.read(id);
        if (user == null) {
            throw new RuntimeException("404");
        }
    }
}
