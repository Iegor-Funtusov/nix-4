package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;

public class DB {

    private final List<User> users = new ArrayList<>();

    private static DB instance;

    private DB() {}

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public void createUser(User data) {
        int size = users.size();
        int id = size + 1;
        data.setId(id);
        users.add(data);
    }

    public User readUser(int id) {
        return users.stream().filter(data -> data.getId() == id).findFirst().orElse(null);
    }

    public User readUserByEmail(String email) {
        return users.stream().filter(data -> data.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<User> read() {
        return users;
    }

    public void updateUser(User data) {
        User current = readUser(data.getId());
        current.setAge(data.getAge());
        current.setName(data.getName());
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
