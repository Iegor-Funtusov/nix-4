package ua.com.alevel.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel(value = "RegisterDto", description = "basic registration data by email")
public class RegisterDto {

    @NotBlank(message = "email can not be empty or email is not a correct format")
    @Email(message = "email is not in the correct format")
    @ApiModelProperty(notes = "parameter email: can be unique, errors: {email can not be empty or email is not a correct format}")
    private String login;

    @NotBlank(message = "password can not be empty")
    @ApiModelProperty(notes = "parameter password, errors: {password can not be empty}")
    private String password;
}
