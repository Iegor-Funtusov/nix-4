package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuthToken extends BaseEntity {

    private String token;
    private String userId;
    private Date expired;

    public AuthToken() {
        super();
    }
}
