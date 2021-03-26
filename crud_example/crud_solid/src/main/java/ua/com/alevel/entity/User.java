package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public abstract class User extends BaseEntity {

    private String login;
    private String password;
    private Role role;

    public User() {
        super();
    }
}
