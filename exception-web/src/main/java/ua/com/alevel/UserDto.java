package ua.com.alevel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "name cann't be empty")
    private String name;

    @NotBlank(message = "email cann't be empty")
    @Email(message = "email field is wrong format")
    private String email;
}
