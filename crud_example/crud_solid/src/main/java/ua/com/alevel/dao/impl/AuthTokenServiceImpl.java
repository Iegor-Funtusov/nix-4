package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AuthTokenService;
import ua.com.alevel.db.DBInMemory;
import ua.com.alevel.entity.AuthToken;

import java.util.UUID;

public class AuthTokenServiceImpl implements AuthTokenService {

    private final DBInMemory db = DBInMemory.getInstance();

    @Override
    public void create(AuthToken authToken) {
        String token = generateToken(UUID.randomUUID().toString());
        authToken.setToken(token);
        db.createToken(authToken);
    }

    @Override
    public String findUserIdByTokenId(String tokenId) {
        return db.getUserIdByToken(tokenId);
    }

    @Override
    public String findTokenIdByUserId(String userId) {
        return db.getTokenIdByUserId(userId);
    }

    private String generateToken(String token) {
        if (db.isTokenExist(token)) {
            return generateToken(UUID.randomUUID().toString());
        }
        return token;
    }
}
