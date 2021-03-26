package ua.com.alevel.db;

import ua.com.alevel.entity.AuthToken;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Personal;
import ua.com.alevel.entity.Role;
import ua.com.alevel.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DBInMemory {

    private static DBInMemory db;

    private final List<User> users;
    private final List<AuthToken> authTokens;

    private DBInMemory() {
        this.authTokens = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public static DBInMemory getInstance() {
        if (db == null) {
            db = new DBInMemory();
        }
        return db;
    }

    public void createUser(User user) {
        user.setId(generateId(UUID.randomUUID().toString(), User.class));
        users.add(user);
    }

    public <U extends User> void updateUser(U user) {
        User current = findById(user.getId());
        if (current.getClass().isAssignableFrom(Personal.class)) {
            Personal currentPersonal = (Personal) current;
            Personal requestPersonal = (Personal) user;
            currentPersonal.setFirstName(requestPersonal.getFirstName());
            currentPersonal.setLastName(requestPersonal.getLastName());
        }
    }

    public void deleteUser(String id) {
        User current = findById(id);
        this.users.removeIf(user -> user.getId().equals(current.getId()));
    }

    public List<Personal> findAllPersonals() {
        return this.users.stream()
                .filter(user -> user.getRole().equals(Role.PERSONAL))
                .map(user -> (Personal) user)
                .collect(Collectors.toList());
    }

    public <U extends User> U findById(String id) {
        User current = users.stream().filter(u -> id.equals(u.getId())).findFirst().orElse(null);
        if (current == null) {
            throw new RuntimeException("user not found");
        }
        return (U) current;
    }

    public <U extends User> U findByLogin(String login) {
        User current = users.stream().filter(u -> login.equals(u.getLogin())).findFirst().orElse(null);
        return (U) current;
    }

    public boolean existByLogin(String login) {
        return users.stream().anyMatch(user -> user.getLogin().equals(login));
    }

    public void createToken(AuthToken authToken) {
        authToken.setId(generateId(UUID.randomUUID().toString(), AuthToken.class));
        this.authTokens.add(authToken);
    }

    public boolean isTokenExist(String token) {
        return authTokens.stream().anyMatch(authToken -> authToken.getToken().equals(token));
    }

    public boolean isUserExist(String id) {
        return users.stream().anyMatch(user -> user.getId().equals(id));
    }

    public String getUserIdByToken(String token) {
        return this.authTokens
                .stream()
                .filter(authToken -> authToken.getToken().equals(token))
                .findFirst().get().getUserId();
    }

    public String getTokenIdByUserId(String userId) {
        return this.authTokens
                .stream()
                .filter(authToken -> authToken.getUserId().equals(userId))
                .findFirst().get().getToken();
    }

    private <C extends BaseEntity> String generateId(String id, Class<C> aClass) {
        if (aClass.isAssignableFrom(User.class)) {
            if (users.stream().anyMatch(user -> user.getId().equals(id))) {
                return generateId(UUID.randomUUID().toString(), User.class);
            }
            return id;
        }
        if (aClass.isAssignableFrom(AuthToken.class)) {
            if (authTokens.stream().anyMatch(authToken -> authToken.getId().equals(id))) {
                return generateId(UUID.randomUUID().toString(), AuthToken.class);
            }
            return id;
        }
        return null;
    }
}
