package ua.com.alevel;

import java.util.List;

public class UserDao {

    public void create(User data) {
        DB.getInstance().createUser(data);
    }


    public User read(int id) {
        return DB.getInstance().readUser(id);
    }


    public void update(User data) {
        DB.getInstance().updateUser(data);
    }


    public void delete(int id) {
        DB.getInstance().deleteUser(id);
    }


    public User readUserByEmail(String email) {
        return DB.getInstance().readUserByEmail(email);
    }

    public List<User> read() {
        return DB.getInstance().read();
    }
}
