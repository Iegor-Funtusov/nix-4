package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class AdminEntity extends UserEntity {

    public AdminEntity() {
        setRoleType(RoleType.ROLE_ADMIN);
    }
}
