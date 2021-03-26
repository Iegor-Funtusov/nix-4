package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Personal extends User {

    private String firstName;
    private String lastName;

    public Personal() {
        super();
        setRole(Role.PERSONAL);
    }

    @Override
    public String toString() {
        return "Personal{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + super.getId() + '\'' +
                ", login='" + super.getLogin() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                '}';
    }
}
