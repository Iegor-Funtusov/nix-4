package ua.com.alevel.config.security;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ua.com.alevel.persistence.entity.auth.AuthToken;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.auth.AuthTokenRepository;
import ua.com.alevel.persistence.type.PlatformType;
import ua.com.alevel.persistence.type.RoleType;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class SecurityConfig extends OncePerRequestFilter {

    private final AuthTokenRepository authTokenRepository;
    private final Set<String> openResources;

    public SecurityConfig(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
        this.openResources = new HashSet<>();
        this.initOpenResources();
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String resource = request.getServletPath();
        if (isOpenResource(resource)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            response.sendError(401, "unauthorized");
            return;
        }
        String platform = request.getHeader("Platform");
        if (StringUtils.isBlank(platform)) {
            response.sendError(401, "unauthorized");
            return;
        }
        PlatformType platformType = null;
        try {
            platformType = PlatformType.valueOf(platform);
        } catch (Exception e) {
            response.sendError(401, "unauthorized");
            return;
        }
        AuthToken authToken = authTokenRepository.findByAuthTokenAndPlatformType(token, platformType).orElse(null);
        if (authToken == null) {
            response.sendError(401, "unauthorized");
            return;
        }
        if (new Date().after(authToken.getExpiredAuth())) {
            authTokenRepository.deleteByAuthTokenAndPlatformType(token, platformType);
            response.sendError(401, "unauthorized");
            return;
        }
        User user = authToken.getUser();
        RoleType roleType = user.getRoleType();
        String[] paths = resource.split("/");
        if (ObjectUtils.notEqual(paths[2], roleType.getRole())) {
            response.sendError(401, "unauthorized");
            return;
        }

        request.setAttribute("currentUser", user);
        filterChain.doFilter(request, response);
    }

    private boolean isOpenResource(String resource) {
        return this.openResources.stream().anyMatch(openResource -> openResource.equals(resource));
    }

    private void initOpenResources() {
        this.openResources.add("/api/registration");
        this.openResources.add("/api/login");
    }

    @Bean
    public FilterRegistrationBean<SecurityConfig> filter() {
        FilterRegistrationBean<SecurityConfig> bean = new FilterRegistrationBean<>();
        bean.setFilter(new SecurityConfig(authTokenRepository));
        bean.addUrlPatterns("/api/*");
        return bean;
    }
}
