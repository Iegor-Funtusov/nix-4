package ua.com.alevel.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile extends AbstractData {

    private String phone;
    private int userId;

    public Profile() {
        super();
    }
}
