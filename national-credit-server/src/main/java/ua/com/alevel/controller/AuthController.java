package ua.com.alevel.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.alevel.dto.request.LoginDto;
import ua.com.alevel.dto.request.RegisterDto;
import ua.com.alevel.dto.response.DataContainer;
import ua.com.alevel.service.security.SecurityService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(tags = "authentication endpoints API !!!")
public class AuthController {

    private final SecurityService securityService;

    public AuthController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/login")
    public ResponseEntity<DataContainer<String>> login(@RequestBody @Valid LoginDto loginDto) {
        return ResponseEntity.ok(new DataContainer<>(securityService.login(loginDto)));
    }

    @PostMapping("/registration")
    public ResponseEntity<DataContainer<Boolean>> registration(@RequestBody @Valid RegisterDto registerDto) {
        securityService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataContainer<>(true));
    }
}
