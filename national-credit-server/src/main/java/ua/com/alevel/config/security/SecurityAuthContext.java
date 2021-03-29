package ua.com.alevel.config.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ua.com.alevel.persistence.entity.user.User;

import javax.servlet.http.HttpServletRequest;

@Component
@Scope(value = "session",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SecurityAuthContext {

    private final HttpServletRequest httpServletRequest;

    public SecurityAuthContext(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    public User getCurrentUser(){
        return (User) httpServletRequest.getAttribute("currentUser");
    }
}
