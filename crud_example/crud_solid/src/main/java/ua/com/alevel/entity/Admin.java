package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin extends User {

    public Admin() {
        super();
        setRole(Role.ADMIN);
    }
}
