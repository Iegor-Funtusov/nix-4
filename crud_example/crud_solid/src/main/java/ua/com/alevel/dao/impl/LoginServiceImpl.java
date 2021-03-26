package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AuthTokenService;
import ua.com.alevel.dao.LoginService;
import ua.com.alevel.dao.UserService;
import ua.com.alevel.entity.AuthToken;
import ua.com.alevel.entity.Personal;
import ua.com.alevel.entity.User;
import ua.com.alevel.util.SecurityUtil;

import java.io.File;

public class LoginServiceImpl implements LoginService {

    private final AuthTokenService authTokenService = new AuthTokenServiceImpl();
    private final UserService<Personal> userService = new PersonalService();

    @Override
    public String login(String login, String password) {
        User user = userService.findByLogin(login);
        if (user == null) {
            throw new RuntimeException("401");
        }
        String hash = SecurityUtil.generateMD5(password);
        if (!hash.equals(user.getPassword())) {
            throw new RuntimeException("401");
        }
        AuthToken authToken = new AuthToken();
        authToken.setUserId(user.getId());
        authTokenService.create(authToken);

        return authTokenService.findTokenIdByUserId(user.getId());
    }
}
