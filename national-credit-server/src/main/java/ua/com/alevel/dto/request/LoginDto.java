package ua.com.alevel.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.type.PlatformType;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(value = "LoginDto", description = "basic login data by email")
public class LoginDto extends RegisterDto {

    @NotNull(message = "platformType can not be empty")
    @ApiModelProperty(notes = "parameter platformType, errors: {platformType can not be empty}")
    private PlatformType platformType;
}
