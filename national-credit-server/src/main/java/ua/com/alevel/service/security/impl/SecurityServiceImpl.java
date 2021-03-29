package ua.com.alevel.service.security.impl;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.LoginDto;
import ua.com.alevel.dto.request.RegisterDto;
import ua.com.alevel.exception.model.FieldErrorModel;
import ua.com.alevel.exception.status.RestUnProcessableEntityException;
import ua.com.alevel.exception.status.RestUnauthorizedException;
import ua.com.alevel.persistence.entity.auth.AuthToken;
import ua.com.alevel.persistence.entity.balance.ClientBalance;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.auth.AuthTokenRepository;
import ua.com.alevel.persistence.repository.balance.ClientBalanceRepository;
import ua.com.alevel.persistence.repository.user.ClientRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.security.SecurityService;
import ua.com.alevel.util.DateUtil;
import ua.com.alevel.util.SecurityUtil;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Value("${security.expired.time.token}")
    private int expired;

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final AuthTokenRepository authTokenRepository;
    private final ClientBalanceRepository clientBalanceRepository;

    public SecurityServiceImpl(UserRepository userRepository, ClientRepository clientRepository, AuthTokenRepository authTokenRepository, ClientBalanceRepository clientBalanceRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.authTokenRepository = authTokenRepository;
        this.clientBalanceRepository = clientBalanceRepository;
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getLogin()).orElse(null);
        if (user == null) {
            throw new RestUnauthorizedException(Collections.singletonList(
                    new FieldErrorModel("email", HttpStatus.UNAUTHORIZED.getReasonPhrase(), "user not found")));
        }
        String hash = SecurityUtil.generateMD5(loginDto.getPassword());
        if (ObjectUtils.notEqual(hash, user.getPassword())) {
            throw new RestUnauthorizedException(Collections.singletonList(
                    new FieldErrorModel("email", HttpStatus.UNAUTHORIZED.getReasonPhrase(), "user not found")));
        }

        AuthToken authToken = authTokenRepository.findByUserAndPlatformType(user, loginDto.getPlatformType()).orElse(null);
        if (authToken == null) {
            authToken = new AuthToken();
            String token = generateToken(UUID.randomUUID().toString());
            authToken.setAuthToken(token);
            authToken.setPlatformType(loginDto.getPlatformType());
            authToken.setExpiredAuth(DateUtil.addMinuteTime(new Date(), expired));
            authToken.setUser(user);
            authTokenRepository.save(authToken);
            return token;
        }
        return authToken.getAuthToken();
    }

    @Override
    public void register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getLogin())) {
            throw new RestUnProcessableEntityException(Collections.singletonList(
                    new FieldErrorModel("email", HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), "this email is already exist")));
        }
        Client client = new Client();
        client.setEmail(registerDto.getLogin());
        client.setPassword(SecurityUtil.generateMD5(registerDto.getPassword()));
        client = clientRepository.save(client);
        ClientBalance clientBalance = new ClientBalance();
        clientBalance.setClient(client);
        clientBalanceRepository.save(clientBalance);
    }

    private String generateToken(String token) {
        if (authTokenRepository.existsByAuthToken(token)) {
            return generateToken(UUID.randomUUID().toString());
        }
        return token;
    }
}
