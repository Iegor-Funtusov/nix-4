package ua.com.alevel.persistence.entity.auth;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.type.PlatformType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "auth_tokens")
public class AuthToken extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "auth_token", nullable = false, unique = true)
    private String authToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_auth", nullable = false)
    private Date expiredAuth;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform_type", nullable = false)
    private PlatformType platformType;

    public AuthToken() {
        super();
    }
}
