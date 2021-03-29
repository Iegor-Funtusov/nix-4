package ua.com.alevel.persistence.repository.auth;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.auth.AuthToken;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.AbstractRepository;
import ua.com.alevel.persistence.type.PlatformType;

import java.util.Optional;

@Repository
public interface AuthTokenRepository extends AbstractRepository<AuthToken> {

    Optional<AuthToken> findByAuthTokenAndPlatformType(String authToken, PlatformType platformType);
    Optional<AuthToken> findByUserAndPlatformType(User user, PlatformType platformType);

    boolean existsByAuthToken(String token);

    void deleteByAuthTokenAndPlatformType(String authToken, PlatformType platformType);
}
