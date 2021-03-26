package ua.com.alevel.dao;

import ua.com.alevel.entity.AuthToken;

public interface AuthTokenService {

    void create(AuthToken authToken);
    String findUserIdByTokenId(String tokenId);
    String findTokenIdByUserId(String tokenId);
}
