package ua.com.alevel.service.security;

import ua.com.alevel.dto.request.LoginDto;
import ua.com.alevel.dto.request.RegisterDto;

public interface SecurityService {

    String login(LoginDto loginDto);
    void register(RegisterDto registerDto);
}
