package ua.com.alevel.db;

import ua.com.alevel.data.Profile;
import ua.com.alevel.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestDB {

    private final List<User> users = new ArrayList<>();
    private final List<Profile> profiles = new ArrayList<>();

    private static TestDB instance;

    private TestDB() {}

    public static TestDB getInstance() {
        if (instance == null) {
            instance = new TestDB();
        }
        return instance;
    }

    public void createUser(User data) {
        int size = users.size();
        int id = size + 1;
        data.setId(id);
        users.add(data);


        System.out.println(" *********** USERS ***********");
        users.forEach(System.out::println);
    }

    public void createProfile(Profile data) {
        int size = profiles.size();
        int id = size + 1;
        data.setId(id);
        profiles.add(data);
    }

    public List<User> readAllUsers() {
        return users;
    }

    public User readUser(int id) {
        return users.stream().filter(data -> data.getId() == id).findFirst().get();
    }

    public User readUserByEmail(String email) {
        return users.stream().filter(data -> data.getEmail().equals(email)).findFirst().get();
    }

    public Profile readProfile(int id) {
        return profiles.stream().filter(data -> data.getId() == id).findFirst().get();
    }

    public void updateUser(User data) {
        User current = readUser(data.getId());
        current.setAge(data.getAge());
        current.setName(data.getName());
    }

    public void updateProfile(Profile data) {
        Profile current = readProfile(data.getId());
        current.setPhone(data.getPhone());
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public void deleteProfile(int id) {
        profiles.removeIf(profile -> profile.getId() == id);
    }

    public void removeByUserId(int userId) {
        List<Profile> list = profiles.stream().filter(profile -> profile.getUserId() == userId).collect(Collectors.toList());
        list.forEach(profile -> deleteProfile(profile.getId()));
    }
}
