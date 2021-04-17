package ua.com.alevel;

import java.util.List;

public class UserService {

    private final UserDao userDao = new UserDao();

    public void create(User data) {
        userDao.create(data);
    }

    public User read(int id) {
        if (existUser(id)) {
            return userDao.read(id);
        }
        return null;
    }

    public List<User> read() {
        return DB.getInstance().read();
    }

    public void update(User data) {
        if (existUser(data.getId())) {
            userDao.update(data);
        }
    }

    public void delete(int id) {
        if (existUser(id)) {
            userDao.delete(id);
        }
    }

    private boolean existUser(int id) {
        return userDao.read(id) != null;
    }
}
