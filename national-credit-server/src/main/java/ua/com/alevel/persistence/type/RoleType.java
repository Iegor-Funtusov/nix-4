package ua.com.alevel.persistence.type;

import lombok.Getter;

@Getter
public enum RoleType {

    ROLE_ADMIN("admin"), ROLE_CLIENT("client");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }
}
